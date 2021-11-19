package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public class Flame extends Entity{
    private final long time;

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.time=System.currentTimeMillis();
    }

    @Override
    public void update(Bomberman game) {
        if (time < System.currentTimeMillis()-500) {
            this.setDestroyed(true);
        }
    }
}
