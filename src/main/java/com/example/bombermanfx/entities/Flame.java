package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;


public class Flame extends Entity{
    private final int type;
    private final long time;
    public static final int CENTER=0;
    public static final int VERTICAL=1;
    public static final int HORIZONTAL=2;
    public static final int RIGHT=3;
    public static final int LEFT=4;
    public static final int UP=5;
    public static final int DOWN=6;

    private static final Image[][] flameAnimation =new Image[][]{
            {Sprite.bomb_exploded.getFxImage(),Sprite.bomb_exploded_1.getFxImage(),Sprite.bomb_exploded_2.getFxImage()},
            {Sprite.explosion_vertical.getFxImage(),Sprite.explosion_vertical_1.getFxImage(),Sprite.explosion_vertical_2.getFxImage()},
            {Sprite.explosion_horizontal.getFxImage(),Sprite.explosion_horizontal_1.getFxImage(),Sprite.explosion_horizontal_2.getFxImage()},
            {Sprite.explosion_horizontal_right_last.getFxImage(),Sprite.explosion_horizontal_right_last_1.getFxImage(),Sprite.explosion_horizontal_right_last_2.getFxImage()},
            {Sprite.explosion_horizontal_left_last.getFxImage(),Sprite.explosion_horizontal_left_last_1.getFxImage(),Sprite.explosion_horizontal_left_last_2.getFxImage()},
            {Sprite.explosion_vertical_top_last.getFxImage(),Sprite.explosion_vertical_top_last_1.getFxImage(),Sprite.explosion_vertical_top_last_2.getFxImage()},
            {Sprite.explosion_vertical_down_last.getFxImage(),Sprite.explosion_vertical_down_last_1.getFxImage(),Sprite.explosion_vertical_down_last_2.getFxImage()}};
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
        this.type=type;
    }

    @Override
    public void update(Bomberman game) {
        if (System.currentTimeMillis()>time+500) game.getEntities().remove(this);
        else if (System.currentTimeMillis()>time+400) this.img=flameAnimation[type][0];
        else if (System.currentTimeMillis()>time+300) this.img=flameAnimation[type][1];
        else if (System.currentTimeMillis()>time+200) this.img=flameAnimation[type][2];
        else if (System.currentTimeMillis()>time+100) this.img=flameAnimation[type][1];
        else this.img=flameAnimation[type][0];

    }

    @Override
    public void dead(Bomberman game) {

    }
}
