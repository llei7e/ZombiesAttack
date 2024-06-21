package clueless.zombiesattack;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Objects;

public class Player extends Characters {
    //Attributes
    private String name;
    private int points;
    private int timeSurvived;
    private String weapon;
    private boolean isJumping;


    //Constructor
    public Player(int height, int width, int positionX, int positionY, int life, int speed, int strength, Image img){
        super(height, width, positionX, positionY, life, speed, strength, img);
        this.sprite = new ImageView(img);
        this.sprite.setX(0);
        this.sprite.setY(390);
        this.sprite.setFitWidth(100);
        this.sprite.setFitHeight(100);
        this.name = "";
        this.points = 0;
        this.timeSurvived = 0;
        this.weapon = "knife";
        this.isJumping = false;
    }
    

    //Methods
    public int dash(int positionX, int positionY, boolean doubleClick){
        int dashSize = 3;
        //   int newX;
        return 0;
    }

    public void attack(String dir){
        Timeline timeline = new Timeline();

        Image projectile = new Image("brick.png"); // set image of bullet
        ImageView bullet = new ImageView(projectile);
        bullet.setY(sprite.getY() + sprite.getFitHeight()/2);
        KeyFrame kf;
        if(Objects.equals(dir, "right")) {
            bullet.setX(sprite.getX()+50);
            KeyValue kv = new KeyValue(bullet.xProperty(),620, Interpolator.EASE_BOTH);
            kf = new KeyFrame(Duration.millis(1500), kv);

        }
        else {
            bullet.setX(sprite.getX()+50);
            KeyValue kv = new KeyValue(bullet.xProperty(),0, Interpolator.EASE_BOTH);
            kf = new KeyFrame(Duration.millis(1500), kv);
        }
        timeline.getKeyFrames().add(kf);
        timeline.play();

        //Image attack = new Image("");
        //Image hit = new Image("");
    }

    public void jump() {
        if(!isJumping) {
            this.isJumping = true;

            Timeline timeline = new Timeline();
            timeline.setCycleCount(2);
            timeline.setAutoReverse(true);

            // Up effect
            KeyValue kvUp = new KeyValue(sprite.yProperty(), sprite.getY() - 150, Interpolator.EASE_BOTH);
            KeyFrame kfUp = new KeyFrame(Duration.millis(300), kvUp);
            // Down effect
            KeyValue kvDn = new KeyValue(sprite.yProperty(), sprite.getY(), Interpolator.EASE_BOTH);
            KeyFrame kfDn = new KeyFrame(Duration.millis(300), kvDn);

            // add keyframes
            timeline.getKeyFrames().addAll(kfUp, kfDn);

            timeline.setOnFinished(e -> isJumping = false);
            timeline.play();
        }
    }

    //Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getPoints() {
        return points;
    }

    public int getTimeSurvived() {
        return timeSurvived;
    }

    public ImageView getSprite() {
        return sprite;
    }
}
