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

    protected boolean checkCollide(Bomberman game) {
        boolean checkX=(x<=game.getPlayer().getX()&&game.getPlayer().getX()<=x+1)
                ||(x<=game.getPlayer().getX()+1&&game.getPlayer().getX()+1<=x+1);
        boolean checkY=(y<=game.getPlayer().getY()&&game.getPlayer().getY()<=y+1)
                || (y<=game.getPlayer().getY()+1&&game.getPlayer().getY()+1<=y+1);
        return checkX&&checkY;
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


}
