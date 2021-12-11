package com.example.bombermanfx.entities.AI;

import com.example.bombermanfx.Bomberman;

import java.util.Stack;

public class AIMedium extends AI{
    private static final int _step = 10;
    private static final int _dodgeRange = 3;

    private Stack<Integer> direction = new Stack<>();
    private int step = 0;
    private boolean dodgeBomb = false;

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
            boolean findPath = false;
            if (!path.hasPathTo(finalX, finalY)) {
                for (; finalX < Bomberman.WIDTH; finalX++) {
                    for (; finalY < Bomberman.HEIGHT; finalY++) {
                        if (path.hasPathTo(finalX, finalY)) {
                            findPath = true;
                            break;
                        }
                    }
                    if (findPath) break;
                }
                if(!findPath) {
                    finalX = playerX;
                    finalY = playerY;
                    for (; finalX > 0; finalX--) {
                        for (; finalY > 0; finalY--) {
                            if (path.hasPathTo(finalX, finalY)) {
                                findPath = true;
                                break;
                            }
                        }
                        if (findPath) break;
                    }
                }
                if (!findPath) return random.nextInt(4);
            }
            direction = path.pathTo(finalX, finalY);
            if (direction.isEmpty()) return random.nextInt(4);
        }

        int check = direction.pop();
        switch (check) {
            case 0 :
                for (int i = 1; i < _dodgeRange; i++) {
                    if (game.hasBomb((int)x, Math.max((int)y - i, 0))) {
                        check = 1;
                        dodgeBomb = true;
                        break;
                    }
                }
                break;
            case 1 :
                for (int i = 1; i < _dodgeRange; i++) {
                    if (game.hasBomb((int)x, Math.min((int)y + i, Bomberman.HEIGHT - 1))) {
                        check = 0;
                        dodgeBomb = true;
                        break;
                    }
                }
                break;
            case 2 :
                for (int i = 1; i < _dodgeRange; i++) {
                    if (game.hasBomb(Math.max((int)x - i, 0), (int)y)) {
                        check = 3;
                        dodgeBomb = true;
                        break;
                    }
                }
                break;
            case 3 :
                for (int i = 1; i < _dodgeRange; i++) {
                    if (game.hasBomb(Math.min((int)x + i, Bomberman.WIDTH - 1), (int)y)) {
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
