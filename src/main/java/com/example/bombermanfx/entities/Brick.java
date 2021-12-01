package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;

public class Brick extends Entity{
    public Brick(double x, double y) {
        super(x, y);
        setPassable(false);
        this.img=Sprite.brick.getFxImage();
    }

    @Override
    public void update(Bomberman game) {

    }

    @Override
    public void dead(Bomberman game) {
        createItems(game);
        game.getEntities().remove(this);
    }

    private void createItems(Bomberman game) {
        int randomType = (int)(Math.random()*3);
        if (randomType<=2){
            game.getEntities().add(new Item(x,y, randomType));
        }
    }
}
