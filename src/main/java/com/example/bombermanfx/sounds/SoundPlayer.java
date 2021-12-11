package com.example.bombermanfx.sounds;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
    private static final Media EXPLOSION = new Media(SoundPlayer.class.getResource("/audio/explosion.mp3").toExternalForm());
    private static final Media LEVELUP = new Media(SoundPlayer.class.getResource("/audio/levelUp.wav").toExternalForm());
    private static final Media MOVE_RIGHT_LEFT = new Media(SoundPlayer.class.getResource("/audio/moveRightLeft.wav").toExternalForm());
    private static final Media MOVE_UP_DOWN = new Media(SoundPlayer.class.getResource("/audio/moveUpDown.wav").toExternalForm());
    private static final Media POWER_UP = new Media(SoundPlayer.class.getResource("/audio/powerUp.wav").toExternalForm());
    private static final Media THEME = new Media(SoundPlayer.class.getResource("/audio/themeSong.mp3").toExternalForm());
    //private static final Media DEAD = new Media(SoundPlayer.class.getResource("/audio/dead.mp3").toExternalForm());

    private static MediaPlayer mediaPlayer;
    public static void explosion(){
        mediaPlayer = new MediaPlayer(EXPLOSION);
        mediaPlayer.play();
    }

    public static void levelUp(){
        mediaPlayer = new MediaPlayer(LEVELUP);
        mediaPlayer.play();
    }

    public static void moveRightLeft(){
        mediaPlayer = new MediaPlayer(MOVE_RIGHT_LEFT);
        mediaPlayer.play();
    }

    public static void moveUpDown(){
        mediaPlayer = new MediaPlayer(MOVE_UP_DOWN);
        mediaPlayer.play();
    }

    public static void powerUp(){
        mediaPlayer = new MediaPlayer(POWER_UP);
        mediaPlayer.play();
    }

    public static void themeSong(){
        mediaPlayer = new MediaPlayer(THEME);
        mediaPlayer.setCycleCount(-1);
        mediaPlayer.play();
    }

    /*public static void dead(){
        mediaPlayer = new MediaPlayer(DEAD);
        mediaPlayer.play();
    }*/
}
