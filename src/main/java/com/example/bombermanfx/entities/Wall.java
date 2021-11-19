package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        setPassable(false);
    }

    @Override
    public void update(Bomberman game) {

    }

    @Override
    public void dead(Bomberman game) {

    }
}