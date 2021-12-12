package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AIMedium;
import com.example.bombermanfx.graphics.Sprite;

//ID: 3
public class RedPontan extends Enemy{
    private static final double SPEED=0.025;
    private static final int POINT=200;
    private static final Sprite[] ANIMATION= new Sprite[]{Sprite.red_pontan_right_1,Sprite.red_pontan_right_2,Sprite.red_pontan_right_3,Sprite.red_pontan_left_1,Sprite.red_pontan_left_2,Sprite.red_pontan_left_3};
    private static final Sprite DEAD_SPRITE = Sprite.red_pontan_dead;
    private static final AI AI_LEVEL= new AIMedium();

    public RedPontan(double x, double y) {
        super(x, y,SPEED);
        this.point=POINT;
        this.enemyAnimation= ANIMATION;
        this.deadSprite= DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }

    @Override
    public void dead(){
        this.destroyed=true;
    }
}