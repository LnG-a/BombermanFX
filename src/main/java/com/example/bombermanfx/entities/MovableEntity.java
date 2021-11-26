package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public abstract class MovableEntity extends Entity {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    protected double speed;
    protected boolean right=false;
    protected boolean left=false;
    protected boolean up=false;
    protected boolean down=false;

    public MovableEntity(double x, double y, Image img) {
        super(x, y, img);
    }

    public void moveRight(Bomberman game) {
        double afterX = x + speed;
        if (canMove(game, afterX, y) || !canMove(game, x, y)) {
            x = afterX;
        } else {
            x = (int) afterX;
        }
    }

    public void moveLeft(Bomberman game) {
        double afterX = x - speed;
        if (canMove(game, afterX, y) || !canMove(game, x, y)) {
            x = afterX;
        } else {
            x = (int) afterX + 1;
        }
    }

    public void moveUp(Bomberman game) {
        double afterY = y - speed;
        if (canMove(game, x, afterY) || !canMove(game, x, y)) {
            y = afterY;
        } else {
            y = (int) afterY + 1;
        }
    }

    public void moveDown(Bomberman game) {
        double afterY = y + speed;
        if (canMove(game, x, afterY) || !canMove(game, x, y)) {
            y = afterY;
        } else {
            y = (int) afterY;
        }
    }

    private boolean canMove(Bomberman game, double x, double y) {
        int checkX = (int) x;
        int checkY = (int) y;
        if (x == checkX && y == checkY) {
            return game.validatePosition(checkX, checkY);
        } else if (x != checkX && y == checkY) {
            return game.validatePosition(checkX, checkY) && game.validatePosition(checkX + 1, checkY);
        } else if (x == checkX && y != checkY) {
            return game.validatePosition(checkX, checkY) && game.validatePosition(checkX, checkY + 1);
        } else {
            return game.validatePosition(checkX, checkY) && game.validatePosition(checkX, checkY + 1)
                    && game.validatePosition(checkX + 1, checkY) && game.validatePosition(checkX + 1, checkY + 1);
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }
}
