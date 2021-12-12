package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AIHigh;
import com.example.bombermanfx.graphics.Sprite;

//ID 5
public class Doria extends Enemy {
    private static final double SPEED = SLOWEST;
    private static final int POINT = 400;
    private static final Sprite[] ANIMATION = new Sprite[]{Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3};
    private static final Sprite DEAD_SPRITE = Sprite.kondoria_dead;
    private static final AI AI_LEVEL = new AIHigh();


    public Doria(double x, double y) {
        super(x, y, SPEED);
        this.point = POINT;
        this.enemyAnimation = ANIMATION;
        this.deadSprite = DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }
}
