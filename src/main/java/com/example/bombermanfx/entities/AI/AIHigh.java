package com.example.bombermanfx.entities.AI;

import com.example.bombermanfx.Bomberman;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class AIHigh extends AI{
    private static final int _step = 5;

    private int step = 0;

    public AIHigh() {
        _dodgeRange = 4;
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
            boolean findPath = false;
            if (!path.hasPathTo(finalX, finalY)) {
                List<Integer> checkOrder;
                if (playerX <= x && playerY >= y) checkOrder = Arrays.asList(1, 2, 4, 3);
                else if (playerX >= x && playerY >= y) checkOrder = Arrays.asList(2, 3, 1, 4);
                else if (playerX >= x && playerY <= y) checkOrder = Arrays.asList(3, 4, 2, 1);
                else checkOrder = Arrays.asList(4, 1, 3, 2);
                for (int i = 0; i < checkOrder.size(); i++) {
                    switch (checkOrder.get(i)) {
                        case 1 :
                            for (finalY = playerY; finalY > 0; finalY--) {
                                for (finalX = playerX; finalX < Bomberman.WIDTH - 1; finalX++) {
                                    if (path.hasPathTo(finalX, finalY) && !(finalX == (int)x && finalY == (int)y)) {
                                        findPath = true;
                                        break;
                                    }
                                }
                                if (findPath) break;
                            }
                            break;
                        case 2 :
                            for (finalY = playerY; finalY > 0; finalY--) {
                                for (finalX = playerX; finalX > 0; finalX--) {
                                    if (path.hasPathTo(finalX, finalY) && !(finalX == (int)x && finalY == (int)y)) {
                                        findPath = true;
                                        break;
                                    }
                                }
                                if (findPath) break;
                            }
                        case 3 :
                            for (finalX = playerX; finalX > 0; finalX--) {
                                for (finalY = playerY; finalY < Bomberman.HEIGHT - 1; finalY++) {
                                    if (path.hasPathTo(finalX, finalY) && !(finalX == (int)x && finalY == (int)y)) {
                                        findPath = true;
                                        break;
                                    }
                                }
                                if (findPath) break;
                            }
                        case 4 :
                            for (finalX = playerX; finalX < Bomberman.WIDTH - 1; finalX++) {
                                for (finalY = playerY; finalY < Bomberman.HEIGHT - 1; finalY++) {
                                    if (path.hasPathTo(finalX, finalY) && !(finalX == (int)x && finalY == (int)y)) {
                                        findPath = true;
                                        break;
                                    }
                                }
                                if (findPath) break;
                            }
                    }
                    if (findPath) break;
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
