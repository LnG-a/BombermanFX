package com.example.bombermanfx.entities.bomber;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.Entity;
import com.example.bombermanfx.graphics.Sprite;
import com.example.bombermanfx.sounds.SoundPlayer;
import javafx.scene.image.Image;


public  class Item extends Entity {
    public static final int FLAME_ITEM =0;
    public static final int BOMB_ITEM =1;
    public static final int SPEED_ITEM =2;

    private static final Image[] itemImages= new Image[]{Sprite.powerup_flames.getFxImage(),Sprite.powerup_bombs.getFxImage(),Sprite.powerup_speed.getFxImage()};

    private final int type;

    public Item(double x, double y, int type) {
        super(x, y);
        this.type=type;
        this.img=itemImages[type];
    }

    @Override
    public void update(Bomberman game) {
        if (checkCollide(game.getPlayer())) {
            //Sound effects
            SoundPlayer.powerUp();
            powerUp(game.getPlayer());
            game.SCORE+=50;
            game.getEntities().remove(this);

        }
    }

    private void powerUp(Bomber player) {
        switch (type){
            case  FLAME_ITEM:
                if (player.getFlameLength() < Bomber.MAX_FLAME_LENGTH) player.setFlameLength(player.getFlameLength() + 1);
                break;
            case SPEED_ITEM:
                if (player.getSpeed() < Bomber.MAX_SPEED ) player.setSpeed(player.getSpeed() + 0.01);
                break;
            case BOMB_ITEM:
                if (player.getNumberOfBombs() < Bomber.MAX_BOMBS ) player.setNumberOfBombs(player.getNumberOfBombs() + 1);
                break;
        }
    }

}
