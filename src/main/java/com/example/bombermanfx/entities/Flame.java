package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;


public class Flame extends Entity{
    private final long time;
    public static final int CENTER=0;
    public static final int VERTICAL=1;
    public static final int HORIZONTAL=2;
    public static final int RIGHT=3;
    public static final int LEFT=4;
    public static final int UP=5;
    public static final int DOWN=6;

    private static final Image[] flameImages =new Image[]{ Sprite.bomb_exploded.getFxImage(),
    Sprite.explosion_vertical.getFxImage(),
    Sprite.explosion_horizontal.getFxImage(),
    Sprite.explosion_horizontal_right_last.getFxImage(),
    Sprite.explosion_horizontal_left_last.getFxImage(),
    Sprite.explosion_vertical_top_last.getFxImage(),
    Sprite.explosion_vertical_down_last.getFxImage()};
    /*enum Direction{
        CENTER,
        VERTICAL,
        HORIZONTAL,
        RIGHT,
        LEFT,
        UP,
        DOWN
    }*/

    public Flame(int xUnit, int yUnit,int type) {
        super(xUnit, yUnit);
        this.time=System.currentTimeMillis();
        this.img=flameImages[type];
    }

    @Override
    public void update(Bomberman game) {
        if (time < System.currentTimeMillis()-500) {
            game.getEntities().remove(this);
        }
    }

    @Override
    public void dead(Bomberman game) {

    }
}
