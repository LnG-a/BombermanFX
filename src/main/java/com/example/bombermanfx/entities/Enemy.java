package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.AI.AI;

public abstract class Enemy extends MovableEntity {
    protected AI ai;
    protected int steps;
    protected final int MAX_STEP;

    public Enemy(double x, double y, double speed) {
        super(x, y);
        this.speed = speed;
        MAX_STEP = (int)(1 / speed);
        steps = 0;
    }

    @Override
    public void update(Bomberman game) {
        calculateMove(game);
        //moveLeft(game);
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
