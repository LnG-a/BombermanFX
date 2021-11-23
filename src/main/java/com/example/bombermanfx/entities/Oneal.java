package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public class Oneal extends MovableEntity{
    private long time=System.currentTimeMillis();
    private int random=(int) (Math.random()*4);;

    public Oneal(double x, double y, Image img) {
        super(x, y, img);
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
                break;
            }
        }
    }

    @Override
    public void dead(Bomberman game) {
    }
}