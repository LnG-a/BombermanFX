package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AILow;
import com.example.bombermanfx.graphics.Sprite;

//ID 3
//No special ability
public class Dall extends Enemy {
    private static final double SPEED=NORMAL;
    private static final int POINT=150;
    private static final Sprite[] ANIMATION= new Sprite[]{Sprite.dall_right1,Sprite.dall_right2,Sprite.dall_right3,Sprite.dall_left1,Sprite.dall_left2,Sprite.dall_left3};
    private static final Sprite DEAD_SPRITE = Sprite.dall_dead;;
    private static final AI AI_LEVEL= new AILow();

    public Dall(double x, double y) {
        super(x, y,SPEED);
        this.point= POINT;
        this.enemyAnimation= ANIMATION;
        this.deadSprite= DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }
}
