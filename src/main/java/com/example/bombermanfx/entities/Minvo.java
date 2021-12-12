package com.example.bombermanfx.entities;

import com.example.bombermanfx.entities.AI.AIMedium;
import com.example.bombermanfx.graphics.Sprite;

public class Minvo extends Enemy {
    public Minvo(double x, double y) {
        super(x, y, 1/30);
        this.img = Sprite.minvo_left1.getFxImage();
        this.ai = new AIMedium();
        this.enemyAnimation= new Sprite[]{Sprite.minvo_right1,Sprite.minvo_right2,Sprite.minvo_right3,Sprite.minvo_left1,Sprite.minvo_left2,Sprite.minvo_left3};
        this.deadSprite=Sprite.minvo_dead;
        this.point=800;
    }
}
