package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;

public class Grass extends Entity {

    public Grass(double x, double y) {
        super(x, y);
        this.img=Sprite.grass.getFxImage();
    }


    @Override
    public void update(Bomberman game) {

    }

    @Override
    public void dead(Bomberman game) {

    }
}