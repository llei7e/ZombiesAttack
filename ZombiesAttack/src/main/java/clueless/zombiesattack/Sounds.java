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

    public static MediaPlayer getRifleSound(int op) {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\shot-rifle-39-mm-37542.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getPistol(int op) {
        if (op == 1)
            String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\.mp3";
        else
            String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getKnife() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getKatana() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getWalking() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getJumping() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getZombieGrowl() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getSmallPunch() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getBigPunch() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }

    public static MediaPlayer getGameOver() {
        String path = "C:\\Game\\Clueless\\ZombiesAttack\\src\\main\\resources\\sounds\\.mp3";
        return new MediaPlayer(new Media(new File (path).toURI().toString()));
    }



}