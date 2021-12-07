package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;

public class Portal extends Entity{
    public Portal(double x, double y) {
        super(x, y);
        this.img=Sprite.portal.getFxImage();
    }

    @Override
    public void update(Bomberman game) {
        if (game.enemies==0&&this.checkCollide(game.getPlayer())){
            game.setLevelUp(true);
            game.getEntities().remove(this);
        }
    }

    @Override
    public void dead(Bomberman game) {

    }


}
