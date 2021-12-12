package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import com.example.bombermanfx.sounds.SoundPlayer;

public class Portal extends Entity{
    public Portal(double x, double y) {
        super(x, y);
        this.img=Sprite.portal.getFxImage();
    }

    @Override
    public void update(Bomberman game) {
        if (game.enemies==0&&this.checkCollide(game.getPlayer())){
            SoundPlayer.levelUp();
            game.setLevelUp(true);
            game.getEntities().remove(this);
        }
    }

}
