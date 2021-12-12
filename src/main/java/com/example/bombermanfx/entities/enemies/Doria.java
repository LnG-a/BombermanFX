package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AIHigh;
import com.example.bombermanfx.entities.enemies.Enemy;
import com.example.bombermanfx.graphics.Sprite;

public class Doria extends Enemy {
    public Doria(double x, double y) {
        super(x, y, 0.01);
        this.img = Sprite.kondoria_left1.getFxImage();
        this.ai = new AIHigh();
        this.enemyAnimation= new Sprite[]{Sprite.kondoria_right1,Sprite.kondoria_right2,Sprite.kondoria_right3,Sprite.kondoria_left1,Sprite.kondoria_left2,Sprite.kondoria_left3};
        this.deadSprite=Sprite.kondoria_dead;
        this.point = 1000;
    }
}
