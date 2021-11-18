package com.example.bombermanfx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class SpaceInvaders extends Application {

    private Pane root = new Pane();

    private Parent createContent(){
        root.setPrefSize(600,800);

        //root.getChildren().add()

        return root;
    }

    private static class Sprite extends Rectangle {
        boolean dead = false;
        final String type;

        Sprite(int x, int y, int w, int h, String type, Color color) {
            super(w,h,color);

            this.type = type;
            setTranslateX(x);
            setTranslateY(y);
        }

        void moveLeft(){
            setTranslateX(getTranslateX()-5);
        }

        void moveRight() {
            setTranslateX(getTranslateX()+5);
        }

        void moveUp(){
            setTranslateY(getTranslateY()-5);
        }

        void moveDown(){
            setTranslateY(getTranslateX()+5);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
