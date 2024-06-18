package clueless.zombiesattack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Characters {
    //Attributes
    private String name;
    private int points;
    private int timeSurvived;
    private String weapon;


    //Constructor
    public Player(int height, int width, int positionX, int positionY, int life, int speed, int strength){
        super(height, width, positionX, positionY, life, speed, strength);
        this.name = "";
        this.points = 0;
        this.timeSurvived = 0;
        this.weapon = "knife";
    }


    //Methods
    public int dash(int positionX, int positionY, boolean doubleClick){
        int dashSize = 3;
        int newX;
        return newX;
    }

    public void attack(int positionX, int positionY){
        Image attack = new Image("");
        Image hit = new Image("");
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
}
