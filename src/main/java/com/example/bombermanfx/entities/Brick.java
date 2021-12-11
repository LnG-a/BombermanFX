package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;

public class Brick extends Entity{
    public Brick(double x, double y) {
        super(x, y);
        setPassable(false);
        this.img=Sprite.brick.getFxImage();
    }

    @Override
    public void update(Bomberman game) {
        if (this.destroyed) {
            if (System.currentTimeMillis()>time+300) {
                createItems(game);
                game.getEntities().remove(this);
                game.getStillEntities().add(new Grass(x,y));
            }
            else if (System.currentTimeMillis()>time+200) this.img=Sprite.brick_exploded_2.getFxImage();
            else if (System.currentTimeMillis()>time+100) this.img=Sprite.brick_exploded_1.getFxImage();
            else this.img=Sprite.brick_exploded.getFxImage();
        } else time=System.currentTimeMillis();
    }

    private void createItems(Bomberman game) {
        int randomType = (int)(Math.random()*20);
        if (randomType<=2){
            game.getEntities().add(new Item(x,y, randomType));
        }
    }
}
