package com.example.bombermanfx.entities.AI;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.Bomb;
import com.example.bombermanfx.entities.Entity;
import com.example.bombermanfx.entities.Grass;

import java.util.Objects;

public class AILow extends AI {
    @Override
    public int calculateDirection(Bomberman game, double x, double y) {
        int direction = random.nextInt(4);
        int xa = 0, ya = 0;
        Entity a = game.getObstacle((int)x - 1, (int)y);
        Entity b = game.getObstacle((int)x + 1, (int)y);
        Entity c = game.getObstacle((int)x, (int)y - 1);
        Entity d = game.getObstacle((int)x, (int)y + 1);
        if (a != null && b != null && c != null && d != null) return direction;
        do {
            direction = random.nextInt(4);
            switch (direction) {
                case 0 -> {
                    xa = 0;
                    ya = -1;
                }
                case 1 -> {
                    xa = 0;
                    ya = 1;
                }
                case 2 -> {
                    xa = -1;
                    ya = 0;
                }
                case 3 -> {
                    xa = 1;
                    ya = 0;
                }
            }
        }
        while (game.getObstacle((int)(x + xa), (int)(y + ya)) != null);
        return direction;
    }
}
