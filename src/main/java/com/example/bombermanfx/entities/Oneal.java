package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;

public class Oneal extends Enemy {
    private long time = System.currentTimeMillis();
    private int random = (int) (Math.random() * 4);

    public Oneal(double x, double y) {
        super(x, y,0.1);
        this.img = Sprite.oneal_left1.getFxImage();
    }

    @Override
    public void update(Bomberman game) {
        dead(game);
    }


}
