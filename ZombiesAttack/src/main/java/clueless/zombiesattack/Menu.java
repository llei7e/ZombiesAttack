package clueless.zombiesattack;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


public class Menu {
    static KeyEvent keys = new KeyEvent();

    public static void homeScreen(Scene scene, Pane pane, Stage stage, Ranking ranking) {

        // backgroundSound - homeScreen
        MediaPlayer backgroundSound = Sounds.getHome();
        backgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundSound.play();

        //Creating elements
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        HBox sprites = new HBox();

        //Buttons Instances
        Button start = new Button("START GAME");
        Button rankingB = new Button("RANKING");
        Button exit = new Button("EXIT");
        ImageView logo = new ImageView("logo.png");


        //adding nodes on root
        //sprites.getChildren().addAll(indiana, zombieM,zombieS,zombieG);
        root.getChildren().addAll(logo, start, rankingB, exit, sprites);
        logo.setFitWidth(620);
        logo.setFitHeight(250);

        //defining css path
        start.getStyleClass().add("homeButtons");
        start.setId("start");

        rankingB.getStyleClass().add("homeButtons");
        exit.getStyleClass().add("homeButtons");
        root.getStyleClass().add("vbox");

        start.setOnMouseReleased(e -> {
            backgroundSound.stop();
            Sounds.getOption().play();
            loading(scene, pane, stage);
        });
        rankingB.setOnMouseReleased(e -> {
            Sounds.getOption().play();
            rankingScreen(scene, pane, stage, ranking);
        });

        exit.setOnMouseReleased(e -> stage.close());

        //adding root on pane
        pane.getChildren().add(root);

        //using css
        String css = Menu.class.getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
    }

    public static void rankingScreen(Scene scene, Pane pane, Stage stage, Ranking ranking) {
        // Creating elements
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);

        HBox rankingSpace = new HBox();
        rankingSpace.setAlignment(Pos.CENTER);
        rankingSpace.setMinWidth(400);
        rankingSpace.setSpacing(180);

        VBox pointsBox = new VBox();
        pointsBox.setAlignment(Pos.CENTER_LEFT);
        pointsBox.setSpacing(10);
        VBox namesBox = new VBox();
        namesBox.setAlignment(Pos.CENTER_RIGHT);
        namesBox.setSpacing(10);

        // Load winners from ranking
        ArrayList<Winner> winners = ranking.getWinners();
        for (Winner winner : winners) {
            Text pointsText = new Text(String.valueOf(winner.getPoints()));
            Text nameText = new Text(winner.getName());

            pointsBox.getChildren().add(pointsText);
            namesBox.getChildren().add(nameText);

            pointsText.getStyleClass().add("rankingText");
            nameText.getStyleClass().add("rankingText");
        }

        ImageView logo = new ImageView("ranking.png");

        Button back = new Button("back");

        // Adding nodes on root
        rankingSpace.getChildren().addAll(pointsBox, namesBox);
        root.getChildren().addAll(logo, rankingSpace, back);
        logo.setFitWidth(620);
        logo.setFitHeight(270);

        // Defining css path
        root.getStyleClass().add("vbox");
        back.getStyleClass().add("back");

        // Adding root on pane
        pane.getChildren().add(root);

        //set action on click
        back.setOnMouseReleased(e -> {
            Sounds.getOption().play();
            homeScreen(scene, pane, stage, ranking);
        });

