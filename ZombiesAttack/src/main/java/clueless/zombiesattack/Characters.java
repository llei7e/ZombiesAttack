package clueless.zombiesattack;

import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public abstract class Characters extends ImageView {

    // Properties
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
    public void move(KeyEvent key ,int positionX, int positionY) {
        if (key.getCode() == KeyCode.A) {
            positionX -= speed;
        }
        else if (key.getCode() == KeyCode.D){
            positionX += speed;
        }
    }

    public void jump(Characters character) {
        // Indicar que o salto começou
        isJumping.set(true);
        // Criar a animação de translação
        TranslateTransition jump = new TranslateTransition(Duration.millis(230), character);
        jump.setByY(-200);
        //define que reverterá a direção depois do primeiro ciclo
        jump.setAutoReverse(true);
        //Quantidade de ciclos(dois para ir e voltar)
        jump.setCycleCount(2);
        jump.setOnFinished(event -> isJumping.set(false));
        if (character.positionY > 375)
            jump.play();
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



