package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public class Brick extends Entity{
    public Brick(double x, double y, Image img) {
        super(x, y, img);
        setPassable(false);
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
        int randomType = (int)(Math.random()*10);
        if (randomType<=2){
            game.getEntities().add(new Item(x,y,null,randomType));
        }
    }
}
