package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public abstract class MovableEntity extends Entity {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    protected double speed;
    protected boolean isMovingRight=false;
    protected boolean isMovingLeft=false;
    protected boolean isMovingUp=false;
    protected boolean isMovingDown=false;

    protected double fixingNumber=0;

    public MovableEntity(double x, double y) {
        super(x, y);
    }

    public void moveRight(Bomberman game) {
        double afterX = x + speed;
        //if (afterX>=(int)afterX+1-fixingNumber) afterX=(int) afterX+1;
        if (y<=(int) y+fixingNumber) y =(int) y;
        else if (y>=(int)y+1-fixingNumber) y=(int) y+1;
        if (canMove(game, afterX, y)) {
            x = afterX;
        } else {
            x = (int) afterX;
        }
    }

    public void moveLeft(Bomberman game) {
        double afterX = x - speed;
        if (y<=(int) y+fixingNumber) y =(int) y;
        else if (y>=(int)y+1-fixingNumber) y=(int) y+1;
        //if (afterX<=(int)afterX+fixingNumber) afterX=(int)afterX;
        if (canMove(game, afterX, y)) {
            x = afterX;
        } else {
            x = (int) afterX + 1;
        }
    }

    public void moveUp(Bomberman game) {
        double afterY = y - speed;
        if (x>=(int)x+1-fixingNumber) x = (int)x +1;
        else if (x<=(int)x+fixingNumber+0.05) x =(int) x;
        //if (afterY<=(int)afterY+fixingNumber) afterY=(int)afterY;
        if (canMove(game, x, afterY)) {
            y = afterY;
        } else {
            y = (int) afterY + 1;
        }
    }

    public void moveDown(Bomberman game) {
        double afterY = y + speed;
        if (x>=(int)x+1-fixingNumber) x = (int)x +1;
        else if (x<=(int)x+fixingNumber+0.05) x =(int) x;
        //if (afterY>=(int)afterY+1-fixingNumber) afterY=(int)afterY+1;
        if (canMove(game, x, afterY)) {
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

    public void setX(double x){
        this.x=x;
    }
    public void setY(double y){
        this.y=y;
    }
}
