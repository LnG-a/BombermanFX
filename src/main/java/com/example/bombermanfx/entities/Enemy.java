package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.AI.AI;
import com.example.bombermanfx.graphics.Sprite;

public abstract class Enemy extends MovableEntity {
    protected AI ai;
    protected int steps;
    protected final int MAX_STEP;
    protected Sprite[] enemyAnimation;
    protected boolean spriteDirection = true;
    protected int life = 2;

    public Enemy(double x, double y, double speed) {
        super(x, y);
        this.speed = speed;
        MAX_STEP = (int)(1 / speed);
        steps = 0;
    }

    @Override
    public void update(Bomberman game) {
        //System.out.println("Moving : " + x + " " + y);
        if (animation<Integer.MAX_VALUE-1) animation++;
        else animation=0;
        calculateMove(game);
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
        }
        else {
            if (isMovingUp) moveUp(game);
            else if (isMovingDown) moveDown(game);
            else if (isMovingLeft) {
                moveLeft(game);
                spriteDirection = false;
            }
            else if (isMovingRight) {
                moveRight(game);
                spriteDirection = true;
            }
            if (spriteDirection) this.img = Sprite.movingSprite(enemyAnimation[0], enemyAnimation[1], enemyAnimation[2], animation, 20).getFxImage();
            else this.img = Sprite.movingSprite(enemyAnimation[3], enemyAnimation[4], enemyAnimation[5], animation, 20).getFxImage();
                steps--;
        }

    }
}
