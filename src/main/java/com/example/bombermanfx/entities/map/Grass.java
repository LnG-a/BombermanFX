package com.example.bombermanfx.entities.map;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.Entity;
import com.example.bombermanfx.graphics.Sprite;

public class Grass extends Entity {

    public Grass(double x, double y) {
        super(x, y);
        this.img=Sprite.grass.getFxImage();
    }

    @Override
    public void update(Bomberman game) {

    }

}