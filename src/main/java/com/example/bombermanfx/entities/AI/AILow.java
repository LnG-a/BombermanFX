package com.example.bombermanfx.entities.AI;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.Bomb;
import com.example.bombermanfx.entities.Entity;
import com.example.bombermanfx.entities.Grass;

import java.util.Objects;

public class AILow extends AI {
    public AILow() {
        _dodgeRange = 3;
    }

    @Override
    public int calculateDirection(Bomberman game, double x, double y) {
        x = Math.round(x);
        y = Math.round(y);
//        Entity a = game.getObstacle((int)x - 1, (int)y);
//        Entity b = game.getObstacle((int)x + 1, (int)y);
//        Entity c = game.getObstacle((int)x, (int)y - 1);
//        Entity d = game.getObstacle((int)x, (int)y + 1);
//        if (a != null && b != null && c != null && d != null) return random.nextInt(4);
        if (direction.isEmpty() || dodgeBomb) {
            dodgeBomb = false;
            int finalX = random.nextInt(Bomberman.WIDTH - 2) + 1;
            int finalY = random.nextInt(Bomberman.HEIGHT - 2) + 1;
            Path path = new Path(game, (int)x, (int)y);
            while (!path.hasPathTo(finalX, finalY) || finalX == (int)x && finalY == (int)y) {
                finalX = random.nextInt(Bomberman.WIDTH - 2) + 1;
                finalY = random.nextInt(Bomberman.HEIGHT - 2) + 1;
            }
            direction = path.pathTo(finalX, finalY);
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
