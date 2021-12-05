package com.example.bombermanfx.entities.AI;

public class AILow extends AI {
    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }
}
