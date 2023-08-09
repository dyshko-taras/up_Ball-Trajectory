package com.ballisticmyach.ball_trajectory.tools;

public class GameState {
    public static final int START_GAME = 0;
    public static final int READY_TO_SHOOT = 1;
    public static final int SHOOTING = 2;
    public static final int BALL_MOVING = 3;
    public static final int BLOCKS_MOVING = 4;
    public static final int STOP_BLOCKS = 5;
    public static final int GAME_OVER = 6;
    public static final int MENU = 7;

    private static int currentState = MENU;

    public static void setState(int newState) {
        currentState = newState;
    }

    public static int getState() {
        return currentState;
    }
}
