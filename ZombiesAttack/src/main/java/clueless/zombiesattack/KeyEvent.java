package clueless.zombiesattack;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Objects;


public class KeyEvent {
    private boolean isShooting = false;

    private String direction = "right";


    public void keyEvent(Scene scene, Pane pane, Player p1, Zombies z1, Zombies z2, Zombies z3,
                         ImageView life, ImageView weapon, Text weaponName, Text points) {
        new AnimationTimer() {
            private long lastUpdate = 0;
            private int currentFrame = 0;
            Image lifeImage;
            Image weaponImg;

            // check collision between player and zombie
            public boolean checkCollision(Characters Player, Characters Zombie) {
                return Player.sprite.getBoundsInParent().intersects(Zombie.sprite.getBoundsInParent());
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
                        direction = "right";
                    }
                    if (event.getCode() == KeyCode.A) {
                        p1.setRight(false);
                        p1.setLeft(true);
                        direction = "left";
                    }
                    if (event.getCode() == KeyCode.W) {
                        p1.jump();
                        p1.setLife(p1.getLife() - 1);
                        p1.setPoints(p1.getPoints() + 100);
                        p1.setWeapon("knife");
                    }
                    if (event.getCode() == KeyCode.J) {
                        p1.attack(direction, pane, isShooting, z1);
                        isShooting = true;
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
                        isShooting = false; // end of shooting
                    }
                });
                if (z1.getLife() > 0)
                    if (!checkCollision(p1, z1))
                        z1.chasing(p1, z1, currentFrame);
                // only moves when it is no shot
                if (!isShooting)
                    p1.move(currentFrame);

                z2.chasing(p1, z2, currentFrame);
                z3.chasing(p1, z3, currentFrame);


                //Define Life Image
                lifeImage = new Image("life" + p1.getLife() + ".png");
                life.setImage(lifeImage);

                //Define Weapon Image
                weaponImg = new Image(p1.getWeapon() + ".png");
                weapon.setImage(weaponImg);

                //Define Weapon Text
                weaponName.setText(p1.getWeapon());

                //Define Points
                points.setText(String.valueOf(p1.getPoints()) + " pts");

            }
        }.start();
    }
}
