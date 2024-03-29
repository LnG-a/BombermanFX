package com.example.bombermanfx.entities.bomber;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.Entity;
import com.example.bombermanfx.graphics.Sprite;
import com.example.bombermanfx.sounds.SoundPlayer;

public class Bomb extends Entity {
    public static int currentBomb = 0;

    private final int flameLength;
    
    public Bomb(int x, int y, int flameLength) {
        super(x, y);
        time = System.currentTimeMillis();
        this.flameLength = flameLength;
        currentBomb++;
    }

    @Override
    public void update(Bomberman game) {
        animation+=1;
        if (destroyed){
            explode(game);
            destroyed=false;
        }
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
        this.img=Sprite.movingSprite(Sprite.bomb_2,Sprite.bomb_1,Sprite.bomb,animation,30).getFxImage();// ~6 FPS because in 1s animation +=60 and everytime animation+= 30/3: next frame.
    }

    @Override
    public void dead() {
        this.destroyed=true;
    }

    private boolean checkPassable(Bomberman game) {
        return game.getPlayer().getX() >= x + 1
                || game.getPlayer().getY() >= y + 1
                || game.getPlayer().getX() + 1 <= x
                || game.getPlayer().getY() + 1 <= y;
    }


    public void explode(Bomberman game) {
        //Play sound
        SoundPlayer.explosion();

        //Remove bomb and standardize x,y
        currentBomb--;
        int xBomb = (int) this.x;
        int yBomb = (int) this.y;
        game.getEntities().remove(this);

        //Create flame
        game.getEntities().add(new Flame(xBomb, yBomb,Flame.CENTER));
        for (int i = xBomb - 1; i >= xBomb - flameLength; i--) {
            Entity a = game.getObstacle(i, yBomb);
            if (a!= null&&!a.isPassable()){
                a.dead();
                break;
            }
            if (i == xBomb - flameLength) {
                game.getEntities().add(new Flame(i, yBomb, Flame.LEFT));
            } else {
                game.getEntities().add(new Flame(i, yBomb, Flame.HORIZONTAL));
            }

        }

        for (int i = xBomb + 1; i <= xBomb + flameLength; i++) {
            Entity a = game.getObstacle(i, yBomb);
            if (a!= null&&!a.isPassable()){
                a.dead();
                break;
            }

            if (i == xBomb + flameLength) {
                game.getEntities().add(new Flame(i, yBomb, Flame.RIGHT));
            } else {
                game.getEntities().add(new Flame(i, yBomb, Flame.HORIZONTAL));
            }
        }

        for (int i = yBomb - 1; i >= yBomb - flameLength; i--) {
            Entity a = game.getObstacle(xBomb, i);
            if (a!= null&&!a.isPassable()){
                a.dead();
                break;
            }
            if (i == yBomb - flameLength) {
                game.getEntities().add(new Flame(xBomb, i, Flame.UP));
            } else {
                game.getEntities().add(new Flame(xBomb, i, Flame.VERTICAL));
            }
        }

        for (int i = yBomb + 1; i <= yBomb + flameLength; i++) {
            Entity a = game.getObstacle(xBomb, i);
            if (a!= null&&!a.isPassable()){
                a.dead();
                break;
            }
            if (i == yBomb + flameLength) {
                game.getEntities().add(new Flame(xBomb, i, Flame.DOWN));
            } else if (i != yBomb) {
                game.getEntities().add(new Flame(xBomb, i, Flame.VERTICAL));
            }
        }
    }
}
