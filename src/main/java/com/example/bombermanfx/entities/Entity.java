package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;

    protected Image img;
    protected boolean passable;
    protected boolean destroyed=false;
    protected int animation=0;
    protected long time;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
        passable = true;
    }

    /*protected boolean checkCollide(Entity other) {
        boolean checkX=(x<=other.getX()&&other.getX()<x+1)
                ||(x<other.getX()+1&&other.getX()+1<=x+1);
        boolean checkY=(y<=other.getY()&&other.getY()<y+1)
                || (y<other.getY()+1&&other.getY()+1<=y+1);
        return checkX&&checkY;
    }*/

    protected boolean checkCollide(Entity other) {
       return autoFix(x)==autoFix(other.x)&&autoFix(y)==autoFix(other.y);
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

    public void dead(){
        this.destroyed=true;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    protected int autoFix(double x){
        int fixedX= (int) x;
        double next = x % 1;
        if (next >= 0.5) fixedX++;
        return fixedX;
    }
}
