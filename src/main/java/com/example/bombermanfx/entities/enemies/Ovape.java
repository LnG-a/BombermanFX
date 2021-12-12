package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AIMedium;
import com.example.bombermanfx.graphics.Sprite;

//ID 6
public class Ovape extends Enemy{
    private static final double SPEED = SLOW;
    private static final int POINT = 1000;
    private static final Sprite[] ANIMATION = new Sprite[]{Sprite.ovape_right_1, Sprite.ovape_right_2, Sprite.ovape_right_3, Sprite.ovape_left_1, Sprite.ovape_left_2, Sprite.ovape_left_3};
    private static final Sprite DEAD_SPRITE = Sprite.ovape_dead;
    private static final AI AI_LEVEL = new AIMedium();

    public Ovape(double x, double y) {
        super(x, y, SPEED);
        this.point = POINT;
        this.enemyAnimation = ANIMATION;
        this.deadSprite = DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }
}
