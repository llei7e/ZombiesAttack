package clueless.zombiesattack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import kotlin.Unit;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Game extends Application {
    private int width = 620;
    private int height = 620;

    public void start(Stage primaryStage){
        Pane pane = new Pane();
        Scene scene = new Scene(pane, width, height);




        // configurando "primaryStage"
        primaryStage.setTitle("Zombies Attack");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}