package clueless.zombiesattack;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player extends Characters {
    //Attributes
    private String name;
    private int points;
    private String weapon;
    private boolean isJumping = false;
    private boolean isAttacking = false;
    private String direction = "right";

    //Constructor
    public Player() {
        super();

        //define stats
        this.name = "";
        this.points = 0;
        this.weapon = "knife";
        setLife(10);
        setSpeed(5);


        //define sprites
        playerWeapons();
        this.getSprite().setX(310);
        this.getSprite().setY(390 + 80 - this.getSprite().getFitHeight());
    }

    //Methods

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


            img = new Image("knifewalk-right2.png");
            this.setSprite(img, getWeapon());

            setStrength(1);

        }

        if (Objects.equals(this.getWeapon(), "katana")) {

            // right
            this.walking[0] = new Image("katanawalk-right1.png");
            this.walking[1] = new Image("katanawalk-right2.png");
            this.walking[2] = new Image("katanawalk-right3.png");
            // left
            this.walking[3] = new Image("katanawalk-left1.png");
            this.walking[4] = new Image("katanawalk-left2.png");
            this.walking[5] = new Image("katanawalk-left3.png");

            Sounds.getKatana().play();

            img = new Image("katanawalk-right2.png");
            this.setSprite(img, getWeapon());

            setStrength(1.2);

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

            Sounds.getPistol(0).play();

            img = new Image("rickwalk2-right.png");
            this.setSprite(img, getWeapon());

            setStrength(1.2);
        }

        if (Objects.equals(this.getWeapon(), "rifle")) {

            // right
            this.walking[0] = new Image("riflewalk-right1.png");
            this.walking[1] = new Image("riflewalk-right2.png");
            this.walking[2] = new Image("riflewalk-right3.png");
            // left
            this.walking[3] = new Image("riflewalk-left1.png");
            this.walking[4] = new Image("riflewalk-left2.png");
            this.walking[5] = new Image("riflewalk-left3.png");

            Sounds.getRifle(0).play();

            img = new Image("riflewalk-right2.png");
            this.setSprite(img, getWeapon());

            setStrength(1.5);
        }
    }


    // This method is the main one for attack
    public void attack(Pane pane, ArrayList<Zombies> zombies) {
        // Instances
        Timeline timeline = new Timeline();
        KeyFrame kf;

        if (!isAttacking) {

            // projectile instance and settings
            ImageView projectile;

            // if right
            if (Objects.equals(direction, "right")) {

                // player sprite right
                if (Objects.equals(getWeapon(), "knife")) {
                    this.setSprite(new Image("knife-attack-right.png"), getWeapon(), this.isAttacking);

                    projectile = new ImageView(new Image("slash.png"));

                    projectile.setFitHeight(20);
                    projectile.setFitWidth(25);
                    projectile.setY(getSprite().getY() + getSprite().getFitHeight() / 2 - 8);

                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() + 250);
                    kf = new KeyFrame(Duration.millis(800), kv);

                    // define bullet X
                    projectile.setX(getSprite().getX() + 65);

                } else if (Objects.equals(getWeapon(), "katana")) {
                    this.setSprite(new Image("katana-attack-right.png"), getWeapon(), this.isAttacking);

                    projectile = new ImageView(new Image("katana-slash.png"));

                    projectile.setFitHeight(80);
                    projectile.setFitWidth(50);
                    projectile.setY(getSprite().getY() + getSprite().getFitHeight() / 2 - 40);

                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() + 350);
                    kf = new KeyFrame(Duration.millis(600), kv);

                    // define bullet X
                    projectile.setX(getSprite().getX() + 60);

                } else if (Objects.equals(getWeapon(), "pistol")) {
                    this.setSprite(new Image("pistolShooting1-right.png"), getWeapon(), this.isAttacking);

                    projectile = new ImageView(new Image("bullet1.png"));

                    projectile.setFitHeight(25);
                    projectile.setFitWidth(25);
                    projectile.setY(getSprite().getY() + getSprite().getFitHeight() / 2 - 20);

                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() + 350);
                    kf = new KeyFrame(Duration.millis(400), kv);

                    // define bullet X
                    projectile.setX(getSprite().getX() + 50);

                } else {
                    this.setSprite(new Image("rifle-attack-right.png"), getWeapon(), this.isAttacking);

                    projectile = new ImageView(new Image("rifle-bullet.png"));

                    projectile.setFitHeight(25);
                    projectile.setFitWidth(50);
                    projectile.setY(getSprite().getY() + getSprite().getFitHeight() / 2 - 15);

                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() + 550);
                    kf = new KeyFrame(Duration.millis(800), kv);

                    // define bullet X
                    projectile.setX(getSprite().getX() + 75);
                }

            } else {
                // player sprite left
                if (Objects.equals(getWeapon(), "knife")) {
                    this.setSprite(new Image("knife-attack-left.png"), getWeapon(), this.isAttacking);

                    projectile = new ImageView(new Image("slash-left.png"));

                    projectile.setFitHeight(20);
                    projectile.setFitWidth(25);
                    projectile.setY(getSprite().getY() + getSprite().getFitHeight() / 2 - 8);

                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() - 200);
                    kf = new KeyFrame(Duration.millis(800), kv);

                    // define projectile X
                    projectile.setX(getSprite().getX() - 20);

                } else if (Objects.equals(getWeapon(), "katana")) {
                    this.setSprite(new Image("katana-attack-left.png"), getWeapon(), this.isAttacking);

                    projectile = new ImageView(new Image("katana-slash-left.png"));

                    projectile.setFitHeight(80);
                    projectile.setFitWidth(50);
                    projectile.setY(getSprite().getY() + getSprite().getFitHeight() / 2 - 40);

                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() - 300);
                    kf = new KeyFrame(Duration.millis(600), kv);

                    // define projectile X
                    projectile.setX(getSprite().getX() - 20);

                } else if (Objects.equals(getWeapon(), "pistol")) {
                    this.setSprite(new Image("pistolShooting1-left.png"), getWeapon(), this.isAttacking);

                    projectile = new ImageView(new Image("bullet1.png"));

                    projectile.setFitHeight(25);
                    projectile.setFitWidth(25);
                    projectile.setY(getSprite().getY() + getSprite().getFitHeight() / 2 - 20);

                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() - 300);
                    kf = new KeyFrame(Duration.millis(400), kv);

                    // define projectile X
                    projectile.setX(getSprite().getX());

                } else {
                    this.setSprite(new Image("rifle-attack-left.png"), getWeapon(), this.isAttacking);

                    projectile = new ImageView(new Image("rifle-bullet-left.png"));

                    projectile.setFitHeight(25);
                    projectile.setFitWidth(50);
                    projectile.setY(getSprite().getY() + getSprite().getFitHeight() / 2 - 15);

                    KeyValue kv = new KeyValue(projectile.xProperty(), getSprite().getX() - 500);
                    kf = new KeyFrame(Duration.millis(800), kv);
                    // define projectile X
                    projectile.setX(getSprite().getX() - 25);
                }
            }

            // CollisionChecker
            AnimationTimer collisionChecker = new AnimationTimer() {

                @Override
                public void handle(long now) {
                    // List of zombies to remove
                    List<Zombies> toRemove = new ArrayList<>();

                    // check if collide with zombie
                    for (Zombies z : zombies) {
                        if (projectile.getBoundsInParent().intersects(z.getSprite().getBoundsInParent())) {
                            z.takeHit();
                            // define direction
                            if (Objects.equals(direction, "right")) {
                                if (Objects.equals(getWeapon(), "knife")) {
                                    setSprite(new Image("knifewalk-right2.png"), getWeapon());
                                } else if (Objects.equals(getWeapon(), "katana")) {
                                    setSprite(new Image("katanawalk-right2.png"), getWeapon());
                                } else if (Objects.equals(getWeapon(), "pistol")) {
                                    setSprite(new Image("rickwalk2-right.png"), getWeapon());
                                } else {
                                    setSprite(new Image("riflewalk-right2.png"), getWeapon());
                                }
                            } else {
                                if (Objects.equals(getWeapon(), "knife")) {
                                    setSprite(new Image("knifewalk-left2.png"), getWeapon());
                                } else if (Objects.equals(getWeapon(), "katana")) {
                                    setSprite(new Image("katanawalk-left2.png"), getWeapon());
                                } else if (Objects.equals(getWeapon(), "pistol")) {
                                    setSprite(new Image("rickwalk2-left.png"), getWeapon());
                                } else {
                                    setSprite(new Image("riflewalk-left2.png"), getWeapon());
                                }
                            }

                            // Set zombie life
                            z.setLife((int)(z.getLife() - getStrength()));

                            if (z.getLife() <= getStrength()) {
                                // Update points
                                if (z.getType() == 1) {
                                    setPoints(getPoints() + 20);
                                } else if (z.getType() == 3) {
                                    setPoints(getPoints() + 30);
                                } else if (z.getType() == 2) {
                                    setPoints(getPoints() + 10);
                                }

                                // zombie sound
                                Sounds.getZombieGrowl().play();

                                // Add zombie to remove List
                                toRemove.add(z);
                            }

                            // Stop timeline and remove projectile
                            timeline.stop();
                            pane.getChildren().remove(projectile);
                            this.stop();
                        }
                    }

                    // Remove zombies collected after loop
                    for (Zombies z : toRemove) {
                        pane.getChildren().remove(z.getSprite());
                        zombies.remove(z);
                    }

                }
            };

            // add keyFrame to timeline instance
            timeline.getKeyFrames().add(kf);

            // add projectile sprite to Pane
            pane.getChildren().add(projectile);


            // remove projectile
            timeline.setOnFinished(e -> {
                pane.getChildren().remove(projectile);
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
            Sounds.getJumping().play();
            timeline.play();
        }
    }
    // check-healing

    public AnimationTimer checkHealing(ImageView healing, Pane pane) {
        return new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (healing.getBoundsInParent().intersects(getSprite().getBoundsInParent())) {
                    pane.getChildren().remove(healing);
                    Sounds.getHealing().play();
                    // set new life
                    if (getLife() > 8)
                        setLife(10); // regenerates life
                    else
                        setLife(getLife() + 2);

                    this.stop();
                }
            }
        };

    }

