package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AIHigh;
import com.example.bombermanfx.graphics.Sprite;

//Second Mode after an Ovape died
public class OvapeReborn extends Enemy{
    private static final double SPEED = FAST;
    private static final int POINT = 700;
    private static final Sprite[] ANIMATION = new Sprite[]{Sprite.ovape_reborn_right_1, Sprite.ovape_reborn_right_2, Sprite.ovape_reborn_right_3, Sprite.ovape_reborn_left_1, Sprite.ovape_reborn_left_2, Sprite.ovape_reborn_left_3};
    private static final Sprite DEAD_SPRITE = Sprite.ovape_reborn_dead;
    private static final AI AI_LEVEL = new AIHigh();

    public OvapeReborn(double x, double y) {
        super(x, y, SPEED);
        this.point = POINT;
        this.enemyAnimation = ANIMATION;
        this.deadSprite = DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }
}
