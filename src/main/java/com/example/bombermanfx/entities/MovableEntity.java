package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public abstract class MovableEntity extends Entity{
    protected double speed;

    public MovableEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void moveRight(Bomberman game){
        double afterX=x+speed;
        if (canMove(game,afterX,y)||!canMove(game,x,y)) {
            x=afterX;
        } else {
            x=(int) afterX;
        }
    }

    public void moveLeft(Bomberman game){
        double afterX=x-speed;
        if (canMove(game,afterX,y)||!canMove(game,x,y)) {
            x=afterX;
        } else {
            x=(int) afterX+1;
        }
    }

    public void moveUp(Bomberman game){
        double afterY=y-speed;
        if (canMove(game,x,afterY)||!canMove(game,x,y)) {
            y=afterY;
        } else {
            y=(int) afterY+1;
        }
    }

    public void moveDown(Bomberman game){
        double afterY=y+speed;
        if (canMove(game,x,afterY)||!canMove(game,x,y)) {
            y=afterY;
        } else {
            y=(int) afterY;
        }
    }

    public boolean canMoveRight(Bomberman game){
        return false;
    }

    public boolean canMoveLeft(Bomberman game){
        return false;
    }

    public boolean canMoveUp(Bomberman game){
        return false;
    }

    public boolean canMoveDown(Bomberman game){
        return false;
    }

    private boolean canMove(Bomberman game,double x, double y){
        int checkX=(int) x;
        int checkY=(int) y;
        if (x==checkX && y==checkY) {
            return validatePosition(game,checkX,checkY);
        } else if (x!=checkX&& y==checkY){
            return validatePosition(game,checkX,checkY) && validatePosition(game,checkX+1,checkY);
        }
        else if (x==checkX&&y!=checkY){
            return validatePosition(game,checkX,checkY) && validatePosition(game,checkX,checkY+1);
        } else {
            return validatePosition(game,checkX,checkY) && validatePosition(game,checkX,checkY+1)
                    && validatePosition(game,checkX+1,checkY) && validatePosition(game,checkX+1,checkY+1);
        }
    }

}
