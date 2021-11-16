package com.example.bombermanfx.entities;

import javafx.scene.image.Image;

public abstract class MovableEntity extends Entity{

    public MovableEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void moveRight();

    public abstract void moveLeft();

    public abstract void moveUp();

    public abstract void moveDown();
}
