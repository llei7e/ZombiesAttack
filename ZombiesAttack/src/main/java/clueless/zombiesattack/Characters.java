package clueless.zombiesattack;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public abstract class Characters extends ImageView {

    // Properties
    private int height;
    private int width;
    private int positionX;
    private int positionY;
    private boolean right = false;
    private boolean left = false;
    private int life;
    private int speed;
    private int strength;
    protected ImageView sprite;
    protected Image [] walking = new Image[6];


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

    public void move(int frame) {
        // Timeline declaration and set a cycle
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        // it checks which direction to move and it reverses if diff.
        if (right && speed < 0)
            speed *= -1;
        else if (left && speed > 0)
            speed *= -1;

        if(right||left) {
            // check sprite direction and setSprite
            if(right)
                setSprite(walking[frame]);
            else
                setSprite(walking[frame+3]);
            double displacement = sprite.xProperty().get() + speed;
            // define KeyValue used in KeyFrame
            KeyValue kv = new KeyValue(sprite.xProperty(), displacement, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.millis(30), kv);
            // added KeyFrame to timeline object
            timeline.getKeyFrames().add(kf);
            timeline.play();
        }
    }

    public void move() {
        // Timeline declaration and set a cycle
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        // it checks which direction to move and it reverses if diff.
        if (right && speed < 0)
            speed *= -1;
        else if (left && speed > 0)
            speed *= -1;

        if(right||left) {
            double displacement = sprite.xProperty().get() + speed;
            // define KeyValue used in KeyFrame
            KeyValue kv = new KeyValue(sprite.xProperty(), displacement, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.millis(30), kv);
            // added KeyFrame to timeline object
            timeline.getKeyFrames().add(kf);
            timeline.play();
        }
    }

    // Getters

    public int getLife() {
        return life;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public ImageView getSprite() {return sprite;}
    
    // Setters

    public void setLife(int life) {
        this.life = life;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setSprite(Image img) {
        this.sprite.setImage(img);
        this.sprite.setFitWidth(60);
        this.sprite.setFitHeight(80);
    }

    public void setSprite(int frame) {
        // define new sprite considering time
        this.sprite.setImage(walking[frame]);
        this.sprite.setFitWidth(60);
        this.sprite.setFitHeight(80);
    }
    // test
    public void setSprite (int frame, Image [] frames) {
        this.sprite.setImage(frames[frame]);
        this.sprite.setFitWidth(60);
        this.sprite.setFitHeight(80);
    }
}



