package com.example.bombermanfx.entities;

import com.example.bombermanfx.entities.AI.AILow;
import com.example.bombermanfx.graphics.Sprite;

public class Dall extends Enemy {
    public Dall(double x, double y) {
        super(x, y,0.025);
        this.img = Sprite.doll_left1.getFxImage();
        this.ai = new AILow();
        this.enemyAnimation= new Sprite[]{Sprite.doll_right1,Sprite.doll_right2,Sprite.doll_right3,Sprite.doll_left1,Sprite.doll_left2,Sprite.doll_left3};
        this.deadSprite= Sprite.doll_dead;
        this.point= 400;
    }
}
