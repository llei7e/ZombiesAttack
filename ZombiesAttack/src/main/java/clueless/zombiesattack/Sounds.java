package clueless.zombiesattack;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Sounds {

    // Properties
    private MediaPlayer sound;

    // Construtor
    public Sounds () {

    }

    public Sounds(String audioPath) {
        this.sound = new MediaPlayer(new Media(new File (audioPath).toURI().toString()));
    }


    // Methods
    public MediaPlayer getSoundEffect() {
        return sound;
    }

    public static MediaPlayer getRifle(int op) {
        String path;
        if (op == 1) // shooting
            path = "/sounds/shot-gun.mp3";
        else // cocking
            path = "/sounds/shot-gun-cocking.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getPistol(int op) {
        String path;
        if (op == 1) // shooting
            path = "/sounds/pistol-shot.mp3";
        else // cocking
            path = "/sounds/pistol-cock.mp3";

        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getKnife() {
        String path = "/sounds/hit-knife.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getKatana() {
        String path = "/sounds/hit-sword.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getWalking() {
        String path = "/sounds/steps.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getJumping() {
        String path = "/sounds/jump.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getZombieGrowl() {
        String path = "/sounds/zombie.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getSmallPunch() {
        String path = "/sounds/punch-2.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getBigPunch() {
        String path = "/sounds/punch-1.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getGameOver() {
        String path = "/sounds/game-over.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getOption () {
        String path = "/sounds/option.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

    public static MediaPlayer getHealing () {
        String path = "/sounds/healing.mp3";
        return new MediaPlayer(new Media(Sounds.class.getResource(path).toString()));
    }

}