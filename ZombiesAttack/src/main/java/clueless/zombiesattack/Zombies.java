package clueless.zombiesattack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Zombies extends Characters{
    // Attributes
    private int type;

    // Contructor
    public Zombies (int height, int width, int positionX, int positionY, int life, int speed, int strength, int type, Image img){
        super(height, width, positionX, positionY, life, speed, strength, img);
        if(type == 1) {
            img = new Image("zombieP.png");
            this.setSprite(img);
            // right
            this.walking[0] = new Image("zombieP-walkright1.png");
            this.walking[1] = new Image("zombieP-walkright2.png");
            this.walking[2] = new Image("zombieP-walkright3.png");
            // left
            this.walking[3] = new Image("zombieP-walking1.png");
            this.walking[4] = new Image("zombieP-walking2.png");
            this.walking[5] = new Image("zombieP-walking3.png");
        }else if(type == 3) {
            img = new Image("zombieG.png");
            this.setSprite(img);
            // right
            this.walking[0] = new Image("zombieP-walkright1.png");
            this.walking[1] = new Image("zombieP-walkright2.png");
            this.walking[2] = new Image("zombieP-walkright3.png");
            // left
            this.walking[3] = new Image("zombieP-walking1.png");
            this.walking[4] = new Image("zombieP-walking2.png");
            this.walking[5] = new Image("zombieP-walking3.png");
        }else{
            img = new Image("zombieM.png");
            this.setSprite(img);
            // right
            this.walking[0] = new Image("zombieP-walkright1.png");
            this.walking[1] = new Image("zombieP-walkright2.png");
            this.walking[2] = new Image("zombieP-walkright3.png");
            // left
            this.walking[3] = new Image("zombieP-walking1.png");
            this.walking[4] = new Image("zombieP-walking2.png");
            this.walking[5] = new Image("zombieP-walking3.png");
        }
        this.sprite.setX(300);
        this.sprite.setY(390);
    }


    //Methods
    public void chasing(Player player, Zombies zombie, int frame){
        if((zombie.sprite.getX() - player.sprite.getX()) < 0){
            setRight(true);
            setLeft(false);
        }
        if (zombie.sprite.getX() - player.sprite.getX() > 0){
            setRight(false);
            setLeft(true);
        }
        zombie.move(frame);
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