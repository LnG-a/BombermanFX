package com.example.bombermanfx.entities;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class Bomber extends MovableEntity {
    public static final double MAXSPEED = 0.08;
    public static final int MAXFLAMELENGTH = 8;
    public static final int MAXBOMBS = 8;

    private int life;
    private int flameLength;
    private int numberOfBombs;


    public Bomber(int x, int y) {
        super(x, y);
        this.speed = 0.04;
        this.flameLength = 1;
        this.numberOfBombs = 1;
        this.life = 3;
        this.fixingNumber = 0.2;
        this.img = Sprite.player_down.getFxImage();
    }

    @Override
    public void update(Bomberman game) {
        dead(game);
        if (this.destroyed) {
            if (System.currentTimeMillis() > time + 450) {
                this.img = Sprite.player_down.getFxImage();
                try {
                    game.createMap(game.LEVEL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                destroyed = false;
            } else if (System.currentTimeMillis() > time + 300) this.img = Sprite.player_dead_2.getFxImage();
            else if (System.currentTimeMillis() > time + 150) this.img = Sprite.player_dead_1.getFxImage();
            else this.img = Sprite.player_dead.getFxImage();
        } else {
            time = System.currentTimeMillis();
            animation++;
            game.getCanvas().setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch (keyEvent.getCode()) {
                        case RIGHT:
                            isMovingRight = true;
                            isMovingLeft = false;
                            isMovingUp = false;
                            isMovingDown = false;
                            break;
                        case LEFT:
                            isMovingRight = false;
                            isMovingLeft = true;
                            isMovingUp = false;
                            isMovingDown = false;
                            break;
                        case UP:
                            isMovingRight = false;
                            isMovingLeft = false;
                            isMovingUp = true;
                            isMovingDown = false;
                            break;
                        case DOWN:
                            isMovingRight = false;
                            isMovingLeft = false;
                            isMovingUp = false;
                            isMovingDown = true;
                            break;
                        case SPACE:
                            if (canCreateBomb()) {
                                createBomb(game);
                            }
                            break;
                    }
                }
            });
            game.getCanvas().setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch (keyEvent.getCode()) {
                        case RIGHT:
                            isMovingRight = false;
                            break;
                        case LEFT:
                            isMovingLeft = false;
                            break;
                        case UP:
                            isMovingUp = false;
                            break;
                        case DOWN:
                            isMovingDown = false;
                            break;
                    }
                }
            });
            if (isMovingRight) {
                moveRight(game);
                this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animation, 20).getFxImage();
            }
            if (isMovingLeft) {
                moveLeft(game);
                this.img = Sprite.player_left.getFxImage();
                this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animation, 20).getFxImage();
            }
            if (isMovingDown) {
                moveDown(game);
                this.img = Sprite.player_down.getFxImage();
                this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animation, 20).getFxImage();
            }
            if (isMovingUp) {
                moveUp(game);
                this.img = Sprite.player_up.getFxImage();
                this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animation, 20).getFxImage();
            }
        }
    }

    @Override
    public void dead(Bomberman game) {
        for (Entity i : game.getEntities()) {
            if (this.checkCollide(i) && i.getClass().equals(Flame.class)) {
                this.destroyed = true;
                break;
            }
        }
    }

    public void createBomb(Bomberman game) {
        int xBomb = (int) this.x;
        int yBomb = (int) this.y;
        double next = x % 1;
        if (next >= 0.5) xBomb++;
        next = this.y % 1;
        if (next >= 0.5) yBomb++;
        if (game.getObstacle(xBomb, yBomb) == null) {
            game.getEntities().add(new Bomb(xBomb, yBomb, this.flameLength));
        }
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

    public int getFlameLength() {
        return flameLength;
    }

    public int getLife() {
        return life;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    private boolean canCreateBomb() {
        return numberOfBombs > Bomb.currentBomb;
    }
}