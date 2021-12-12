package com.example.bombermanfx.entities.bomber;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.MovableEntity;
import com.example.bombermanfx.graphics.Sprite;
import com.example.bombermanfx.sounds.SoundPlayer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class Bomber extends MovableEntity {
    public static final double MAX_SPEED = 0.08;
    public static final int MAX_FLAME_LENGTH = 8;
    public static final int MAX_BOMBS = 8;

    public static final double DEFAULT_SPEED = 0.04;
    public static final int DEFAULT_FLAME_LENGTH = 1;
    public static final int DEFAULT_BOMB = 1;
    public static final int DEFAULT_LIFE = 3;

    private int life;
    private int flameLength;
    private int numberOfBombs;

    public Bomber(int x, int y) {
        super(x, y);
        this.speed = DEFAULT_SPEED;
        this.flameLength = DEFAULT_FLAME_LENGTH;
        this.numberOfBombs = DEFAULT_BOMB;
        this.life = DEFAULT_LIFE;
        this.fixingNumber = 0.2;
        this.img = Sprite.player_down.getFxImage();
    }

    @Override
    public void update(Bomberman game) {
        if (animation < Integer.MAX_VALUE - 1) animation++;
        else animation = 0;

        if (this.destroyed) {
            if (animation > animationDead) animation = 0;
            if (animation >= animationDead) {
                this.life--;
                reset();
                destroyed = false;
                if (life > 0) {
                    try {
                        game.createMap(game.LEVEL);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    SoundPlayer.stopThemeSong();
                    SoundPlayer.gameOver();
                }
            } else
                this.img = Sprite.movingSprite(Sprite.player_dead, Sprite.player_dead_1, Sprite.player_dead_2, animation, animationDead).getFxImage();
        } else {
            //Keyboard input
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

            //Move, animation and sound effects
            if (isMovingRight) {
                moveRight(game);
                this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animation, 20).getFxImage();
                if (animation % 20 == 0) SoundPlayer.moveRightLeft();
            }
            if (isMovingLeft) {
                moveLeft(game);
                this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animation, 20).getFxImage();
                if (animation % 20 == 0) SoundPlayer.moveRightLeft();
            }
            if (isMovingDown) {
                moveDown(game);
                this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animation, 20).getFxImage();
                if (animation % 20 == 0) SoundPlayer.moveUpDown();
            }
            if (isMovingUp) {
                moveUp(game);
                this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animation, 20).getFxImage();
                if (animation % 20 == 0) SoundPlayer.moveUpDown();
            }
        }
        this.checkDeadByFlame(game);
    }

    public void createBomb(Bomberman game) {
        SoundPlayer.createBomb();
        int xBomb = autoFix(x);
        int yBomb = autoFix(y);
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
        return numberOfBombs > Bomb.currentBomb && life > 0;
    }

    private void reset() {
        this.speed = 0.04;
        this.flameLength = 1;
        this.numberOfBombs = 1;
        this.img = Sprite.player_down.getFxImage();
    }
}