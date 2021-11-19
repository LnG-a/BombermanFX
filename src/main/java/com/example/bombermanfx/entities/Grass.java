package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public class Grass extends Entity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(Bomberman game) {

    }

    @Override
    public void dead(Bomberman game) {

    }
}