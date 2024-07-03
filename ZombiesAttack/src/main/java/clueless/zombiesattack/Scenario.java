package clueless.zombiesattack;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Scenario {

    // Properties
    private ImageView background;
    private Rectangle plataform;

    // Constructor
    public Scenario(ImageView background, Object plataform) {
        this.background = background;
        this.plataform = new Rectangle(620,100,Color.ANTIQUEWHITE);
    }
}
