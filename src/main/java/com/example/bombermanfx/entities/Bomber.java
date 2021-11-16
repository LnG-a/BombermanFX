package com.example.bombermanfx.entities;

import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Bomber extends MovableEntity {
    private int life;
    private int speed;
    private int flameLength;
    private int numberOfBombs;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.speed=1;
        this.flameLength=1;
        this.numberOfBombs=1;
        this.life=3;
    }

    @Override
    public void update() {

    }

    @Override
    public void moveRight(){
        this.x+=speed* Sprite.SCALED_SIZE;
    }

    @Override
    public void moveLeft(){
        this.x-=speed* Sprite.SCALED_SIZE;
    }

    @Override
    public void moveUp(){
        this.y-=speed* Sprite.SCALED_SIZE;
    }

    @Override
    public void moveDown(){
        this.y+=speed* Sprite.SCALED_SIZE;
    }

    public void setFlameLength(int flameLength) {
        this.flameLength = flameLength;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFlameLength() {
        return flameLength;
    }

    public int getLife() {
        return life;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public int getSpeed() {
        return speed;
    }
}