package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.AI.AILow;
import com.example.bombermanfx.graphics.Sprite;

public class Balloom extends Enemy {

    public Balloom(double x, double y) {
        super(x, y, 0.1);
        this.img = Sprite.balloom_left1.getFxImage();
        this.ai = new AILow();
    }
}
