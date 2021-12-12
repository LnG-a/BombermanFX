package com.example.bombermanfx.entities.enemies.AI;

import com.example.bombermanfx.Bomberman;
import com.example.bombermanfx.entities.map.Grass;

import java.util.*;

public class Path {
    private boolean[][] marked;
    private int [][] direction;
    private int xStart;
    private int yStart;

    public Path(Bomberman game, int x, int y) {
        marked = new boolean[Bomberman.WIDTH][Bomberman.HEIGHT];
        direction = new int[Bomberman.WIDTH][Bomberman.HEIGHT];
        xStart = x;
        yStart = y;
        visit(game, x, y);
    }

    private void visit(Bomberman game, int x, int y) {
        marked[x][y] = true;
        Grass up = null, down = null, left = null, right = null;
        List<Integer> list = new ArrayList<>();
        if (!marked[x][y-1]) {
            up = game.getGrass(x, y - 1);
            list.add(0);
        }
        if (!marked[x][y+1]) {
            down = game.getGrass(x, y + 1);
            list.add(1);
        }
        if (!marked[x-1][y]) {
            left = game.getGrass(x - 1, y);
            list.add(2);
        }
        if (!marked[x+1][y]) {
            right = game.getGrass(x + 1, y);
            list.add(3);
        }
        Collections.shuffle(list, new Random());
        for (int i = 0; i < list.size(); i++) {
            switch(list.get(i)) {
                case 0 :
                    if (up != null) {
                        direction[(int)up.getX()][(int)up.getY()] = 0;
                        //System.out.println((int)up.getX() + " " + (int)up.getY() + " " + direction[(int)up.getX()][(int)up.getY()]);
                        visit(game, (int)up.getX(), (int)up.getY());
                    }
                    break;
                case 1 :
                    if (down != null) {
                        direction[(int)down.getX()][(int)down.getY()] = 1;
                        //System.out.println((int)down.getX() + " " + (int)down.getY() + " " + direction[(int)down.getX()][(int)down.getY()]);
                        visit(game, (int)down.getX(), (int)down.getY());
                    }
                    break;
                case 2 :
                    if (left != null) {
                        direction[(int)left.getX()][(int)left.getY()] = 2;
                        //System.out.println((int)left.getX() + " " + (int)left.getY() + " " + direction[(int)left.getX()][(int)left.getY()]);
                        visit(game, (int)left.getX(), (int)left.getY());
                    }
                    break;
                case 3 :
                    if (right != null) {
                        direction[(int)right.getX()][(int)right.getY()] = 3;
                        //System.out.println((int)right.getX() + " " + (int)right.getY() + " " + direction[(int)right.getX()][(int)right.getY()]);
                        visit(game, (int)right.getX(), (int)right.getY());
                    }
                    break;
            }
        }

    }

    public boolean hasPathTo(int x, int y) {
        return marked[x][y];
    }

    public Stack<Integer> pathTo(int x, int y) {
        if (!hasPathTo(x, y)) return null;
        Stack<Integer> path = new Stack<>();
        while (x != xStart || y != yStart) {
            int tmp = direction[x][y];
            path.push(tmp);
            //System.out.print(tmp + " ");
            switch (tmp) {
                case 0 -> y++;
                case 1 -> y--;
                case 2 -> x++;
                case 3 -> x--;
            }
        }
        return path;
    }
}
