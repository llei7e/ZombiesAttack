package clueless.zombiesattack;

public class Player extends Characters{
    //Attributes
    private String name;
    private int points;
    private int timeSurvived;
    private String weapon;

    //Constructor
    public Player(Object image){
        this.name = "";
        this.points = 0;
        this.timeSurvived = 0;
        this.weapon = "knife";
    }

    //Methods
    public void dash(){

    }

    public void attack(){

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
