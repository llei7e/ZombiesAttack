package clueless.zombiesattack;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    private boolean isAttacking = false;
    private String direction = "right";
    private Image[] shooting = new Image[2];


    //Constructor
    public Player() {
        super();

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
        this.getSprite().setX(310);
        this.getSprite().setY(390 + 80 - this.getSprite().getFitHeight());
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

        if (!isAttacking) {
            // define isWalking
            isWalking = isRight() || isLeft();

            // projectile instance and settings
            ImageView projectile;

            if (Objects.equals(getWeapon(), "knife")) {
                projectile = new ImageView(new Image("slash.png"));
            } else {
                projectile = new ImageView(new Image("bullet1.png"));
            }

            projectile.setFitHeight(25);
            projectile.setFitWidth(25);
            projectile.setY(getSprite().getY() + getSprite().getFitHeight() / 2 - 20);

            // Instances
            Timeline timeline = new Timeline();
            KeyFrame kf;

            // CollisionChecker
            AnimationTimer collisionChecker = new AnimationTimer() {

                @Override
                public void handle(long now) {
                    // check if  collide with zombie
                    for (Zombies z : zombies)
                        if (projectile.getBoundsInParent().intersects(z.getSprite().getBoundsInParent())) {
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
                                pane.getChildren().remove(z.getSprite());
                            }

                            timeline.stop(); // stop timeline if yes
                            pane.getChildren().remove(projectile);// remove projectile from pane
                            this.stop(); // stop collisionChecker
                        }
                }
            };

            // if right
            if (Objects.equals(direction, "right")) {
                // player sprite right
                if (Objects.equals(getWeapon(), "knife")) {
                    this.setSprite(new Image("knife-attack-right.png"), getWeapon(), this.isAttacking);
                } else {
                    this.setSprite(new Image("pistolShooting1-right.png"), getWeapon(), this.isAttacking);
                }
                // define bullet X
                projectile.setX(getSprite().getX() + 50);

                if (Objects.equals(getWeapon(), "knife")) {
                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() + 250);
                    kf = new KeyFrame(Duration.millis(800), kv);
                } else {
                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() + 350);
                    kf = new KeyFrame(Duration.millis(400), kv);
                }

            } else {
                // player sprite left
                if (Objects.equals(getWeapon(), "knife")) {
                    this.setSprite(new Image("knife-attack-left.png"), getWeapon(), this.isAttacking);
                } else {
                    this.setSprite(new Image("pistolShooting1-left.png"), getWeapon(), this.isAttacking);
                }
                // define projectile X
                projectile.setX(getSprite().getX());
                if (Objects.equals(getWeapon(), "knife")) {
                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() - 200);
                    kf = new KeyFrame(Duration.millis(800), kv);
                } else {
                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() - 300);
                    kf = new KeyFrame(Duration.millis(400), kv);
                }
            }
            // add keyFrame to timeline instance
            timeline.getKeyFrames().add(kf);

            // add projectile sprite to Pane
            pane.getChildren().add(projectile);
            // remove projectile
            timeline.setOnFinished(e -> {
                pane.getChildren().remove(projectile);
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
            // Jumping
            this.isJumping = true;

            // define logic of gravity
            Timeline timeline = new Timeline();
            timeline.setCycleCount(2);
            timeline.setAutoReverse(true);

            // Up effect
            KeyValue kvUp = new KeyValue(getSprite().yProperty(), getSprite().getY() - 150, Interpolator.EASE_BOTH);
            KeyFrame kfUp = new KeyFrame(Duration.millis(300), kvUp);
            // Down effect
            KeyValue kvDn = new KeyValue(getSprite().yProperty(), getSprite().getY(), Interpolator.EASE_BOTH);
            KeyFrame kfDn = new KeyFrame(Duration.millis(300), kvDn);

            // add keyframes
            timeline.getKeyFrames().addAll(kfUp, kfDn);

            // end of jump
            timeline.setOnFinished(e -> isJumping = false);
            timeline.play();
        }
    }
    // check-healing

    public void checkHealing(ImageView cure, Pane pane) {
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (cure.getBoundsInParent().intersects(getSprite().getBoundsInParent())) {
                    pane.getChildren().remove(cure);

                    // verify
                    if (getLife() > 8)
                        setLife(10); // regenerates life
                    else
                        setLife(getLife()+2);
                }
            }
        }.start();



    }


//    Getters

    public String getName() {
        return name;
    }

    public int getTimeSurvived() {
        return timeSurvived;
    }

    public String getWeapon() {
        return weapon;
    }

    public int getPoints() {
        return points;
    }

    public boolean getShooting() {
        return this.isAttacking;
    }


//    Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
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
        this.isAttacking = isShooting;
    }
}
