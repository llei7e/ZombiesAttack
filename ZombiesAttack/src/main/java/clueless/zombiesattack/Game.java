package clueless.zombiesattack;

import com.almasb.fxgl.dsl.FXGL;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
    private int width = 620;
    private int height = 620;
    private static final double GRAVITY = 0.1; // Gravidade
    private double velocityY = 0; // Velocidade vertical


    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, width, height);
        Rectangle rect = new Rectangle( 100, 20, Color.BISQUE);

        pane.getChildren().add(rect);

        // Iniciar o loop de atualização

        /* refactor */
        new AnimationTimer() {
            
            @Override
            public void handle(long now) {
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.D){

                    }
                });
            }
        }.start();

        Player p1 = new Player(10,10,width/2, height/2,10,10,10);
        Zombies z1 =  new Zombies(160,400,10,width/2, height/2,10,10,10,10,10);
        Zombies z2 =  new Zombies(100,400,10,width/2, height/2,10,10,10,10,10);
        Zombies z3 =  new Zombies(250,400,10,width/2, height/2,10,10,10,10,10);

        Image img = new Image("fundo.png");
        ImageView fundo = new ImageView(img);
        fundo.setFitWidth(620);
        fundo.setFitHeight(620);
        pane.getChildren().add(fundo);

        pane.getChildren().add(p1.getSprite());
        pane.getChildren().add(z1.getSprite());
        pane.getChildren().add(z2.getSprite());
        pane.getChildren().add(z3.getSprite());

        // configurando "primaryStage"
        primaryStage.setTitle("Zombies Attack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}