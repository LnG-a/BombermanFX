package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;

public class Portal extends Entity{
    public Portal(double x, double y) {
        super(x, y);
        this.img=Sprite.portal.getFxImage();
    }

    @Override
    public void update(Bomberman game) {

    }

    @Override
    public void dead(Bomberman game) {

    }
}
