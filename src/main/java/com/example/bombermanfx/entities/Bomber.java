package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Bomber extends MovableEntity {
    public static final double MAXSPEED=0.25;
    public static final int MAXFLAMELENGTH=8;
    public static final int MAXBOMBS=8;

    private int life;
    private int flameLength;
    private int numberOfBombs;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.speed=0.09;
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
                        right=true;
                        /*left=false;
                        up=false;
                        down=false;*/
                        break;
                    case LEFT:
//                        right=false;
                        left=true;
                        /*up=false;
                        down=false;*/
                        break;
                    case UP:
                        /*right=false;
                        left=false;*/
                        up=true;
                        /*down=false;*/
                        break;
                    case DOWN:
                        /*right=false;
                        left=false;
                        up=false;*/
                        down=true;
                        break;
                    case SPACE:
                        if (canCreateBomb()){
                            createBomb(game);
                        }
                        break;
                }
            }
        });
        game.getCanvas().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        right=false;
                        break;
                    case LEFT:
                        left=false;
                        break;
                    case UP:
                        up=false;
                        break;
                    case DOWN:
                        down=false;
                        break;
                }
            }
        });
        if (right) moveRight(game);
        if (left) moveLeft(game);
        if (down) moveDown(game);
        if (up) moveUp(game);
    }

    @Override
    public void dead(Bomberman game) {

    }

    public void createBomb(Bomberman game){
        int xBomb= (int) this.x;
        int yBomb= (int) this.y;
        double next = x%1;
        if (next>=0.5) xBomb++;
        next=this.y%1;
        if (next>=0.5) yBomb++;
        if( game.getAt(xBomb,yBomb)==null){
            game.getEntities().add(new Bomb(xBomb,yBomb,Sprite.bomb.getFxImage(),this.flameLength));
        }
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

    public int getFlameLength() {
        return flameLength;
    }

    public int getLife() {
        return life;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public boolean canCreateBomb(){
        return numberOfBombs>Bomb.currentBomb;
    }
}