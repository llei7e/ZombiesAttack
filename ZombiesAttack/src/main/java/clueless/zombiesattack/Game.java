package clueless.zombiesattack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
    private final int WindowWidth = 620;
    private final int WindowHeight = 620;


    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WindowWidth, WindowHeight);
        Menu screen = new Menu();
        Ranking ranking = new Ranking();
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