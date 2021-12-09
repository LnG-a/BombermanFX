package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.AI.AILow;
import com.example.bombermanfx.graphics.Sprite;

public class Balloom extends Enemy {
    public Balloom(double x, double y) {
        super(x, y, 0.1);
        this.img = Sprite.balloom_left1.getFxImage();
        this.ai = new AILow();
        this.enemyAnimation= new Sprite[]{Sprite.balloom_right1,Sprite.balloom_right2,Sprite.balloom_right3,Sprite.balloom_left1,Sprite.balloom_left2,Sprite.balloom_left3};
    }
}
