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

    public static MediaPlayer getRifleSound() {
        return new MediaPlayer(new Media(new File (/*caminho*/).toURI().toString()));
    }
}

