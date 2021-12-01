package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;


public  class Item extends Entity{
    public static final int FLAMEITEM =0;
    public static final int BOMBITEM =1;
    public static final int SPEEDITEM =2;

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
            powerUp(game.getPlayer());
            game.getEntities().remove(this);
        }
    }

    private void powerUp(Bomber player) {
        switch (type){
            case  FLAMEITEM:
                if (player.getFlameLength() < Bomber.MAXFLAMELENGTH) player.setFlameLength(player.getFlameLength() + 1);
                break;
            case SPEEDITEM:
                if (player.getSpeed() < Bomber.MAXSPEED ) player.setSpeed(player.getSpeed() + 0.02);
                break;
            case BOMBITEM:
                if (player.getNumberOfBombs() < Bomber.MAXBOMBS ) player.setNumberOfBombs(player.getNumberOfBombs() + 1);
                break;
        }
    }

    @Override
    public void dead(Bomberman game) {

    }

}
