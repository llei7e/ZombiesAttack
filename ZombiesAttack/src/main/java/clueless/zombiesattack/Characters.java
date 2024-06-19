package clueless.zombiesattack;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import com.almasb.fxgl.physics.PhysicsComponent;

import java.util.Objects;


public abstract class Characters extends ImageView {

    // Properties
    private PhysicsComponent physics;
    private int height;
    private int width;
    private int positionX;
    private int positionY;
    private int life;
    private int speed;
    private int strength;
    BooleanProperty isJumping = new SimpleBooleanProperty(false);

    // Constructor

    public Characters(int height, int width, int positionX, int positionY, int life, int speed, int strength) {
        this.height = height;
        this.width = width;
        this.positionX = positionX;
        this.positionY = positionY;
        this.life = life;
        this.speed = speed;
        this.strength = strength;
    }

    // Methods
    public void move(String direction) {
        if (Objects.equals(direction, "Left")) {
            positionX -= 10;
        }
        else {
            positionX += 10;
        }
    }

    public void jump() {
        physics.setVelocityY(-400);
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



