package clueless.zombiesattack;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class Player extends Characters {
    //Attributes
    private String name;
    private int points;
    private int timeSurvived;
    private String weapon;
    private boolean isJumping = false;
    private boolean isWalking = false;
    private boolean isShooting = false;
    private String direction = "right";
    private Image[] shooting = new Image[2];


    //Constructor
    public Player(int height, int width, int positionX, int positionY) {
        super(height, width, positionX, positionY);

        //define stats
        this.name = "";
        this.points = 0;
        this.timeSurvived = 0;
        this.weapon = "knife";
        setLife(10);
        setSpeed(5);
        setStrength(1);

        //define sprites
        playerWeapons();
        this.sprite.setX(310);
        this.sprite.setY(390 + 80 - this.sprite.getFitHeight());
    }

    //Methods
    public int dash(int positionX, int positionY, boolean doubleClick) {
        int dashSize = 3;
        //   int newX;
        return 0;
    }

    public void playerWeapons() {
        Image img;

        if (Objects.equals(this.getWeapon(), "knife")) {

            // right
            this.walking[0] = new Image("knifewalk-right1.png");
            this.walking[1] = new Image("knifewalk-right2.png");
            this.walking[2] = new Image("knifewalk-right3.png");
            // left
            this.walking[3] = new Image("knifewalk-left1.png");
            this.walking[4] = new Image("knifewalk-left2.png");
            this.walking[5] = new Image("knifewalk-left3.png");
            // shooting
            this.shooting[0] = new Image("knife-attack-right.png");
            this.shooting[1] = new Image("knifewalk-right2.png");

            img = new Image("knifewalk-right2.png");
            this.setSprite(img, getWeapon());

        }

        if (Objects.equals(this.getWeapon(), "pistol")) {

            // right
            this.walking[0] = new Image("rickwalk1-right.png");
            this.walking[1] = new Image("rickwalk2-right.png");
            this.walking[2] = new Image("rickwalk3-right.png");
            // left
            this.walking[3] = new Image("rickwalk1-left.png");
            this.walking[4] = new Image("rickwalk2-left.png");
            this.walking[5] = new Image("rickwalk3-left.png");
            // shooting
            this.shooting[0] = new Image("pistolShooting1-right.png");
            this.shooting[1] = new Image("pistol-shot-walk-right.png");

            img = new Image("rickwalk2-right.png");
            this.setSprite(img, getWeapon());
        }
    }


    // This method is the main one for attack
    public void attack(Pane pane, ArrayList<Zombies> zombies) {

        if (!isShooting) {
            // define isWalking
            isWalking = isRight() || isLeft();
            // Bullet instance and settings
            ImageView bullet;
            if (Objects.equals(getWeapon(), "knife")) {
                bullet = new ImageView(new Image("slash.png"));
            } else {
                bullet = new ImageView(new Image("bullet1.png"));
            }

            bullet.setFitHeight(25);
            bullet.setFitWidth(25);
            bullet.setY(sprite.getY() + sprite.getFitHeight() / 2 - 20);

            // Timeline instance
            Timeline timeline = new Timeline();
            // CollisionChecker
            AnimationTimer collisionChecker = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    // check if bullet collide with zombie
                    for (Zombies z : zombies)
                        if (bullet.getBoundsInParent().intersects(z.sprite.getBoundsInParent())) {
                            // define direction
                            if (Objects.equals(direction, "right")) {
                                if (Objects.equals(getWeapon(), "knife")) {
                                    setSprite(new Image("knifewalk-right2.png"), getWeapon());
                                } else {
                                    setSprite(new Image("rickwalk2-right.png"), getWeapon());
                                }
                            } else {
                                if (Objects.equals(getWeapon(), "knife")) {
                                    setSprite(new Image("knifewalk-left2.png"), getWeapon());
                                } else {
                                    setSprite(new Image("rickwalk2-left.png"), getWeapon());
                                }
                            }
                            // Remove zombie from zombies ArrayList
                            // refactor
                            z.setLife(z.getLife() - getStrength());
                            if (z.getLife() <= getStrength()) {
                                pane.getChildren().remove(z.sprite);

                            }
                            timeline.stop(); // stop timeline if yes
                            pane.getChildren().remove(bullet);// remove bullet from pane
                            this.stop(); // stop collisionChecker
                        }
                }
            };
            // define KeyFrame direction
            KeyFrame kf;
            // if right
            if (Objects.equals(direction, "right")) {
                // player sprite right
                if (Objects.equals(getWeapon(), "knife")) {
                    this.setSprite(new Image("knife-attack-right.png"), getWeapon(), this.isShooting);
                } else {
                    this.setSprite(new Image("pistolShooting1-right.png"), getWeapon(), this.isShooting);
                }
                // define bullet X
                bullet.setX(sprite.getX() + 50);

                if (Objects.equals(getWeapon(), "knife")) {
                    KeyValue kv = new KeyValue(bullet.xProperty(), sprite.getX() + 250);
                    kf = new KeyFrame(Duration.millis(800), kv);
                } else {
                    KeyValue kv = new KeyValue(bullet.xProperty(), sprite.getX() + 350);
                    kf = new KeyFrame(Duration.millis(400), kv);
                }

            } else {
                // player sprite left
                if (Objects.equals(getWeapon(), "knife")) {
                    this.setSprite(new Image("knife-attack-left.png"), getWeapon(), this.isShooting);
                } else {
                    this.setSprite(new Image("pistolShooting1-left.png"), getWeapon(), this.isShooting);
                }
                // define bullet X
                bullet.setX(sprite.getX());
                if (Objects.equals(getWeapon(), "knife")) {
                    KeyValue kv = new KeyValue(bullet.xProperty(), sprite.getX() - 200);
                    kf = new KeyFrame(Duration.millis(800), kv);
                } else {
                    KeyValue kv = new KeyValue(bullet.xProperty(), sprite.getX() - 300);
                    kf = new KeyFrame(Duration.millis(400), kv);
                }
            }
            // add keyFrame to timeline instance
            timeline.getKeyFrames().add(kf);

            // add bullet sprite to Pane
            pane.getChildren().add(bullet);
            // remove bullet
            timeline.setOnFinished(e -> {
                pane.getChildren().remove(bullet);
                if (Objects.equals(direction, "right")) {
                    if (Objects.equals(this.getWeapon(), "knife")) {
                        this.setSprite(new Image("knifewalk-right2.png"), getWeapon());
                    } else {
                        this.setSprite(new Image("rickwalk2-right.png"), getWeapon());
                    }
                } else {
                    if (Objects.equals(this.getWeapon(), "knife")) {
                        this.setSprite(new Image("knifewalk-left2.png"), getWeapon());
                    } else {
                        this.setSprite(new Image("rickwalk2-left.png"), getWeapon());
                    }
                }
                collisionChecker.stop();
            });

            timeline.play();
            collisionChecker.start();
        }
    }

    public void jump() {
        if (!isJumping) {
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

    public void setDirection(String dir) {
        this.direction = dir;
    }

    public void setShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public boolean getShooting() {
        return this.isShooting;
    }
}
