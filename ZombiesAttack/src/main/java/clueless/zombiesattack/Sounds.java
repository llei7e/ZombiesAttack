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
            path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\shot-gun.mp3";
        else // cocking
            path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\shot-gun-cocking.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getPistol(int op) {
        String path;
        if (op == 1) // shooting
            path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\pistol-shot.mp3";
        else // cocking
            path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\pistol-cock.mp3";

        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getKnife() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\hit-knife.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getKatana() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\hit-sword.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getWalking() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\steps.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getJumping() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\jump.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getZombieGrowl() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\zombie.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getSmallPunch() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\punch-2.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getBigPunch() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\punch-1.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getGameOver() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\game-over.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getOption () {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\option.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getHealing () {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\healing.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

}