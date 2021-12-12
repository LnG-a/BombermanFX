package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AILow;
import com.example.bombermanfx.entities.enemies.Enemy;
import com.example.bombermanfx.graphics.Sprite;

public class Dall extends Enemy {
    public Dall(double x, double y) {
        super(x, y,0.025);
        this.ai = new AILow();
        this.enemyAnimation= new Sprite[]{Sprite.dall_right1,Sprite.dall_right2,Sprite.dall_right3,Sprite.dall_left1,Sprite.dall_left2,Sprite.dall_left3};
        this.deadSprite= Sprite.dall_dead;
        this.point= 400;
    }
}
