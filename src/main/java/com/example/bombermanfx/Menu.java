package com.example.bombermanfx;

import com.example.bombermanfx.graphics.Sprite;
import com.example.bombermanfx.sounds.SoundPlayer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    public Stage stage;
    public Bomberman game;

    @FXML
    private Label exitButton;

    @FXML
    private Label playButton;

    @FXML
    void exitButtonClicked() {
        stage.close();
    }


    @FXML
    void playButtonClicked() throws IOException {
        game.play();
    }

}