        // Using css
        String css = Menu.class.getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
    }


    public static void gameKeys(Scene scene, Pane pane, Stage stage) {
        //Creating elements
        Image img = new Image("gameKeys.png");
        ImageView gameKeys = new ImageView(img);

        gameKeys.setFitWidth(pane.getWidth());
        gameKeys.setFitHeight(pane.getHeight());

        //adding root on pane
        pane.getChildren().add(gameKeys);

        gameKeys.setOnMouseReleased(e -> game(scene, pane, stage));
    }

    public static void gameOver(Scene scene, Pane pane, Stage stage, Player p1) {
        //Creating elements
        Image img = new Image("gameOver.png");
        ImageView gameOver = new ImageView(img);
        gameOver.setFitWidth(pane.getWidth());
        gameOver.setFitHeight(pane.getHeight());

        //adding root on pane
        pane.getChildren().add(gameOver);


        PauseTransition delay = new PauseTransition(Duration.seconds(3));

        delay.setOnFinished(e -> rankingName(scene, pane, stage, p1));

        delay.play();
    }

    public static void game(Scene scene, Pane pane, Stage stage) {

        Player p1 = new Player();

        ImageView background = new ImageView(new Image("background.png"));
        background.setFitHeight(pane.getHeight());
        background.setFitWidth(pane.getWidth());

        // Zombies Collection
        ArrayList<Zombies> zombies = new ArrayList<>();

        //HUD

        //HUD - Points
        HBox pointsBox = new HBox();
        pointsBox.setAlignment(Pos.CENTER);

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
        pane.getChildren().addAll(background, pointsBox, lifeWeapon, p1.getSprite());

        //Get css
        String css = Menu.class.getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        //Get css class
        points.getStyleClass().add("points");
        pointsBox.getStyleClass().add("timePoints");
        weaponName.getStyleClass().add("points");
        lifeWeapon.getStyleClass().add("lifeweapon");
        weapon.getStyleClass().add("weaponbox");

        //Actions
        keys.keyEvent(scene, pane, p1, zombies, life, weaponImg, weaponName, points, stage);
    }

    public static void loading(Scene scene, Pane pane, Stage stage) {

        // backgroundSound - loading
        MediaPlayer backgroundSound = Sounds.getLoading();
        backgroundSound.play();

        //Creating elements
        Image imgDark = new Image("loadEscuro2.jpeg");
        ImageView loadingDark = new ImageView(imgDark);
        loadingDark.setFitWidth(pane.getWidth());
        loadingDark.setFitHeight(pane.getHeight());

        Image imgLight = new Image("loadClaro.jpeg");
        ImageView loadingLight = new ImageView(imgLight);
        loadingLight.setFitWidth(pane.getWidth());
        loadingLight.setFitHeight(pane.getHeight());

        Image imgText1 = new Image("load1.png");
        ImageView load1 = new ImageView(imgText1);
        load1.setX(450);
        load1.setY(30);
        Image imgText2 = new Image("load2.png");
        ImageView load2 = new ImageView(imgText2);
        load2.setX(450);
        load2.setY(30);
        Image imgText3 = new Image("load3.png");
        ImageView load3 = new ImageView(imgText3);
        load3.setX(450);
        load3.setY(30);
        Image imgText4 = new Image("load4.png");
        ImageView load4 = new ImageView(imgText4);
        load4.setX(450);
        load4.setY(30);

        Image imgzombie1 = new Image("zombieG-walking2.png");
        ImageView zombie1 = new ImageView(imgzombie1);
        zombie1.setX(285);
        zombie1.setY(535);
        zombie1.setFitWidth(75);
        zombie1.setFitHeight(86);
        Image imgzombie2 = new Image("zombieM-walking2.png");
        ImageView zombie2 = new ImageView(imgzombie2);
        zombie2.setX(293);
        zombie2.setY(550);
        zombie2.setFitWidth(50);
        zombie2.setFitHeight(67);

        //adding root on pane
        pane.getChildren().addAll(loadingDark);

        Timeline backgroundTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), actionEvent -> {
                    pane.getChildren().remove(loadingDark);
                    pane.getChildren().addAll(loadingLight, zombie1);
                }),
                new KeyFrame(Duration.seconds(2), actionEvent -> {
                    pane.getChildren().removeAll(loadingLight, zombie1);
                    pane.getChildren().add(loadingDark);
                }),
                new KeyFrame(Duration.seconds(3), actionEvent -> {
                    pane.getChildren().remove(loadingDark);
                    pane.getChildren().addAll(loadingLight, zombie2);
                }),
                new KeyFrame(Duration.seconds(4), actionEvent -> {
                    pane.getChildren().removeAll(loadingLight, zombie2);
                    pane.getChildren().add(loadingDark);
                })
        );

        // Create a separate Timeline to alternate between loading text images
        Timeline loadingTextTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.25), actionEvent -> {
                    pane.getChildren().remove(load4);
                    pane.getChildren().add(load3);
                }),
                new KeyFrame(Duration.seconds(0.5), actionEvent -> {
                    pane.getChildren().remove(load3);
                    pane.getChildren().add(load2);
                }),
                new KeyFrame(Duration.seconds(0.75), actionEvent -> {
                    pane.getChildren().remove(load2);
                    pane.getChildren().add(load1);
                }),
                new KeyFrame(Duration.seconds(1), actionEvent -> {
                    pane.getChildren().remove(load1);
                    pane.getChildren().add(load4);
                })

        );

        PauseTransition delay = new PauseTransition(Duration.seconds(7));
        // Set the cycle counts and play the timelines
        backgroundTimeline.setCycleCount(Timeline.INDEFINITE);
        loadingTextTimeline.setCycleCount(Timeline.INDEFINITE);

        delay.setOnFinished(e -> {
            backgroundSound.stop();
            backgroundTimeline.stop();
            loadingTextTimeline.stop();
            gameKeys(scene, pane, stage);
        });
        backgroundTimeline.play();
        loadingTextTimeline.play();
        delay.play();
    }

    public static void rankingName(Scene scene, Pane pane, Stage stage, Player p1) {
        // Creating elements
        Image moonImg = new Image("rankingbg.jpeg");
        ImageView moon = new ImageView(moonImg);
        moon.setFitWidth(620);
        moon.setFitHeight(620);
        Image hand = new Image("zombiehand.png");
        ImageView zhand = new ImageView(hand);
        zhand.setX(229);
        zhand.setY(244);

        TextField nameField = new TextField();
        nameField.setLayoutX(165);
        nameField.setLayoutY(264);
        Button confirm = new Button("CONFIRM");
        confirm.setLayoutX(250);
        confirm.setLayoutY(500);

        confirm.getStyleClass().add("homeButtons");
        nameField.getStyleClass().add("text-field");

        //limit characters
        final int maxCharacters = 10;
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxCharacters) {
                nameField.setText(newValue.substring(0, maxCharacters));
            }
        });


        // Using css
        String css = Menu.class.getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Update confirm button action
        confirm.setOnMouseReleased(e -> {
            String playerName = nameField.getText();
            // Assume points are calculated elsewhere in the game
            int playerPoints = p1.getPoints();

            //add the new winner and save it to the file
            Ranking ranking = new Ranking();
            ranking.saveWinner(playerName, playerPoints);

            rankingScreen(scene, pane, stage, ranking);
        });

        pane.getChildren().addAll(moon, confirm, nameField, zhand);
    }

    public void buyScreen(Scene scene, Pane pane) {

        //background
        ImageView background = new ImageView(new Image("fundo.png"));
        background.setFitHeight(620);
        background.setFitWidth(620);

        //player
        Player p1 = new Player();
        p1.getSprite().setX(100);
        p1.getSprite().setY(265);
        p1.getSprite().setFitWidth(85);
        p1.getSprite().setFitHeight(110);

        //HUD - Points
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

        //Buy Options
        VBox options = new VBox();
        options.setAlignment(Pos.CENTER);
        Text weaponsOp = new Text("Weapons:");


        HBox knifeBox = new HBox();
        knifeBox.setAlignment(Pos.CENTER_LEFT);
        Button knife = new Button("0   knife");
        ImageView coin2 = new ImageView(img2);

        HBox swordBox = new HBox();
        swordBox.setAlignment(Pos.CENTER_LEFT);
        Button katana = new Button("200 sword");
        ImageView coin3 = new ImageView(img2);

        HBox pistolBox = new HBox();
        pistolBox.setAlignment(Pos.CENTER_LEFT);
        Button pistol = new Button("400 pistol");
        ImageView coin4 = new ImageView(img2);

        HBox rifleBox = new HBox();
        rifleBox.setAlignment(Pos.CENTER_LEFT);
        Button rifle = new Button("800 rifle");
        ImageView coin5 = new ImageView(img2);


        knifeBox.getChildren().addAll(coin2, knife);
        swordBox.getChildren().addAll(coin3, katana);
        pistolBox.getChildren().addAll(coin4, pistol);
        rifleBox.getChildren().addAll(coin5, rifle);

        options.getChildren().addAll(weaponsOp, knifeBox, swordBox, pistolBox, rifleBox);

        options.setLayoutX(300);
        options.setLayoutY(200);

        //Play
        Button play = new Button("PLAY");
        play.setLayoutX(250);
        play.setLayoutY(530);


        //Add objects at the pane (screen)
        pane.getChildren().addAll(background, pointsBox, lifeWeapon, p1.getSprite(), options, play);

        //Get css
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);

        //Get css class
        points.getStyleClass().add("points");
        pointsBox.getStyleClass().add("timePoints");
        weaponName.getStyleClass().add("points");
        lifeWeapon.getStyleClass().add("lifeweapon");
        weapon.getStyleClass().add("weaponbox");

        knife.getStyleClass().add("buttons");
        katana.getStyleClass().add("buttons");
        pistol.getStyleClass().add("buttons");
        rifle.getStyleClass().add("buttons");
        play.getStyleClass().add("buyPlay");

        weaponsOp.getStyleClass().add("points");

        knife.setOnMouseReleased(e -> p1.setWeapon("knife"));
        katana.setOnMouseReleased(e -> p1.setWeapon("katana"));
        pistol.setOnMouseReleased(e -> p1.setWeapon("pistol"));
        rifle.setOnMouseReleased(e -> p1.setWeapon("rifle"));

    }
}
