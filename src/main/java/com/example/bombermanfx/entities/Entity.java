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
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    protected boolean destroyed;
    protected boolean passable;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.destroyed = false;
        passable = true;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void update(Bomberman game);

    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }
}
