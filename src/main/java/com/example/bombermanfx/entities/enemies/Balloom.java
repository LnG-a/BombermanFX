package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AILow;
import com.example.bombermanfx.graphics.Sprite;

//ID: 1
public class Balloom extends Enemy {
    private static final double SPEED = SLOW;
    private static final int POINT = 100;
    private static final Sprite[] ANIMATION = new Sprite[]{Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3};
    private static final Sprite DEAD_SPRITE = Sprite.balloom_dead;
    private static final AI AI_LEVEL = new AILow();

    public Balloom(double x, double y) {
        super(x, y, SPEED);
        this.point = POINT;
        this.enemyAnimation = ANIMATION;
        this.deadSprite = DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }
}
