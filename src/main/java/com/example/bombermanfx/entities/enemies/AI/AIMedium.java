package com.example.bombermanfx.entities.enemies.AI;

import com.example.bombermanfx.Bomberman;

import java.util.Arrays;
import java.util.List;

public class AIMedium extends AI{
    private static final int _step = 10;

    private int step = 0;

    public AIMedium() {
        _dodgeRange = 3;
    }

    @Override
    public int calculateDirection(Bomberman game, double x, double y) {
        x = Math.round(x);
        y = Math.round(y);
        if (!direction.isEmpty() && step > 0 && !dodgeBomb) {
            step--;
        }
        else {
            dodgeBomb = false;
            step = _step;
            int playerX = (int)game.getPlayer().getX();
            int playerY = (int)game.getPlayer().getY();
            int finalX = playerX;
            int finalY = playerY;
            Path path = new Path(game, (int)x, (int)y);
            if (!path.hasPathTo(finalX, finalY)) {
                dodgeBomb = false;
                finalX = random.nextInt(Bomberman.WIDTH - 2) + 1;
                finalY = random.nextInt(Bomberman.HEIGHT - 2) + 1;
                while (!path.hasPathTo(finalX, finalY) || finalX == (int)x && finalY == (int)y) {
                    finalX = random.nextInt(Bomberman.WIDTH - 2) + 1;
                    finalY = random.nextInt(Bomberman.HEIGHT - 2) + 1;
                }
            }
            direction = path.pathTo(finalX, finalY);
        }

        int check;
        if (direction.isEmpty()) check = random.nextInt(4);
        else check = direction.pop();
        switch (check) {
            case 0 :
                for (int i = 1; i < _dodgeRange; i++) {
                    if (game.hasBomb((int)x, Math.max((int)y - i, 1))) {
                        check = 1;
                        dodgeBomb = true;
                        break;
                    }
                }
                break;
            case 1 :
                for (int i = 1; i < _dodgeRange; i++) {
                    if (game.hasBomb((int)x, Math.min((int)y + i, Bomberman.HEIGHT - 2))) {
                        check = 0;
                        dodgeBomb = true;
                        break;
                    }
                }
                break;
            case 2 :
                for (int i = 1; i < _dodgeRange; i++) {
                    if (game.hasBomb(Math.max((int)x - i, 1), (int)y)) {
                        check = 3;
                        dodgeBomb = true;
                        break;
                    }
                }
                break;
            case 3 :
                for (int i = 1; i < _dodgeRange; i++) {
                    if (game.hasBomb(Math.min((int)x + i, Bomberman.WIDTH - 2), (int)y)) {
                        check = 2;
                        dodgeBomb = true;
                        break;
                    }
                }
                break;
        }
        return check;
    }
}
