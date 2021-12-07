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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Bomberman extends Application {
    public static int WIDTH = 31;
    public static int HEIGHT = 13;

    public int LEVEL=1;
    public boolean levelUp;
    public int enemies=0;

    private Bomber player = new Bomber(1, 1);
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillEntities = new ArrayList<>();
    private Stage mainStage;

    public static void main(String[] args) {
        Application.launch(Bomberman.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.mainStage=stage;
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE*WIDTH, Sprite.SCALED_SIZE*HEIGHT);
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
        createMap(LEVEL);

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
        if (levelUp) {
            LEVEL++;
            try {
                levelUp=false;
                createMap(LEVEL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.update(this);
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(this);
        }
    }

    private void createMap(int level) throws IOException {
        ClassLoader c = ClassLoader.getSystemClassLoader();
        File file=new File(Objects.requireNonNull(c.getResource("levels/Level"+level+".txt")).getFile());
        Scanner scanner = new Scanner(file);

        System.out.println(scanner.nextInt());
        HEIGHT=scanner.nextInt();
        WIDTH= scanner.nextInt();
        scanner.nextLine();

        //set size canvas
        canvas.setHeight(Sprite.SCALED_SIZE* HEIGHT);
        canvas.setWidth(Sprite.SCALED_SIZE*WIDTH);

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        mainStage.setScene(scene);
        mainStage.show();

        reset();

        for (int i=0;i<HEIGHT;i++){
           String line= scanner.nextLine();
           for (int j=0;j<line.length();j++){
               switch (line.charAt(j)){
                   case '#':
                       stillEntities.add(new Wall(j,i));
                       break;
                   case ' ':
                       stillEntities.add(new Grass(j,i));
                       break;
                   case '*':
                       stillEntities.add(new Grass(j,i));
                       entities.add(new Brick(j,i));
                       break;
                   case '1':
                       stillEntities.add(new Grass(j,i));
                       entities.add(new Balloom(j,i));
                       enemies++;
                       break;
                   case '2':
                       stillEntities.add(new Grass(j,i));
                       entities.add(new Oneal(j,i));
                       enemies++;
                       break;
                   case 'x':
                       stillEntities.add(new Grass(j,i));
                       entities.add(new Portal(j,i));
                       entities.add(new Brick(j,i));
                       break;
                   case 'p':
                       entities.add(new Grass(j,i));
                       player.setX(j);
                       player.setY(i);
                       break;
               }

           }
        }
    }

    private void reset() {
        Bomb.currentBomb=0;
        for (int i=0;i<entities.size();i++){
            entities.remove(i);
        }
        for (int i=0;i<stillEntities.size();i++){
            stillEntities.remove(i);
        }
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

    public void setLevelUp(boolean levelUp) {
        this.levelUp = levelUp;
    }
}
