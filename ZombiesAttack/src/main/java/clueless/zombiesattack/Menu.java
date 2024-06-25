package clueless.zombiesattack;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Menu {

    public void homeScreen(Scene scene, Pane pane, Stage stage) {

        //Creating elements
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        HBox sprites = new HBox();

        //Buttons Instances
        Button start = new Button("START GAME");
        Button ranking = new Button("RANKING");
        Button exit = new Button("EXIT");
        ImageView logo = new ImageView("logo.png");


        //adding nodes on root
        //sprites.getChildren().addAll(indiana, zombieM,zombieS,zombieG);
        root.getChildren().addAll(logo, start, ranking, exit, sprites);
        logo.setFitWidth(620);
        logo.setFitHeight(250);

        //defining css path
        start.getStyleClass().add("homeButtons");
        start.setId("start");

        ranking.getStyleClass().add("homeButtons");
        exit.getStyleClass().add("homeButtons");
        root.getStyleClass().add("vbox");

        //set action on click
        start.setOnAction(e -> gameKeys(scene, pane, stage));
        ranking.setOnAction(e -> rankingScreen(scene, pane, stage));
        exit.setOnAction(e -> stage.close());

        //adding root on pane
        pane.getChildren().add(root);

        //using css
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
    }

    public void rankingScreen(Scene scene, Pane pane, Stage stage) {
        //Creating elements
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);

        HBox rankingSpace = new HBox();
        rankingSpace.setAlignment(Pos.CENTER);
        rankingSpace.setMinWidth(400);
        rankingSpace.setSpacing(180);

        VBox points = new VBox();
        points.setAlignment(Pos.CENTER_LEFT);
        points.setSpacing(10);
        VBox names = new VBox();
        names.setAlignment(Pos.CENTER_RIGHT);
        names.setSpacing(10);

        Text points1 = new Text("1170");
        Text name1 = new Text("law");

        Text points2 = new Text("1075");
        Text name2 = new Text("mwlaofr");

        Text points3 = new Text("777");
        Text name3 = new Text("llei7e");

        Text points4 = new Text("675");
        Text name4 = new Text("grodrigues");

        Text points5 = new Text("-");
        Text name5 = new Text("-");

        ImageView logo = new ImageView("ranking.png");

        Button back = new Button("back");

        //adding nodes on root
        points.getChildren().addAll(points1, points2, points3, points4, points5);
        names.getChildren().addAll(name1, name2, name3, name4, name5);
        rankingSpace.getChildren().addAll(points, names);

        root.getChildren().addAll(logo, rankingSpace, back);
        logo.setFitWidth(620);
        logo.setFitHeight(270);

        //defining css path
        name1.getStyleClass().add("rankingText");
        name2.getStyleClass().add("rankingText");
        name3.getStyleClass().add("rankingText");
        name4.getStyleClass().add("rankingText");
        name5.getStyleClass().add("rankingText");
        points1.getStyleClass().add("rankingText");
        points2.getStyleClass().add("rankingText");
        points3.getStyleClass().add("rankingText");
        points4.getStyleClass().add("rankingText");
        points5.getStyleClass().add("rankingText");
        root.getStyleClass().add("vbox");
        back.getStyleClass().add("back");


        //adding root on pane
        pane.getChildren().add(root);

        //set action on click
        back.setOnAction(e -> homeScreen(scene, pane, stage));

        //using css
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
    }

    public void gameKeys(Scene scene, Pane pane, Stage stage) {
        //Creating elements
        Image img = new Image("gamekeys.png");
        ImageView gameKeys = new ImageView(img);

        gameKeys.setFitWidth(620);
        gameKeys.setFitHeight(620);

        //adding root on pane
        pane.getChildren().add(gameKeys);

        gameKeys.setOnMouseReleased(e -> game(scene, pane, stage));
    }

    public void gameOver(Scene scene, Pane pane, Stage stage) {
        //Creating elements
        Image img = new Image("gameOver.png");
        ImageView gameOver = new ImageView(img);
        gameOver.setFitWidth(620);
        gameOver.setFitHeight(620);

        //adding root on pane
        pane.getChildren().add(gameOver);
    }

    public void game(Scene scene, Pane pane, Stage stage) {
        ImageView background = new ImageView(new Image("fundo.png"));
        KeyEvent keys = new KeyEvent();
        background.setFitHeight(620);
        background.setFitWidth(620);
        Image playerImg = new Image("rickwalk2-right.png");
        Player p1 = new Player(10, 10, 0, 0, 10, 5, 10, playerImg);
        Image img = new Image("zombieP.png");
        Zombies z1 = new Zombies(10,10,10,0,10,2,10,1, img);

        // Time and Points
        VBox timePoints =  new VBox();
        timePoints.setAlignment(Pos.TOP_LEFT);
        HBox pointsBox = new HBox();
        pointsBox.setAlignment(Pos.TOP_LEFT);

        //life and weapon
        p1.setPoints(p1.getPoints() + 777);
        p1.setTimeSurvived(95);
        Text points = new Text(String.valueOf(p1.getPoints()));
        Image img2 = new Image("coin2.png");
        ImageView coin = new ImageView(img2);
        Text time = new Text("time: " + String.valueOf(p1.getTimeSurvived()) + "s");

        keys.keyEvent(scene, pane, p1, z1);

        // define p1 position and width/height
        p1.setX(100);
        p1.setY(300);
        p1.setFitWidth(80);
        p1.setFitHeight(100);

        pointsBox.getChildren().addAll(coin, points);
        timePoints.getChildren().addAll(time ,pointsBox);


        pane.getChildren().addAll(background, timePoints, p1.getSprite(), z1.getSprite());


        points.getStyleClass().add("points");
        pointsBox.getStyleClass().add("pointsbox");
        timePoints.getStyleClass().add("timePoints");
        time.getStyleClass().add("time");

        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);

    }
}
