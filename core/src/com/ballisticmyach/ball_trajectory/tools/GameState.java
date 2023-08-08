package com.ballisticmyach.ball_trajectory.tools;

public class GameState {
    public static final int START_GAME = 0;
    public static final int READY_TO_SHOOT = 0;
    public static final int MOVING = 1;
    public static final int HIT_BLOCK = 2;
    public static final int BLOCKS_MOVING = 3;
    public static final int GAME_OVER = 4;
    // Додайте інші стани за потреби

    private int currentState;

    public void setState(int newState) {
        currentState = newState;
    }

    public int getState() {
        return currentState;
    }
}
