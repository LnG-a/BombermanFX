package com.example.bombermanfx;

import com.example.bombermanfx.entities.*;
import com.example.bombermanfx.graphics.Sprite;
import com.example.bombermanfx.sounds.SoundPlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Bomberman extends Application {
    public static int WIDTH = 31;
    public static int HEIGHT = 13;
    private static Font fontRetro = Font.loadFont(Bomberman.class.getResource("/font/Fipps-Regular.otf").toExternalForm(),30);;


    public int LEVEL=1;
    public boolean levelUp;
    public int enemies=0;
    public int SCORE=0;

    private Bomber player = new Bomber(1, 1);
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillEntities = new ArrayList<>();
    private Stage mainStage;

    public static void main(String[] args) {
        launch();
    }

    public void play() throws IOException {
        //Theme song
        SoundPlayer.themeSong();

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
        mainStage.setScene(scene);
        //stage.show();

        // Set font
        gc.setFont(fontRetro);

        //Main loop
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

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Bomberman.class.getResource("Menu.fxml"));
        this.mainStage = stage;
        mainStage.setTitle("Bomberman 2k21");
        mainStage.setResizable(false);
        mainStage.getIcons().add(new Image(Objects.requireNonNull(Bomberman.class.getResourceAsStream("icon.png"))));
        mainStage.setScene(new Scene(fxmlLoader.load()));
        mainStage.show();
        Menu menuController = fxmlLoader.getController();
        menuController.stage = mainStage;
        menuController.game = this;

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
        updateStat();
        if (levelUp) {
            LEVEL++;
            try {
                levelUp=false;
                createMap(LEVEL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            player.update(this);
            for (int i = 0; i < entities.size(); i++) {
                entities.get(i).update(this);
            }
        }
    }

    public void createMap(int level) throws IOException {
        ClassLoader c = ClassLoader.getSystemClassLoader();
        File file=new File(Objects.requireNonNull(c.getResource("levels/Level"+level+".txt")).getFile());
        Scanner scanner = new Scanner(file);

        scanner.nextInt();
        HEIGHT=scanner.nextInt();
        WIDTH= scanner.nextInt();
        scanner.nextLine();

        //set size canvas
        canvas.setHeight(Sprite.SCALED_SIZE* HEIGHT+100);
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
                       //stillEntities.add(new Grass(j,i));
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
                       //stillEntities.add(new Grass(j,i));
                       entities.add(new Portal(j,i));
                       entities.add(new Brick(j,i));
                       break;
                   case 'p':
                       stillEntities.add(new Grass(j,i));
                       player.setX(j);
                       player.setY(i);
                       break;
               }
           }
        }
        //System.out.println(enemies);
    }

    private void reset() {
        Bomb.currentBomb=0;
        entities=new ArrayList<>();
        stillEntities=new ArrayList<>();
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

    public Entity getObstacle(int x, int y) {
        for (Entity i : this.getEntities()) {
            if (i.getX() == x && i.getY() == y && (i.getClass().equals(Brick.class)||i.getClass().equals(Bomb.class))) return i;
        }
        for (Entity i : this.getStillEntities()) {
            if (i.getX() == x && i.getY() == y && i.getClass().equals(Wall.class)) return i;
        }
        return null;
    }

    public Grass getGrass(int x, int y) {
        for (Entity i : this.getStillEntities()) {
            if(i.getX() == x && i.getY() == y && i.getClass().equals(Grass.class)) return (Grass)i;
        }
        return null;
    }

    public boolean hasBomb(int x, int y) {
        for (Entity i : this.getEntities()) {
            if(i.getX() == x && i.getY() == y && i.getClass().equals(Bomb.class)) return true;
        }
        return false;
    }

    public boolean validatePosition(int x, int y) {
        if (getObstacle(x,y)==null|| getObstacle(x,y).isPassable()) return true;
        return false;
    }

    public void setLevelUp(boolean levelUp) {
        this.levelUp = levelUp;
    }

    private void updateStat(){
        gc.setFill(Color.rgb(185,185,185));
        gc.fillRect(0, canvas.getHeight()-100, canvas.getWidth(), 100);
        gc.setFill(Color.rgb(0,0,0));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Level "+LEVEL,50,canvas.getHeight()-30);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText("Left " +(player.getLife()-1),canvas.getWidth() - 50,canvas.getHeight()-30);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(SCORE+"",canvas.getWidth()/2,canvas.getHeight()-30);
    }
}
