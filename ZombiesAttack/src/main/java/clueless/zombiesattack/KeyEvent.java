package clueless.zombiesattack;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class KeyEvent {

    public void keyEvent(Scene scene, Pane pane, Player p1, ArrayList<Zombies> zombies,
                         ImageView life, ImageView weapon, Text weaponName, Text points) {

        new AnimationTimer() {

            // Properties
            private long lastUpdate = 0;
            private int currentFrame = 0;
            Image lifeImage;
            Image weaponImg;
            private boolean hitBreak = false;
            private boolean canSpawn = true;

            // check collision between player and zombie
            public boolean checkCollision(Characters Player, Characters Zombie) {

                return Player.sprite.getBoundsInParent().intersects(Zombie.sprite.getBoundsInParent());
            }

            //zombie factory
            private Zombies zombieFactory(int type) {
                Image img = new Image("zombieM-walking2.png");
                return new Zombies(10,10,10,0, type, img);
            }

            // game looping
            @Override
            public void handle(long now) {
                // define nanoTime
                if (now - lastUpdate >= 200000000) { // 200ms
                    lastUpdate = now; // update lastUpdate
                    currentFrame = (currentFrame + 1) % 3; // troca entre os três frames(p1 walking)
                }
                // check if keyboard has been pressed
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.D) {
                        p1.setRight(true);
                        p1.setLeft(false);
                        p1.setDirection("right");
                    }
                    if (event.getCode() == KeyCode.A) {
                        p1.setRight(false);
                        p1.setLeft(true);
                        p1.setDirection("left");
                    }
                    if (event.getCode() == KeyCode.W) {
                        p1.jump();
                    }
                    if (event.getCode() == KeyCode.J) {
                        p1.attack(pane, zombies);
                        p1.setShooting(true);
                    }
                });

                // check if keyboard has been released
                scene.setOnKeyReleased(event -> {
                    if (event.getCode() == KeyCode.D) {
                        p1.setRight(false);
                        p1.setSprite(new Image("rickwalk2-right.png"));
                    }
                    if (event.getCode() == KeyCode.A) {
                        p1.setLeft(false);
                        p1.setSprite(new Image("rickwalk2-left.png"));
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

                // only moves when it is no shot
                if (!p1.getShooting())
                    p1.move(currentFrame);


                //Define Life Image
                lifeImage = new Image("life" + p1.getLife() + ".png");
                life.setImage(lifeImage);
                if (p1.getLife() <= 0) {
                    Menu.gameOver(scene, pane);
                    zombies.clear();
                }

                //Define Weapon Image
                weaponImg = new Image(p1.getWeapon() + ".png");
                weapon.setImage(weaponImg);

                //Define Weapon Text
                weaponName.setText(p1.getWeapon());

                //Define Points
                points.setText(String.valueOf(p1.getPoints()) + " pts");

                // Add zombies
                if (canSpawn) {
                    canSpawn = false;
                    // Define spawn zombie
                    Zombies z = zombieFactory(1);
                    zombies.add(z);
                    pane.getChildren().add(z.getSprite());
                    // Define delay (Wave)
                    PauseTransition delay = new PauseTransition(Duration.seconds(3));
                    delay.setOnFinished(event -> canSpawn = true);
                    delay.play();
                }
                // Remove zombies of ArrayList
                zombies.removeIf(z -> z.getLife() <= p1.getStrength() && p1.getShooting());
            }
        }.start();
    }
}
