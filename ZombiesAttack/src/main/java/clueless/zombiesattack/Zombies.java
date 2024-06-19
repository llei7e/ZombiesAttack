package clueless.zombiesattack;

import javafx.scene.image.Image;

public class Zombies extends Characters{
    // Attributes
    private int type;

    // Contructor

    public Zombies (int type, int height, int width, int positionX, int positionY, int life, int speed, int strength){
        super(height, width, positionX, positionY, life, speed, strength);
        this.type = type;
    }


    //Methods

    public void chasing(Object player, Object zombie){
        //Create method to chase player (wating for moves to be ready);
    }


    // Getters and Setters
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}