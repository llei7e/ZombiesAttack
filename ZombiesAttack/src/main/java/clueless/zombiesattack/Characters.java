package clueless.zombiesattack;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;


public abstract class Characters extends ImageView {

    // Properties
    private boolean right = false;
    private boolean left = false;
    private int life;
    private int speed;
    private int strength;
    private ImageView sprite;
    protected Image [] walking = new Image[6];
//  private Image [] walking = new Image[6];

    // Constructor
    public Characters() {
        this.sprite = new ImageView();
    }

    // Methods

    public void move(int frame, String weapon) {
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
                setSprite(walking[frame], weapon);
            else
                setSprite(walking[frame+3], weapon);
            double displacement = sprite.xProperty().get() + speed;
            // define KeyValue used in KeyFrame
            KeyValue kv = new KeyValue(sprite.xProperty(), displacement, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.millis(30), kv);
            // added KeyFrame to timeline object
            timeline.getKeyFrames().add(kf);
            timeline.play();
        }
    }

    public void move(int frame, int type) {
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
                setSprite(walking[frame], type);
            else
                setSprite(walking[frame+3], type);
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

    public ImageView getSprite() { return sprite; }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

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

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setSprite(Image img, String weapon) {
        if (Objects.equals(weapon, "knife")){
            this.sprite.setImage(img);
            this.sprite.setFitWidth(55);
            this.sprite.setFitHeight(80);
        }
        if (Objects.equals(weapon, "pistol")){
            this.sprite.setImage(img);
            this.sprite.setFitWidth(70);
            this.sprite.setFitHeight(80);
        }
    }

    // Define zombie sprite according to the type
    public void setSprite(Image img, int type) {
        //Small Zombie
        if(type == 1){
            this.sprite.setImage(img);
            this.sprite.setFitWidth(50);
            this.sprite.setFitHeight(65);
        }

        //Medium Zombie
        if(type == 2){
            this.sprite.setImage(img);
            this.sprite.setFitWidth(60);
            this.sprite.setFitHeight(80);
        }

        //Great Zombie
        if(type == 3){
            this.sprite.setImage(img);
            this.sprite.setFitWidth(75);
            this.sprite.setFitHeight(100);
        }
    }

    public void setSprite(Image img, String weapon, boolean isShooting) {
        if (Objects.equals(weapon, "knife")){
            this.sprite.setImage(img);
            this.sprite.setFitWidth(70);
            this.sprite.setFitHeight(80);
        }
        if (Objects.equals(weapon, "pistol")){
            this.sprite.setImage(img);
            this.sprite.setFitWidth(70);
            this.sprite.setFitHeight(80);
        }
    }
}



