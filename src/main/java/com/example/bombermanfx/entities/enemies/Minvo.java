package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AIMedium;
import com.example.bombermanfx.graphics.Sprite;

//ID: 6
public class Minvo extends Enemy {
    public Minvo(double x, double y) {
        super(x, y, 1/30);
        this.ai = new AIMedium();
        this.enemyAnimation= new Sprite[]{Sprite.minvo_right1,Sprite.minvo_right2,Sprite.minvo_right3,Sprite.minvo_left1,Sprite.minvo_left2,Sprite.minvo_left3};
        this.deadSprite=Sprite.minvo_dead;
        this.point=800;
    }
}
