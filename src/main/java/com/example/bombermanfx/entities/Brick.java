package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;

public class Brick extends Entity{
    private boolean destroyed=false;
    private long time;
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
            }
            else if (System.currentTimeMillis()>time+200) this.img=Sprite.brick_exploded_2.getFxImage();
            else if (System.currentTimeMillis()>time+100) this.img=Sprite.brick_exploded_1.getFxImage();
            else this.img=Sprite.brick_exploded.getFxImage();
        } else time=System.currentTimeMillis();
    }

    @Override
    public void dead(Bomberman game) {
        this.destroyed =true;
    }

    private void createItems(Bomberman game) {
        int randomType = (int)(Math.random()*20);
        if (randomType<=2){
            game.getEntities().add(new Item(x,y, randomType));
        }
    }
}
