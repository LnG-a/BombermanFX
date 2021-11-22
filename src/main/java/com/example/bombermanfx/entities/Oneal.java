package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public class Oneal extends MovableEntity{
    private long time=System.currentTimeMillis();
    private int random=(int) (Math.random()*4);;

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.speed=0;
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
    }

    @Override
    public void dead(Bomberman game) {
        game.getEntities().remove(this);
    }
}
