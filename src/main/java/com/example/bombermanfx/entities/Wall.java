package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
        setPassable(false);
        this.img=Sprite.wall.getFxImage();
    }

    @Override
    public void update(Bomberman game) {

    }

    @Override
    public void dead(Bomberman game) {

    }
}