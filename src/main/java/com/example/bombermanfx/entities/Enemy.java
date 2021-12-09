package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.AI.AI;
import com.example.bombermanfx.graphics.Sprite;

public abstract class Enemy extends MovableEntity {
    protected AI ai;
    protected int steps;
    protected final int MAX_STEP;
    protected Sprite[] enemyAnimation;

    public Enemy(double x, double y, double speed) {
        super(x, y);
        this.speed = speed;
        MAX_STEP = (int)(1 / speed);
        steps = 0;
    }

    @Override
    public void update(Bomberman game) {
        if (animation<Integer.MAX_VALUE-1) animation++;
        else animation=0;
        calculateMove(game);
        //moveLeft(game);
        if (isMovingRight) {
            this.img = Sprite.movingSprite(enemyAnimation[0], enemyAnimation[1], enemyAnimation[2], animation, 20).getFxImage();
        }
        if (isMovingLeft) {
            this.img = Sprite.movingSprite(enemyAnimation[3], enemyAnimation[4], enemyAnimation[5], animation, 20).getFxImage();
        }
        if (isMovingUp) {
            this.img = Sprite.movingSprite(enemyAnimation[0], enemyAnimation[1], enemyAnimation[2], animation, 20).getFxImage();
        }
        if (isMovingDown) {
            this.img = Sprite.movingSprite(enemyAnimation[0], enemyAnimation[1], enemyAnimation[2], animation, 20).getFxImage();
        }
        dead(game);
    }

    @Override
    public void dead(Bomberman game) {
        for (Entity i:game.getEntities()){
            if (this.checkCollide(i)&&i.getClass().equals(Flame.class)) {
                game.getEntities().remove(this);
                game.enemies--;
                break;
            }
        }
    }

    public void calculateMove(Bomberman game) {
        if (steps == 0) {
            int direction = ai.calculateDirection();
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
        }
        else {
            int xa = 0, ya = 0;
            if (isMovingUp) moveUp(game);
            else if (isMovingDown) moveDown(game);
            else if (isMovingLeft) moveLeft(game);
            else if (isMovingRight) moveRight(game);
            steps--;
        }

    }
}
