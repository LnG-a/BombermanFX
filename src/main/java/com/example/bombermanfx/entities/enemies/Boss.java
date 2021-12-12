package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.bomber.Bomb;
import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.enemies.AI.AIHigh;
import com.example.bombermanfx.graphics.Sprite;

//ID 10
//Only die if it's the last one
public class Boss extends Enemy{
    private static final double SPEED = FAST;
    private static final int POINT = 1500;
    private static final Sprite[] ANIMATION = new Sprite[]{Sprite.boss_down_1, Sprite.boss_down_2, Sprite.balloom_left1, Sprite.balloom_left2,Sprite.boss_right_1,Sprite.boss_right_2, Sprite.boss_up_1, Sprite.boss_up_2};
    private static final Sprite DEAD_SPRITE = Sprite.boss_down_1;
    private static final AI AI_LEVEL = new AIHigh();

    public Boss(double x, double y) {
        super(x, y, SPEED);
        this.point = POINT;
        this.enemyAnimation = ANIMATION;
        this.deadSprite = DEAD_SPRITE;
        this.ai = AI_LEVEL;
    }

    @Override
    public void update(Bomberman game) {
        if (animation < Integer.MAX_VALUE - 1) animation++;
        else animation = 0;

        if (destroyed) {
            destroyed=false;
            if (game.enemies==1) {
                if (animation > animationDead) {
                    animation = -60;
                }
                //Dead animation
                if (animation >= animationDead) {
                    game.getEntities().remove(this);
                    game.enemies--;
                    game.SCORE += this.point;
                } else if (animation >= 0) {
                    this.img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animation, animationDead).getFxImage();
                } else this.img = deadSprite.getFxImage();
            }
        } else {
            calculateMove(game);
            if (isMovingDown) this.img=Sprite.movingSprite(Sprite.boss_down_1,Sprite.boss_down_2,animation,35).getFxImage();
            if (isMovingUp) this.img=Sprite.movingSprite(Sprite.boss_up_1,Sprite.boss_up_2,animation,35).getFxImage();
            if (isMovingRight) this.img=Sprite.movingSprite(Sprite.boss_right_1,Sprite.boss_right_2,animation,35).getFxImage();
            if (isMovingLeft) this.img=Sprite.movingSprite(Sprite.boss_left_1,Sprite.boss_left_2,animation,35).getFxImage();
            //Check dead by flame
            if (this.checkCollide(game.getPlayer())) game.getPlayer().dead();
            checkDeadByFlame(game);
        }
    }
}
