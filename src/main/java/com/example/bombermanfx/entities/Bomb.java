package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;

public class Bomb extends Entity {
    public static int currentBomb=0;

    private final long time;
    private final int flameLength;

    public Bomb(int xUnit, int yUnit, Image img, int flameLength) {
        super(xUnit, yUnit, img);
        time = System.currentTimeMillis();
        this.flameLength = flameLength;
        currentBomb++;
    }

    @Override
    public void update(Bomberman game) {
        if (time < System.currentTimeMillis() - 2000) explode(game);
        if (checkPassable(game)) setPassable(false);
    }

    private boolean checkPassable(Bomberman game) {
        return game.getPlayer().getX()>=x+1
                ||game.getPlayer().getY()>=y+1
                ||game.getPlayer().getX()+1<=x
                ||game.getPlayer().getY()+1<=y;
    }

    private void explode(Bomberman game) {
        setDestroyed(true);
        currentBomb--;

        int xBomb = (int) this.x;
        int yBomb = (int) this.y;
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
