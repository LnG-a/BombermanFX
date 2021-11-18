package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;

public class Bomb extends Entity {
    private long time;
    private int flameLength;

    public Bomb(int xUnit, int yUnit, Image img, int flameLength) {
        super(xUnit, yUnit, img);
        time = System.currentTimeMillis();
        this.flameLength = flameLength;
        setPassable(false);
    }

    @Override
    public void update(Bomberman game) {
        if (time < System.currentTimeMillis() - 2000) {
            explode(game);
            setDestroyed(true);
        }
    }

    private void explode(Bomberman game) {
        int xBomb = this.x / Sprite.SCALED_SIZE;
        int yBomb = this.y / Sprite.SCALED_SIZE;
        game.getEntities().add(new Flame(xBomb, yBomb, Sprite.flame));
        for (int i = xBomb - flameLength; i <= xBomb + flameLength; i++) {
            if (i == xBomb - flameLength) {
                game.getEntities().add(new Flame(xBomb - flameLength, yBomb, Sprite.flameLeft));
            } else if (i == xBomb + flameLength) {
                game.getEntities().add(new Flame(xBomb + flameLength, yBomb, Sprite.flameRight));
            } else if (i != xBomb) {
                game.getEntities().add(new Flame(i, yBomb, Sprite.flameHorizontal));
            }
        }
        for (int i = yBomb - flameLength; i <= yBomb + flameLength; i++) {
            if (i == yBomb - flameLength) {
                game.getEntities().add(new Flame(xBomb, yBomb - flameLength, Sprite.flameUp));
            } else if (i == yBomb + flameLength) {
                game.getEntities().add(new Flame(xBomb, yBomb + flameLength, Sprite.flameDown));
            } else if (i != yBomb) {
                game.getEntities().add(new Flame(xBomb, i, Sprite.flameVertical));
            }
        }
    }
}
