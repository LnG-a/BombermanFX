package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
        setPassable(false);
        this.img = Sprite.wall.getFxImage();
    }

    @Override
    public void update(Bomberman game) {
    }
}