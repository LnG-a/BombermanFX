package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public class Balloom extends MovableEntity{

    public Balloom(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update(Bomberman game) {

    }

    @Override
    public void dead(Bomberman game) {

    }
}
