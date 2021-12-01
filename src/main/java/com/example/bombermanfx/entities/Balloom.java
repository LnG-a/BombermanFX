package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;

public class Balloom extends MovableEntity{

    public Balloom(double x, double y) {
        super(x, y);
        this.img=Sprite.balloom_dead.getFxImage();
    }

    @Override
    public void update(Bomberman game) {

    }

    @Override
    public void dead(Bomberman game) {

    }
}
