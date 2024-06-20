package clueless.zombiesattack;

import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


import java.util.Objects;


public abstract class Characters extends ImageView {

    // Properties
    private int height;
    private int width;
    private int positionX;
    private int positionY;
    private int life;
    private int speed;
    private int strength;
    protected ImageView sprite;


    // Constructor

    public Characters(int height, int width, int positionX, int positionY, int life, int speed, int strength, Image sprite) {
        this.sprite = new ImageView(sprite);
        this.height = height;
        this.width = width;
        this.positionX = positionX;
        this.positionY = positionY;
        this.life = life;
        this.speed = speed;
        this.strength = strength;
    }

    // Methods

    public void move(boolean r, boolean l) {
        // Timeline declaration and set a cycle
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        // it checks which direction to move and it reverses if diff.
        if (r && speed < 0)
            speed *= -1;
        else if (l && speed > 0)
            speed *= -1;

        if(r||l) {
            double desloc = sprite.xProperty().get() + speed;

            // define KeyValue used in KeyFrame
            KeyValue kv = new KeyValue(sprite.xProperty(), desloc, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.millis(30), kv);
            // added KeyFrame to timeline object
            timeline.getKeyFrames().add(kf);
            timeline.play();
        }
    }

    // Getters

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getLife() {
        return life;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }
    
    // Setters
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

}



