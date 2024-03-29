package com.example.bombermanfx.entities.enemies;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.bomber.Bomb;
import com.example.bombermanfx.entities.enemies.AI.AI;
import com.example.bombermanfx.entities.MovableEntity;
import com.example.bombermanfx.graphics.Sprite;

public abstract class Enemy extends MovableEntity {
    protected static final double SLOWEST=0.01;
    protected static final double SLOW=0.02;
    protected static final double NORMAL=0.025;
    protected static final double FAST=0.05;
    protected static final double FASTEST=0.1;

    protected AI ai;
    protected int steps;
    protected final int MAX_STEP;
    protected Sprite[] enemyAnimation;
    protected Sprite deadSprite;
    protected boolean spriteDirection = true;
    protected int life = 1;
    protected int point;

    public Enemy(double x, double y, double speed) {
        super(x, y);
        this.speed = speed;
        MAX_STEP = (int) (1 / speed);
        steps = 0;
    }

    @Override
    public void update(Bomberman game) {
        if (animation < Integer.MAX_VALUE - 1) animation++;
        else animation = 0;

        if (destroyed) {
            if (animation > animationDead) {
                animation = -60;
            }
            if (animation >= animationDead) {
                game.getEntities().remove(this);
                game.enemies--;
                if (this.getClass().equals(RedPontan.class)) {
                    game.getEntities().add(new YellowPontan(autoFix(x),autoFix(y)));
                    game.getEntities().add(new YellowPontan(autoFix(x),autoFix(y)));
                    game.enemies+=2;
                } else if (this.getClass().equals(Doria.class)){
                    Bomb a =new Bomb(autoFix(x),autoFix(y), game.LEVEL);
                    a.explode(game);
                } else if (this.getClass().equals(Ovape.class)){
                    game.getEntities().add(new OvapeReborn(autoFix(x),autoFix(y)));
                    game.enemies++;
                }
                game.SCORE+=this.point;
            } else if (animation >= 0) {
                if (this.getClass().equals(Ovape.class)) this.img= Sprite.movingSprite(Sprite.ovape_reborn_transform_1, Sprite.ovape_reborn_transform_2, Sprite.ovape_reborn_dead, animation, animationDead).getFxImage();
                else this.img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animation, animationDead).getFxImage();
            } else this.img = deadSprite.getFxImage();
        } else {
            calculateMove(game);
            if (spriteDirection)
                this.img = Sprite.movingSprite(enemyAnimation[0], enemyAnimation[1], enemyAnimation[2], animation, 35).getFxImage();
            else
                this.img = Sprite.movingSprite(enemyAnimation[3], enemyAnimation[4], enemyAnimation[5], animation, 35).getFxImage();
            //Check dead by flame
            if (this.checkCollide(game.getPlayer())) game.getPlayer().dead();
            checkDeadByFlame(game);
        }
    }

    public void calculateMove(Bomberman game) {
        if (steps == 0) {
            int direction = ai.calculateDirection(game, this.x, this.y);
            switch (direction) {
                case 0 -> {
                    isMovingUp = true;
                    isMovingDown = false;
                    isMovingLeft = false;
                    isMovingRight = false;
                }
                case 1 -> {
                    isMovingUp = false;
                    isMovingDown = true;
                    isMovingLeft = false;
                    isMovingRight = false;
                }
                case 2 -> {
                    isMovingUp = false;
                    isMovingDown = false;
                    isMovingLeft = true;
                    isMovingRight = false;
                }
                case 3 -> {
                    isMovingUp = false;
                    isMovingDown = false;
                    isMovingLeft = false;
                    isMovingRight = true;
                }
            }
            steps = MAX_STEP;
        } else {
            if (isMovingUp) moveUp(game);
            else if (isMovingDown) moveDown(game);
            else if (isMovingLeft) {
                moveLeft(game);
                spriteDirection = false;
            } else if (isMovingRight) {
                moveRight(game);
                spriteDirection = true;
            }

            steps--;
        }
    }
}
