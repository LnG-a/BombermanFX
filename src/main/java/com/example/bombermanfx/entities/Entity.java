package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;

    protected Image img;

    protected boolean passable;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
        passable = true;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x*Sprite.SCALED_SIZE, y*Sprite.SCALED_SIZE);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract void update(Bomberman game);

    public abstract void dead(Bomberman game);

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean validatePosition(Bomberman game,int x, int y){
        for (Entity i: game.getEntities()){
            if (i.getX()==x && i.getY()==y &&!i.passable) return false;
        }
        for (Entity i: game.getStillEntities()){
            if (i.getX()==x&&i.getY()==y&& !i.passable) return false;
        }
        return true;
    }

}
