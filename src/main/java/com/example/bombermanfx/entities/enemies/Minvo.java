package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AIMedium;
import com.example.bombermanfx.graphics.Sprite;

//ID: 4
//No special ability, fast
public class Minvo extends Enemy {
    private static final double SPEED=FAST;
    private static final int POINT=400;
    private static final Sprite[] ANIMATION= new Sprite[]{Sprite.minvo_right1,Sprite.minvo_right2,Sprite.minvo_right3,Sprite.minvo_left1,Sprite.minvo_left2,Sprite.minvo_left3};
    private static final Sprite DEAD_SPRITE = Sprite.minvo_dead;
    private static final AI AI_LEVEL= new AIMedium();

    public Minvo(double x, double y) {
        super(x, y, SPEED);
        this.point=POINT;
        this.enemyAnimation= ANIMATION;
        this.deadSprite=DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }
}
