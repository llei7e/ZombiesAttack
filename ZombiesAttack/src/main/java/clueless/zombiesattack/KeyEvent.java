package clueless.zombiesattack;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;


public class KeyEvent {

    private boolean paused = false; // pause gameLooping
    private boolean canRestart = false; // can restart the gameLooping
    private double difficulty = 1;

    public void keyEvent(Scene scene, Pane pane, Player p1, ArrayList<Zombies> zombies,
                         ImageView life, ImageView weapon, Text weaponName, Text points, Stage stage) {

        AnimationTimer gameLooping = new AnimationTimer() {

            // Properties

            // time for frames
            private long lastUpdate = 0;
            private int currentFrame = 0;

            // booleans
            private boolean hitBreak = false;
            private boolean canSpawn = true;
            private boolean canSpawnHealing = true;

            // HUD images
            Image lifeImage;
            Image weaponImg;

            // Methods

            // Set gameLooping RUNNING
            private void gameRunning() {
                    // start new game or restart the game
                    this.start();
                    paused = false;
                    canRestart = !canRestart;
            }

            // Check if playerCanMove
            private boolean canMove () {
                // limit on the right side
                double endScreen = pane.getWidth() - p1.getSprite().getFitWidth();

                // check if exceed right limit of the screen
                boolean rightLimit = p1.getSprite().getX() > endScreen && p1.isRight();

                // check if exceed left limit of the screen (0)
                boolean leftLimit = p1.getSprite().getX() < 0 && p1.isLeft();

                return rightLimit || leftLimit;
            }

            // CheckCollision method
            private boolean checkCollision(Characters Player, Characters Zombie) {
                //check collision between player and zombie
                return Player.getSprite().getBoundsInParent().intersects(Zombie.getSprite().getBoundsInParent());
            }

            // ZombieFactory method
            private Zombies zombieFactory() {
                // Define random value and type
                Random r = new Random();
                int X = r.nextInt(2)+1;
                int type = r.nextInt(3)+1;
                // Define positionX
                int value = r.nextInt(3) + 1;
                if (value == 1)
                    X = 1446;
                else if(value == 2)
                    X = -15;

                return new Zombies(X, type);
            }

            // Spawn Healing method
            private ImageView spawnHealing () {
                canSpawnHealing = !canSpawnHealing;

                ImageView healing = new ImageView(new Image("healing.png"));
                Random r = new Random();

                healing.setFitHeight(40);
                healing.setFitWidth(40);
                healing.setX(r.nextInt(1200)+100);
                healing.setY(425);

                pane.getChildren().add(healing);

                return healing;
            }

            // GAME LOOPING
            @Override
            public void handle(long now) {
                // check if gameLooping can run
               if (canRestart)
                   gameRunning();

                // Define nanoTime
                if (now - lastUpdate >= 200000000) { // 200ms
                    lastUpdate = now; // update lastUpdate
                    currentFrame = (currentFrame + 1) % 3; // change between three frames
                }

            // Keyboard Input

                // check if keyboard has been pressed
                scene.setOnKeyPressed(event -> {
                    if (paused) return; // disable input
                    switch (event.getCode()){
                        case W:
                            p1.jump();
                            break;
                        case J:
                            p1.attack(pane, zombies);
                            break;
                        case D :
                            p1.setRight(true);
                            p1.setLeft(false);
                            p1.setDirection("right");
                            break;
                        case A:
                            p1.setRight(false);
                            p1.setLeft(true);
                            p1.setDirection("left");
                            break;
                        case P:
                            p1.setLeft(false);
                            p1.setRight(false);
                            gamePaused(scene, pane, this);
                            break;
                    }
                });

                // only moves when there is no shot
                if (!p1.getShooting() && !canMove()){
                    p1.move(currentFrame, p1.getWeapon());
                }

                // check if keyboard has been released
                scene.setOnKeyReleased(event -> {
                    if (paused) return; // disable input

                    if (event.getCode() == KeyCode.D) {
                        p1.setRight(false);
                        switch (p1.getWeapon()) {
                            case "knife" -> p1.setSprite(new Image("knifewalk-right2.png"), p1.getWeapon());
                            case "pistol" -> p1.setSprite(new Image("rickwalk2-right.png"), p1.getWeapon());
                            case "katana" -> p1.setSprite(new Image("katanawalk-right2.png"), p1.getWeapon());
                            case null, default -> p1.setSprite(new Image("riflewalk-right2.png"), p1.getWeapon());
                        }
                    }
                    if (event.getCode() == KeyCode.A) {
                        p1.setLeft(false);
                        switch (p1.getWeapon()) {
                            case "knife" -> p1.setSprite(new Image("knifewalk-left2.png"), p1.getWeapon());
                            case "pistol" -> p1.setSprite(new Image("rickwalk2-left.png"), p1.getWeapon());
                            case "katana" -> p1.setSprite(new Image("katanawalk-left2.png"), p1.getWeapon());
                            case null, default -> p1.setSprite(new Image("riflewalk-left2.png"), p1.getWeapon());
                        }
                    }
                    if (event.getCode() == KeyCode.J) {
                        p1.cooldownDelay();
                    }
                });

                // Player damage
                // collision check between player end zombie
                for (Zombies zombie : zombies)
                    if (checkCollision(p1, zombie) && !hitBreak) {
                        p1.hit(zombie);
                        p1.setLife(p1.getLife() - (int)zombie.getStrength());
                        hitBreak = true;
                        // emit punch sound
                        if (zombie.getType() == 3)
                            Sounds.getBigPunch().play();
                        else
                            Sounds.getSmallPunch().play();

                        p1.takeDamage();

                        // hit delay
                        PauseTransition delay = new PauseTransition(Duration.seconds(2));
                        delay.setOnFinished(event -> hitBreak = false);
                        delay.play();

                    }
                // check zombie collision
                for (Zombies zombie : zombies) {
                    if (!checkCollision(p1, zombie))
                        zombie.chasing(p1, zombie, currentFrame);
                }

                //HUD
                //Define Life Image
                if(p1.getLife() >= 0)
                    lifeImage = new Image("life" + p1.getLife() + ".png");
                else
                    lifeImage = new Image("life0.png");
                life.setImage(lifeImage);

                // Player died
                if (p1.getLife() <= 0) {

                    canSpawn = false;
                    canSpawnHealing = false;
                    for (Zombies z : zombies)
                        pane.getChildren().remove(z.getSprite());
                    zombies.clear();
                    this.stop();
                    paused = true;
                    canRestart = true;
                    p1.animationEndgame(scene, pane, stage);
                    // End game
                }

                //Define Weapon Image
                weaponImg = new Image(p1.getWeapon() + ".png");
                weapon.setImage(weaponImg);

                //Define Weapon Text
                weaponName.setText(p1.getWeapon());

                //Define Points
                points.setText(String.valueOf(p1.getPoints()) + " pts");

                //DIFFICULTY
                switch (p1.getWeapon()){
                    case "katana":
                        difficulty = 0.5;
                        break;
                    case "pistol":
                        difficulty = 0.3;
                        break;
                    case "rifle":
                        difficulty = 0.2;
                        break;
                    default:
                        difficulty = 1;
                        break;
                }


            // SPAWN ZOMBIES - REMOVE ZOMBIES
                // Add zombies
                if (canSpawn) {
                    canSpawn = false;
                    // Define spawn zombie
                    Zombies z = zombieFactory();
                    zombies.add(z);
                    pane.getChildren().add(z.getSprite());
                    // Define delay (Wave)
                    PauseTransition delay = new PauseTransition(Duration.seconds(difficulty));
                    delay.setOnFinished(event -> canSpawn = true);
                    delay.play();
                }

                // Spawn Healing
                if (canSpawnHealing){
                    ImageView spawnedHealing = spawnHealing();
                    AnimationTimer check = p1.checkHealing(spawnedHealing, pane);

                    PauseTransition waitingHealing = new PauseTransition(Duration.seconds(45));
                    waitingHealing.setOnFinished(e -> {
                        canSpawnHealing = !canSpawnHealing;
                        pane.getChildren().remove(spawnedHealing);
                        check.stop();
                    });
                    Sounds.getSpawn().play();
                    waitingHealing.play();
                    check.start();
                }

                // Remove zombies of ArrayList
                zombies.removeIf(z -> z.getLife() <= 0);

                //change Weapons by points
                if(p1.getPoints() > 199 && p1.getPoints() < 399 && Objects.equals(p1.getWeapon(), "knife")) {
                    p1.setWeapon("katana");
                    p1.playerWeapons();
                }
                if(p1.getPoints() > 599 && p1.getPoints() < 799 && Objects.equals(p1.getWeapon(), "katana")) {
                    p1.setWeapon("pistol");
                    p1.playerWeapons();
                }
                if(p1.getPoints() > 1199 && Objects.equals(p1.getWeapon(), "pistol")) {
                    p1.setWeapon("rifle");
                    p1.playerWeapons();
                }

            }
        };
        gameLooping.start();
    }

    // game pause
    private void gamePaused (Scene scene, Pane pane, AnimationTimer gameLooping ) {
        // stop gameLooping
        paused = !paused;

        Sounds.getOption().play();
        gameLooping.stop();
        // create pauseText
        Text pauseText = new Text("Paused");
        pauseText.setX(pane.getWidth()/2 - 50);
        pauseText.setY(360);
        pauseText.getStyleClass().add("pauseText");

        pane.getChildren().add(pauseText);

        // pauseAnimation
        KeyValue kv = new KeyValue(pauseText.yProperty(), 335, Interpolator.EASE_BOTH);
        Timeline pauseAnimation = new Timeline(new KeyFrame(Duration.seconds(2), kv));
        pauseAnimation.setAutoReverse(true);
        pauseAnimation.setCycleCount(Timeline.INDEFINITE);

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                scene.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.P){
                        Sounds.getOption().play();
                        paused = !paused; // set paused to false
                        pane.getChildren().remove(pauseText); // remove pauseText
                        this.stop(); // end of gamePause
                        gameLooping.start(); // restart gameLooping
                    }
                });
                pauseAnimation.play();
            }
        }.start();
    }
}
