package clueless.zombiesattack;

import javafx.scene.image.Image;

public class Zombies extends Characters{
    // Attributes
    private final int type;

    // Contructor
    public Zombies (int positionX, int type){
        super();
        this.type = type;
        Image img;
        if(type == 1) {
            //define sprites
            img = new Image("zombieP-walking2.png");
            this.setSprite(img, type);
            this.getSprite().setX(positionX);
            this.getSprite().setY(390 + 80 - this.getSprite().getFitHeight());
            // right
            this.walking[0] = new Image("zombieP-walkright1.png");
            this.walking[1] = new Image("zombieP-walkright2.png");
            this.walking[2] = new Image("zombieP-walkright3.png");
            // left
            this.walking[3] = new Image("zombieP-walking1.png");
            this.walking[4] = new Image("zombieP-walking2.png");
            this.walking[5] = new Image("zombieP-walking3.png");


            //define stats
            setLife(3);
            setSpeed(3);
            setStrength(2);

        }else if(type == 3) {
            //define sprites
            img = new Image("zombieG-walking2.png");
            this.setSprite(img, type);
            this.getSprite().setX(positionX);
            this.getSprite().setY(390 + 80 - this.getSprite().getFitHeight());
            // right
            this.walking[0] = new Image("zombieG-walkright1.png");
            this.walking[1] = new Image("zombieG-walkright2.png");
            this.walking[2] = new Image("zombieG-walkright3.png");
            // left
            this.walking[3] = new Image("zombieG-walking1.png");
            this.walking[4] = new Image("zombieG-walking2.png");
            this.walking[5] = new Image("zombieG-walking3.png");

            //define stats
            setLife(3);
            setSpeed(1);
            setStrength(3);

        }else{
            //define sprites
            img = new Image("zombieM-walking2.png");
            this.setSprite(img, type);
            this.getSprite().setX(positionX);
            this.getSprite().setY(390 + 80 - this.getSprite().getFitHeight());
            // right
            this.walking[0] = new Image("zombieM-walkright1.png");
            this.walking[1] = new Image("zombieM-walkright2.png");
            this.walking[2] = new Image("zombieM-walkright3.png");
            // left
            this.walking[3] = new Image("zombieM-walking1.png");
            this.walking[4] = new Image("zombieM-walking2.png");
            this.walking[5] = new Image("zombieM-walking3.png");

            //define stats
            setLife(2);
            setSpeed(2);
            setStrength(1);
        }
    }


    //Methods
    public void chasing(Player player, Zombies zombie, int frame){
        if((zombie.getSprite().getX() - player.getSprite().getX()) < 0){
            setRight(true);
            setLeft(false);
        }
        if (zombie.getSprite().getX() - player.getSprite().getX() > 0){
            setRight(false);
            setLeft(true);
        }
        zombie.move(frame, this.getType());
    }

    // Getters
    public int getType() {
        return type;
    }

}