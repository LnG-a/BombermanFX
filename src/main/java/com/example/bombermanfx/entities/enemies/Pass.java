package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AIHigh;
import com.example.bombermanfx.graphics.Sprite;

//ID 7
public class Pass extends Enemy{
    private static final double SPEED = FAST;
    private static final int POINT = 500;
    private static final Sprite[] ANIMATION = new Sprite[]{Sprite.pass_right_1, Sprite.pass_right_2, Sprite.pass_right_3, Sprite.pass_left_1, Sprite.pass_left_2, Sprite.pass_left_3};
    private static final Sprite DEAD_SPRITE = Sprite.pass_dead;
    private static final AI AI_LEVEL = new AIHigh();

    public Pass(double x, double y) {
        super(x, y, SPEED);
        this.point = POINT;
        this.enemyAnimation = ANIMATION;
        this.deadSprite = DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }
}
