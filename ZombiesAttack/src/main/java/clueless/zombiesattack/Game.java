package clueless.zombiesattack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {


    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        int windowHeight = 620;
        int windowWidth = 620;
        Scene scene = new Scene(pane, windowWidth, windowHeight);
        Menu screen = new Menu();
        Ranking ranking = new Ranking();
        screen.game(scene, pane, primaryStage);

        // setting "primaryStage"
        primaryStage.setTitle("Zombies Attack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}