//    Animation End Game
    public void animationEndgame(Scene scene, Pane pane, Stage stage) {
        PauseTransition endGame = new PauseTransition(Duration.millis(1700));

        Sounds.getGameOver().play();
        setSprite(new Image("rickEndGame.gif"));
        endGame.setOnFinished(e -> Menu.gameOver(scene, pane, stage, this));
        endGame.play();
    }

// WeaponSfx
    public void weaponSfx () {
        switch (getWeapon()){
            case "knife":
                Sounds.getKnife().play();
                break;
            case "katana":
                Sounds.getKatana(isRight()).play();
                break;
            case "pistol":
                Sounds.getPistol(1).play();
                break;
            default:
                Sounds.getRifle(1).play();
                break;
        }
    }

//  Dash
    public void hit (Zombies zombie){
        KeyValue kv;
        int hit;

        // hit type
        if (zombie.getType() == 3)
            hit = 75;
        else
            hit = 50;

        // attack displacement
        if (zombie.isLeft())
            hit *= -1;

        kv = new KeyValue(this.getSprite().xProperty(), this.getSprite().getX()+hit, Interpolator.EASE_BOTH);
        Timeline hitDisplac = new Timeline(new KeyFrame(Duration.millis(170), kv));
        hitDisplac.play();

    }

//    Getters

    public String getName() {
        return name;
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

    public void setDirection(String dir) {
        this.direction = dir;
    }

    public void setShooting(boolean isShooting) {
        this.isAttacking = isShooting;
    }
}
