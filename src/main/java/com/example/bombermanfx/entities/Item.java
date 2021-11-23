package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.image.Image;


public  class Item extends Entity{
    public static final int FLAMEITEM =0;
    public static final int BOMBITEM =1;
    public static final int SPEEDITEM =2;
    private static final Image[] images= new Image[]{Sprite.flameItem,Sprite.bombItem,Sprite.speedItem};

    private final int type;

    public Item(double x, double y, Image img, int type) {
        super(x, y, img);
        this.type=type;
        this.img=images[type];
    }



    @Override
    public void update(Bomberman game) {
        if (checkCollide(game.getPlayer())) {
            powerUp(game.getPlayer());
            game.getEntities().remove(this);
        }
    }

    private void powerUp(Bomber player) {
        if (player.getFlameLength() < Bomber.MAXFLAMELENGTH && type == FLAMEITEM) {
            player.setFlameLength(player.getFlameLength() + 1);
        }
        if (player.getSpeed() < Bomber.MAXSPEED && type == SPEEDITEM) {
            player.setSpeed(player.getSpeed() + 0.05);
        }
        if (player.getNumberOfBombs() < Bomber.MAXBOMBS && type == BOMBITEM) {
            player.setNumberOfBombs(player.getNumberOfBombs() + 1);
        }
    }

    @Override
    public void dead(Bomberman game) {

    }

}
