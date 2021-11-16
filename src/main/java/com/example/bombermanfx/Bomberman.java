package com.example.bombermanfx;

import com.example.bombermanfx.entities.*;
import com.example.bombermanfx.graphics.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bomberman extends Application {
    private static int width = 1000;
    private static int height = 1000;
    private int mainPosX=0;
    private int mainPosY=0;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<MovableEntity> moveEntities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(Bomberman.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();
        Image a = new Image("D:\\E-Learning\\OOP\\BombermanFX\\1.png");
        MovableEntity bomberman = new Bomber(0, 0, a);
        moveEntities.add(bomberman);
    }

    private void render() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);
        /*gc.setFill(Color.BLACK);
        gc.fillRect(mainPosX, mainPosY, 100, 100);*/
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        moveEntities.get(0).moveRight();
                        break;
                    case LEFT:
                        moveEntities.get(0).moveLeft();
                        break;
                    case UP:
                        moveEntities.get(0).moveUp();
                        break;
                    case DOWN:
                            moveEntities.get(0).moveDown();
                        break;
                }
            }
        });
        for (Entity i:moveEntities){
            i.render(gc);
        }
    }

    private void update() {

    }

    private void createMap() {

    }


}
