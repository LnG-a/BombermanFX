package com.example.bombermanfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class HelloController {
    MediaPlayer mediaPlayer;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

        String path=getClass().getResource("/audio/explosion.mp3").getPath();
        System.out.println(path);
        Media media= new Media(new File(path).toURI().toString());
        mediaPlayer=new MediaPlayer(media);
        //mediaPlayer.setCycleCount(1);
        mediaPlayer.play();
    }
}