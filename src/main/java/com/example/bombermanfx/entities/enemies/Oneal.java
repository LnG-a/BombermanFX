package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AIMedium;
import com.example.bombermanfx.graphics.Sprite;

//ID: 2
//No special ability
public class Oneal extends Enemy {
    private static final double SPEED = NORMAL;
    private static final int POINT = 200;
    private static final Sprite[] ANIMATION = new Sprite[]{Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3};
    private static final Sprite DEAD_SPRITE = Sprite.oneal_dead;
    private static final AI AI_LEVEL = new AIMedium();

    public Oneal(double x, double y) {
        super(x, y, SPEED);
        this.point = POINT;
        this.enemyAnimation = ANIMATION;
        this.deadSprite = DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }
}
