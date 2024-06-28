package clueless.zombiesattack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
    private int width = 620;
    private int height = 620;


    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, width, height);
        Menu screen = new Menu();

        screen.game(scene, pane, primaryStage);

        // configurando "primaryStage"
        primaryStage.setTitle("Zombies Attack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}