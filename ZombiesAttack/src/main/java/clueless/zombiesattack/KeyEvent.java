package clueless.zombiesattack;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.*;


public class KeyEvent {

    private boolean paused = false; // pause gameLooping

    public void keyEvent(Scene scene, Pane pane, Player p1, ArrayList<Zombies> zombies,
                         ImageView life, ImageView weapon, Text weaponName, Text points) {

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
                    X = 630;
                else if(value == 2)
                    X = -15;

                return new Zombies(X, type);
            }

            private ImageView spawnHealing () {
                canSpawnHealing = !canSpawnHealing;

                ImageView cure = new ImageView(new Image("cure.png"));
                Random r = new Random();

                cure.setFitHeight(85);
                cure.setFitWidth(60);
                cure.setX(r.nextInt(400)+100);
                cure.setY(410);

                pane.getChildren().add(cure);

                return cure;
            }


            // GAME LOOPING
            @Override
            public void handle(long now) {
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
                            p1.setPoints(p1.getPoints()+100);
                            break;
                        case J:
                            p1.attack(pane, zombies);
                            p1.setShooting(true);
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
                        if (Objects.equals(p1.getWeapon(), "knife")) {
                            p1.setSprite(new Image("knifewalk-right2.png"), p1.getWeapon());
                        } else if(Objects.equals(p1.getWeapon(), "pistol")) {
                            p1.setSprite(new Image("rickwalk2-right.png"), p1.getWeapon());
                        }
                        else if(Objects.equals(p1.getWeapon(), "katana")) {
                            p1.setSprite(new Image("katanawalk-right2.png"), p1.getWeapon());
                        } else{
                            p1.setSprite(new Image("riflewalk-right2.png"), p1.getWeapon());
                        }
                    }
                    if (event.getCode() == KeyCode.A) {
                        p1.setLeft(false);
                        if (Objects.equals(p1.getWeapon(), "knife")) {
                            p1.setSprite(new Image("knifewalk-left2.png"), p1.getWeapon());
                        } else if(Objects.equals(p1.getWeapon(), "pistol")) {
                            p1.setSprite(new Image("rickwalk2-left.png"), p1.getWeapon());
                        }
                        else if(Objects.equals(p1.getWeapon(), "katana")) {
                            p1.setSprite(new Image("katanawalk-left2.png"), p1.getWeapon());
                        } else{
                            p1.setSprite(new Image("riflewalk-left2.png"), p1.getWeapon());
                        }
                    }
                    if (event.getCode() == KeyCode.J) {
                        p1.setShooting(false); // end of shooting
                    }
                });

            // Player damage
                // collision check between player end zombie
                for (Zombies zombie : zombies)
                    if (checkCollision(p1, zombie) && !hitBreak) {
                        p1.setLife(p1.getLife() - zombie.getStrength());
                        hitBreak = true;

                        // hit delay
                        PauseTransition delay = new PauseTransition(Duration.seconds(3));
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
                if (p1.getLife() <= 0) {
                    canSpawn = false;
                    zombies.clear();
                    Menu.gameOver(scene, pane); // End game
                }

                //Define Weapon Image
                weaponImg = new Image(p1.getWeapon() + ".png");
                weapon.setImage(weaponImg);

                //Define Weapon Text
                weaponName.setText(p1.getWeapon());

                //Define Points
                points.setText(String.valueOf(p1.getPoints()) + " pts");


            // SPAWN ZOMBIES - REMOVE ZOMBIES
//                 Add zombies
                if (canSpawn) {
                    canSpawn = false;
                    // Define spawn zombie
                    Zombies z = zombieFactory();
                    zombies.add(z);
                    pane.getChildren().add(z.getSprite());
                    // Define delay (Wave)
                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                    delay.setOnFinished(event -> canSpawn = true);
                    delay.play();
                }

                // Spawn Healing
                if (canSpawnHealing){
                    ImageView spawnedHealing = spawnHealing();
                    AnimationTimer check = p1.checkHealing(spawnedHealing, pane);

                    PauseTransition waitingHealing = new PauseTransition(Duration.seconds(2));
                    waitingHealing.setOnFinished(e -> {
                        canSpawnHealing = !canSpawnHealing;
                        pane.getChildren().remove(spawnedHealing);
                        check.stop();
                    });
                    waitingHealing.play();
                    check.start();
                }

                // Remove zombies of ArrayList
                zombies.removeIf(z -> z.getLife() <= 0);

                if(p1.getPoints() > 199 && p1.getPoints() < 399 && Objects.equals(p1.getWeapon(), "knife")) {
                    p1.setWeapon("katana");
                    p1.playerWeapons();
                }
                if(p1.getPoints() > 399 && p1.getPoints() < 799 && Objects.equals(p1.getWeapon(), "katana")) {
                    p1.setWeapon("pistol");
                    p1.playerWeapons();
                }
                if(p1.getPoints() > 799 && Objects.equals(p1.getWeapon(), "pistol")) {
                    p1.setWeapon("rifle");
                    p1.playerWeapons();
                }

            }
        };
        gameLooping.start();
    }

    private void gamePaused (Scene scene, Pane pane, AnimationTimer gameLooping ) {
        // stop gameLooping
        paused = !paused;
        gameLooping.stop();
        // create pauseText
        Text pauseText = new Text("Paused");
        pauseText.setX(270);
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
