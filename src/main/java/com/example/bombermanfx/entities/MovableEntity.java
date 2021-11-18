package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import javafx.scene.image.Image;

public abstract class MovableEntity extends Entity{

    public MovableEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void moveRight();

    public abstract void moveLeft();

    public abstract void moveUp();

    public abstract void moveDown();

    public boolean canMoveRight(Bomberman game){
        return false;
    }

    public boolean canMoveLeft(Bomberman game){
        return false;
    }

    public boolean canMoveUp(Bomberman game){
        return false;
    }

    public boolean canMoveDown(Bomberman game){
        return false;
    }
}
