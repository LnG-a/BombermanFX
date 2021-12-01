package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;

public class Grass extends Entity {

    public Grass(int x, int y) {
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