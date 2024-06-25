package clueless.zombiesattack;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class KeyEvent {
    private boolean right = false;
    private boolean left = false;
    private boolean isShooting = false;
    private String direction = "right";


    public void keyEvent(Scene scene, Pane pane, Player p1) {
        new AnimationTimer() {
            private long lastUpdate = 0;
            private int currentFrame = 0;

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
                        right = true;
                        left = false;
                        direction = "right";
                    }
                    if (event.getCode() == KeyCode.A) {
                        right = false;
                        left = true;
                        direction = "left";
                    }
                    if (event.getCode() == KeyCode.W){
                        p1.jump();
                    }
                    if (event.getCode() == KeyCode.J) {
                        p1.attack(direction, pane, isShooting, right);
                        isShooting = true;

                    }
                });

                // check if keyboard has been released
                scene.setOnKeyReleased(event -> {
                    if (event.getCode() == KeyCode.D) {
                        right = false;
                        p1.setSprite(new Image("rickwalk2-right.png"));
                    }
                    if (event.getCode() == KeyCode.A) {
                        left = false;
                        p1.setSprite(new Image("rickwalk2-left.png"));
                    }
                    if (event.getCode() == KeyCode.J) {
                        isShooting = false; // end of shooting
                    }
                });
                if (!isShooting)
                    p1.move(right, left, currentFrame);

            }
        }.start();
    }
}
