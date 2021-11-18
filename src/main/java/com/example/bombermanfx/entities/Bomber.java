package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Bomber extends MovableEntity {
    private int life;
    private double speed;
    private int flameLength;
    private int numberOfBombs;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.speed=0.5;
        this.flameLength=2  ;
        this.numberOfBombs=1;
        this.life=3;
    }

    @Override
    public void update(Bomberman game) {
        game.getCanvas().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        moveRight();
                        break;
                    case LEFT:
                        moveLeft();
                        break;
                    case UP:
                        moveUp();
                        break;
                    case DOWN:
                        moveDown();
                        break;
                    case SPACE:
                        game.getEntities().add(createBomb());
                        break;
                }
            }
        });
    }

    @Override
    public void moveRight(){
        this.x+=speed* Sprite.SCALED_SIZE;
    }

    @Override
    public void moveLeft(){
        this.x-=speed* Sprite.SCALED_SIZE;
    }

    @Override
    public void moveUp(){
        this.y-=speed* Sprite.SCALED_SIZE;
    }

    @Override
    public void moveDown(){
        this.y+=speed* Sprite.SCALED_SIZE;
    }

    public Bomb createBomb(){
        int xBomb=this.x/Sprite.SCALED_SIZE;
        int yBomb=this.y/Sprite.SCALED_SIZE;
        int next=this.x%Sprite.SCALED_SIZE;
        if (next>=Sprite.SCALED_SIZE/2) xBomb++;
        next=this.y%Sprite.SCALED_SIZE;
        if (next>=Sprite.SCALED_SIZE/2) yBomb++;
        return new Bomb(xBomb,yBomb,Sprite.bomb,this.flameLength);
    }

    public void setFlameLength(int flameLength) {
        this.flameLength = flameLength;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getFlameLength() {
        return flameLength;
    }

    public int getLife() {
        return life;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public double getSpeed() {
        return speed;
    }
}