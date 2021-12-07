package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;

public class Oneal extends MovableEntity{
    private long time=System.currentTimeMillis();
    private int random=(int) (Math.random()*4);;

    public Oneal(double x, double y) {
        super(x, y);
        this.img=Sprite.oneal_left1.getFxImage();
    }


    @Override
    public void update(Bomberman game) {
        if (time<System.currentTimeMillis()-4000){
            random=(int) (Math.random()*4);
            time=System.currentTimeMillis();
        }
        if (random==MovableEntity.LEFT) moveLeft(game);
        if (random==MovableEntity.RIGHT) moveRight(game);
        if (random==MovableEntity.UP) moveUp(game);
        if (random==MovableEntity.DOWN) moveDown(game);
        for (Entity i:game.getEntities()){
            if (this.checkCollide(i)&&i.getClass().equals(Flame.class)) {
                game.getEntities().remove(this);
                game.enemies--;
                break;
            }
        }
    }

    @Override
    public void dead(Bomberman game) {
    }
}
