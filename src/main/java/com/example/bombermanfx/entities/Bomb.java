package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;

public class Bomb extends Entity {
    public static int currentBomb = 0;

    private final long time;
    private final int flameLength;

    public Bomb(int x, int y, Image img, int flameLength) {
        super(x, y, img);
        time = System.currentTimeMillis();
        this.flameLength = flameLength;
        currentBomb++;
    }

    @Override
    public void update(Bomberman game) {
        if (time < System.currentTimeMillis() - 2000) explode(game);
        else {
            for (Entity i:game.getEntities()){
                if (this.checkCollide(i)&&i.getClass().equals(Flame.class)) {
                    explode(game);
                    break;
                }
            }
        }
        if (checkPassable(game)) setPassable(false);
    }

    @Override
    public void dead(Bomberman game) {
        explode(game);
    }

    private boolean checkPassable(Bomberman game) {
        return game.getPlayer().getX() >= x + 1
                || game.getPlayer().getY() >= y + 1
                || game.getPlayer().getX() + 1 <= x
                || game.getPlayer().getY() + 1 <= y;
    }


    private void explode(Bomberman game) {
        currentBomb--;
        int xBomb = (int) this.x;
        int yBomb = (int) this.y;
        game.getEntities().remove(this);
        game.getEntities().add(new Flame(xBomb, yBomb, Sprite.flame));

        for (int i = xBomb - 1; i >= xBomb - flameLength; i--) {
            Entity a = game.getAt(i, yBomb);
            if (a!=null){
                a.dead(game);
                break;
            }
            if (i == xBomb - flameLength) {
                game.getEntities().add(new Flame(i, yBomb, Sprite.flameLeft));
            } else {
                game.getEntities().add(new Flame(i, yBomb, Sprite.flameHorizontal));
            }

        }

        for (int i = xBomb + 1; i <= xBomb + flameLength; i++) {
            Entity a = game.getAt(i, yBomb);
            if (a!=null){
                a.dead(game);
                break;
            }

            if (i == xBomb + flameLength) {
                game.getEntities().add(new Flame(i, yBomb, Sprite.flameRight));
            } else {
                game.getEntities().add(new Flame(i, yBomb, Sprite.flameHorizontal));
            }
        }

        for (int i = yBomb - 1; i >= yBomb - flameLength; i--) {
            Entity a = game.getAt(xBomb, i);
            if (a!=null){
                a.dead(game);
                break;
            }
            if (i == yBomb - flameLength) {
                game.getEntities().add(new Flame(xBomb, i, Sprite.flameUp));
            } else {
                game.getEntities().add(new Flame(xBomb, i, Sprite.flameVertical));
            }
        }

        for (int i = yBomb + 1; i <= yBomb + flameLength; i++) {
            Entity a = game.getAt(xBomb, i);
            if (a!=null){
                a.dead(game);
                break;
            }
            if (i == yBomb + flameLength) {
                game.getEntities().add(new Flame(xBomb, i, Sprite.flameDown));
            } else if (i != yBomb) {
                game.getEntities().add(new Flame(xBomb, i, Sprite.flameVertical));
            }
        }
    }
}
