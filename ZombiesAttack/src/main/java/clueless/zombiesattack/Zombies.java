package clueless.zombiesattack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Zombies extends Characters{
    // Attributes
    private int type;
    private ImageView sprite;

    // Contructor

    public Zombies (int x,int y,int type, int height, int width, int positionX, int positionY, int life, int speed, int strength){
        super(height, width, positionX, positionY, life, speed, strength);
        this.type = type;
        Image img = new Image("zombie1.gif");
        this.sprite = new ImageView(img);
        sprite.setX(x);
        sprite.setY(y);
        sprite.setFitWidth(100);
        sprite.setFitHeight(100);
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

    public ImageView getSprite() {
        return sprite;
    }
}