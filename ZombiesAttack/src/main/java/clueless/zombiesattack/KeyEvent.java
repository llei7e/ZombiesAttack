package clueless.zombiesattack;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import java.util.Objects;

public class KeyEvent{
    private boolean shooting = false;
    private String direction = "right";


    public void keyEvent(Scene scene, Pane pane, Player p1, Zombies z1, Zombies z2, Zombies z3 ) {
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
                        p1.setRight(true);
                        p1.setLeft(false);
                        direction = "right";
                    }
                    if (event.getCode() == KeyCode.A) {
                        p1.setRight(false);
                        p1.setLeft(true);
                        direction = "left";
                    }
                    if (event.getCode() == KeyCode.W){
                        p1.jump();
                    }
                    if (event.getCode() == KeyCode.J) {
                        p1.attack(direction, pane, shooting);
                        shooting = true;
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
                        shooting = false; // end of shooting
                    }
                });

                p1.move(currentFrame);
                z1.chasing(p1, z1, currentFrame);
                z2.chasing(p1,z2,currentFrame);
                z3.chasing(p1,z3,currentFrame);

            }
        }.start();
    }
}
