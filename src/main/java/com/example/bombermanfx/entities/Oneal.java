package com.example.bombermanfx.entities;

import com.example.bombermanfx.entities.enemies.AI.AIHigh;
import com.example.bombermanfx.graphics.Sprite;

public class Oneal extends Enemy {
    public Oneal(double x, double y) {
        super(x, y,0.025);
        this.img = Sprite.oneal_left1.getFxImage();
        this.ai = new AIHigh();
        this.enemyAnimation= new Sprite[]{Sprite.oneal_right1,Sprite.oneal_right2,Sprite.oneal_right3,Sprite.oneal_left1,Sprite.oneal_left2,Sprite.oneal_left3};
        this.deadSprite= Sprite.oneal_dead;
        this.point=200;
    }
}
