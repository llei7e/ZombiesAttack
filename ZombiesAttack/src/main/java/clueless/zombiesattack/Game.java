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
    private boolean right = false;
    private boolean left = false;
    private boolean shotting = false;
    private String direction = "right";

    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, width, height);
        Rectangle rect = new Rectangle( 100, 20, Color.BISQUE);
        Menu screen = new Menu();

        pane.getChildren().add(rect);

        // Instance player
        Image playerImg= new Image("brick.png");
        Player p1 = new Player(10,10,width/2, height/2,10,5,10, playerImg);
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                // check if keyboard has been pressed
                scene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.D){
                        right = true;
                        left = false;
                        direction = "right";
                    }
                    if (event.getCode() == KeyCode.A){
                        right = false;
                        left = true;
                        direction = "left";
                    }
                    if (event.getCode() == KeyCode.SPACE)
                        p1.jump();
                    if (event.getCode() == KeyCode.J){
                        p1.attack(direction);
                        shotting = true;
                        System.out.println("tiro apertado");

                    }
                });
                // check if keyboard has been released
                scene.setOnKeyReleased(event -> {
                    if (event.getCode() == KeyCode.D)
                        right = false;
                    if (event.getCode() == KeyCode.A)
                        left = false;
                    if (event.getCode() == KeyCode.J){
                        shotting = false;
                        System.out.println("tiro liberado");
                    }

                });
                p1.move(right, left);

            }
        }.start();

        screen.homeScreen(scene, pane, primaryStage);

        // configurando "primaryStage"
        primaryStage.setTitle("Zombies Attack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}