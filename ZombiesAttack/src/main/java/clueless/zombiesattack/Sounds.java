package clueless.zombiesattack;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Sounds {

    // Properties
    private final MediaPlayer sound;

    // Construtor
    public Sounds(String audioPath) {
        // sounds effect
        this.sound = new MediaPlayer(new Media(new File(audioPath).toURI().toString()));
    }


    // Methods
    public MediaPlayer getSoundEffect() {
        return sound;
    }
}
