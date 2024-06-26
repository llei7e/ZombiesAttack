package clueless.zombiesattack;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
        this.sprite.setX(0);
        this.sprite.setY(390);
        this.setSprite(img);
        this.name = "";
        this.points = 0;
        this.timeSurvived = 0;
        this.weapon = "knife";
        this.isJumping = false;
        // right
        this.walking[0] = new Image("rickwalk1-right.png");
        this.walking[1] = new Image("rickwalk2-right.png");
        this.walking[2] = new Image("rickwalk3-right.png");
        // left
        this.walking[3] = new Image("rickwalk1-left.png");
        this.walking[4] = new Image("rickwalk2-left.png");
        this.walking[5] = new Image("rickwalk3-left.png");

    }
    

    //Methods
    public int dash(int positionX, int positionY, boolean doubleClick){
        int dashSize = 3;
        //   int newX;
        return 0;
    }

    // This method is the main one for attack
    public void attack(String dir, Pane pane, boolean shooting){
        if (!shooting) {
            // Bullet instance and settings
            ImageView bullet = new ImageView(new Image("bullet1.png"));
            bullet.setFitHeight(25);
            bullet.setFitWidth(25);
            bullet.setY(sprite.getY() + sprite.getFitHeight() / 2 - 20);

            // Timeline instance
            Timeline timeline = new Timeline();

            // define KeyFrame direction
            KeyFrame kf;
            if (Objects.equals(dir, "right")) {
                // player sprite right
                this.setSprite(new Image("pistolShooting1-right.png"));
                // define bullet X
                bullet.setX(sprite.getX() + 50);
                KeyValue kv = new KeyValue(bullet.xProperty(), sprite.getX() + 350);
                kf = new KeyFrame(Duration.millis(400), kv);
            } else {
                // player sprite left
                this.setSprite(new Image("pistolShooting1-left.png"));

                bullet.setX(sprite.getX());
                KeyValue kv = new KeyValue(bullet.xProperty(),sprite.getX() - 300);
                kf = new KeyFrame(Duration.millis(400), kv);
            }
            // add keyFrame to timeline instance
            timeline.getKeyFrames().add(kf);

            // add bullet sprite to Pane
            pane.getChildren().add(bullet);

            // remove bullet
            timeline.setOnFinished(e -> {
                pane.getChildren().remove(bullet);
                if (Objects.equals(dir, "right"))
                    this.setSprite(new Image("rickwalk2-right.png"));
                else
                    this.setSprite(new Image("rickwalk2-left.png"));
            });

            timeline.play();
        }
    }

    public void jump() {
        if(!isJumping) {
            // On jumping
            this.isJumping = true;

            // define logic of gravity
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
            // end of jump
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

    public void setPoints(int points) {
        this.points = points;
    }

    public void setTimeSurvived(int timeSurvived) {
        this.timeSurvived = timeSurvived;
    }
}
