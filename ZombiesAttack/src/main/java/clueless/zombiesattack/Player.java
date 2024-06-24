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
        this.sprite = new ImageView(img);
        this.sprite.setX(0);
        this.sprite.setY(390);
        this.sprite.setFitWidth(80);
        this.sprite.setFitHeight(100);
        this.name = "";
        this.points = 0;
        this.timeSurvived = 0;
        this.weapon = "knife";
        this.isJumping = false;
        this.walking[0] = new Image("rickAnda-1.png");
        this.walking[1] = new Image("rickAnda-2.png");
        this.walking[2] = new Image("rickAnda-3.png");

    }
    

    //Methods
    public int dash(int positionX, int positionY, boolean doubleClick){
        int dashSize = 3;
        //   int newX;
        return 0;
    }

    public void attack(String dir, Pane pane){
        // Bullet instance and settings
        ImageView bullet = new ImageView(new Image("bullet1.png"));
        bullet.setFitHeight(25);
        bullet.setFitWidth(25);
        bullet.setY(sprite.getY() + sprite.getFitHeight()/2-20);

        // Timeline instance
        Timeline timeline = new Timeline();

        // define KeyFrame direction
        KeyFrame kf;
        if(Objects.equals(dir, "right")) {
            bullet.setX(sprite.getX()+50);
            KeyValue kv = new KeyValue(bullet.xProperty(),620);
            kf = new KeyFrame(Duration.millis(500), kv);
        }
        else {
            bullet.setX(sprite.getX()+50);
            KeyValue kv = new KeyValue(bullet.xProperty(),0);
            kf = new KeyFrame(Duration.millis(500), kv);
        }
        // add keyFrame to timeline instance
        timeline.getKeyFrames().add(kf);
        // add bullet sprite to Pane
        pane.getChildren().add(bullet);
        // remove bullet
        timeline.setOnFinished(e -> pane.getChildren().remove(bullet));
        timeline.play();
    }

    //
    public void attack (String dir, Pane pane, boolean shooting) {
        if (!shooting) {
            // defines the sprite
            this.setSprite(new Image("rickgrimes-shot.gif"));
            // uses another function overload
            attack(dir, pane);
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
