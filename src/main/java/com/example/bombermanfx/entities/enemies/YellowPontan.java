package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AILow;
import com.example.bombermanfx.graphics.Sprite;

//ID: 11
//Generated when a Red Pontan died
public class YellowPontan extends Enemy{
    private static final double SPEED=FAST;
    private static final int POINT=200;
    private static final Sprite[] ANIMATION= new Sprite[]{Sprite.yellow_pontan_right_1,Sprite.yellow_pontan_right_2,Sprite.yellow_pontan_right_3,Sprite.yellow_pontan_left_1,Sprite.yellow_pontan_left_2,Sprite.yellow_pontan_left_3};
    private static final Sprite DEAD_SPRITE = Sprite.yellow_pontan_dead;
    private static final AI AI_LEVEL= new AILow();


    public YellowPontan(double x, double y) {
        super(x, y,SPEED);
        this.point=POINT;
        this.enemyAnimation= ANIMATION;
        this.deadSprite= DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }
}
