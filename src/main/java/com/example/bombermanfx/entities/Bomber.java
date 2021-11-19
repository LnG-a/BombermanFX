package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Bomber extends MovableEntity {
    private int life;

    private int flameLength;
    private int numberOfBombs;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.speed=0.70;
        this.flameLength=2  ;
        this.numberOfBombs=2;
        this.life=3;
    }

    @Override
    public void update(Bomberman game) {
        game.getCanvas().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        moveRight(game);
                        break;
                    case LEFT:
                        moveLeft(game);
                        break;
                    case UP:
                        moveUp(game);
                        break;
                    case DOWN:
                        moveDown(game);
                        break;
                    case SPACE:
                        if (canCreateBomb()){
                            game.getEntities().add(createBomb());
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void dead(Bomberman game) {

    }

    public Bomb createBomb(){
        int xBomb= (int) this.x;
        int yBomb= (int) this.y;
        double next = x%1;
        if (next>=0.5) xBomb++;
        next=this.y%1;
        if (next>=0.5) yBomb++;
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

    public boolean canCreateBomb(){
        return numberOfBombs>Bomb.currentBomb;
    }
}