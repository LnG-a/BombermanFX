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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bomberman extends Application {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private Bomber player = new Bomber(1, 1);
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillEntities = new ArrayList<>();

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
            public void handle(long now) {

                render();
                update();
            }
        };

        timer.start();
        createMap();

    }

    private void render() {
        for (Entity i : stillEntities) {
            i.render(gc);
        }
        for (Entity i : entities) {
            i.render(gc);
        }
        player.render(gc);
    }

    private void update() {
        player.update(this);
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(this);
        }
    }

    private void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i == 0 || i == WIDTH - 1 || j == 0 || j == HEIGHT - 1) {
                    stillEntities.add(new Wall(i, j));
                } else {
                    stillEntities.add(new Grass(i, j));
                }
            }
        }
        for (int i = 3; i < WIDTH - 3; i++) {
            entities.add(new Brick(i, 3));
        }
        for (int i = 3; i < WIDTH - 3; i+=2) {
            stillEntities.add(new Wall(i, 5));
        }
        for (int i = 3; i < WIDTH - 3; i+=2) {
            stillEntities.add(new Wall(i, 7));
        }
        entities.add(new Oneal(17.5, 1.5));
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getStillEntities() {
        return stillEntities;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Bomber getPlayer() {
        return player;
    }

    public Entity getAt(int x, int y) {
        for (Entity i : this.getEntities()) {
            if (i.getX() == x && i.getY() == y && (!i.isPassable()||i.getClass().equals(Bomb.class))) return i;
        }
        for (Entity i : this.getStillEntities()) {
            if (i.getX() == x && i.getY() == y && i.getClass().equals(Wall.class)) return i;
        }
        return null;
    }

    public boolean validatePosition(int x, int y) {
        if (getAt(x,y)==null|| getAt(x,y).isPassable()) return true;
        return false;
    }
}
