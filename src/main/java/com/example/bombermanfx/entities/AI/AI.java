package com.example.bombermanfx.entities.AI;

import com.example.bombermanfx.Bomberman;

import java.util.Random;
import java.util.Stack;

public abstract class AI {
    protected Random random = new Random();
    protected Stack<Integer> direction = new Stack<>();
    protected int _dodgeRange;
    protected boolean dodgeBomb = false;



    /**
     * Thuật toán tìm đường đi
     * @return hướng đi xuống/phải/trái/lên tương ứng với các giá trị 0/1/2/3
     */
    public abstract int calculateDirection(Bomberman game, double x, double y);
}
