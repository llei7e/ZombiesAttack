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
        start.setOnMouseReleased(e -> gameKeys(scene, pane, stage));
        ranking.setOnMouseReleased(e -> rankingScreen(scene, pane, stage));
        exit.setOnMouseReleased(e -> stage.close());

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
        back.setOnMouseReleased(e -> homeScreen(scene, pane, stage));

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
        KeyEvent keys = new KeyEvent();
        Image playerImg = new Image("rickwalk2-right.png");
        Player p1 = new Player(10, 10, 0, 0, playerImg);
        Image img = new Image("zombieM-walking2.png");
        Zombies z1 = new Zombies(10, 10, 10, 0, 1, img);
        Zombies z2 = new Zombies(10, 10, 10, 0, 2, img);
        Zombies z3 = new Zombies(10, 10, 10, 0, 3, img);
        ImageView background = new ImageView(new Image("fundo.png"));
        background.setFitHeight(620);
        background.setFitWidth(620);

        //HUD

        //HUD - Time and Points
        HBox pointsBox = new HBox();
        pointsBox.setAlignment(Pos.CENTER);

        p1.setPoints(p1.getPoints() + 777);
        Text points = new Text(String.valueOf(p1.getPoints()) + " pts");
        Image img2 = new Image("coin2.png");
        ImageView coin = new ImageView(img2);

        pointsBox.getChildren().addAll(coin, points);

        //HUD - life and weapon
        VBox lifeWeapon = new VBox();
        lifeWeapon.setAlignment(Pos.TOP_RIGHT);
        Image img3 = new Image("life" + p1.getLife() + ".png");
        ImageView life = new ImageView(img3);
        HBox weapon = new HBox();
        weapon.setAlignment(Pos.CENTER_RIGHT);
        Text weaponName = new Text(p1.getWeapon());
        Image img4 = new Image(p1.getWeapon() + ".png");
        ImageView weaponImg = new ImageView(img4);

        weapon.getChildren().addAll(weaponName, weaponImg);
        lifeWeapon.getChildren().addAll(life, weapon);
        lifeWeapon.setLayoutX(300);
        pointsBox.setLayoutX(-10);


        //Add objects at the pane (screen)
        pane.getChildren().addAll(background, pointsBox, lifeWeapon, p1.getSprite(), z1.getSprite(), z2.getSprite(), z3.getSprite());

        //Get css
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        //Get css class
        points.getStyleClass().add("points");
        pointsBox.getStyleClass().add("timePoints");
        weaponName.getStyleClass().add("points");
        lifeWeapon.getStyleClass().add("lifeweapon");
        weapon.getStyleClass().add("weaponbox");

        //Actions
        keys.keyEvent(scene, pane, p1, z1, z2, z3, life,weaponImg, weaponName, points);

    }
}
