package com.ballisticmyach.ball_trajectory.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameSettings {
    private static final String PREFERENCES_NAME = "game_settings";
    private static final String KEY_LANGUAGE = "language";
    private static final String KEY_MUSIC_ON = "music_on";
    private static final String KEY_SCORE = "max_score";
    private static final String KEY_PLAY_GAME_TIMES = "play_game_times";

    private static Preferences preferences;

    public static void init() {
        preferences = Gdx.app.getPreferences(PREFERENCES_NAME);
    }

    //language
    public static String getLanguage() {
        return preferences.getString(KEY_LANGUAGE, "en"); // default "en"
    }

    public static void setLanguage(String languageCode) {
        preferences.putString(KEY_LANGUAGE, languageCode);
        preferences.flush();
    }


    //music
    public static boolean getMusicOn() {
        return preferences.getBoolean(KEY_MUSIC_ON, true);
    }

    public static void setMusicOn(boolean music) {
        preferences.putBoolean(KEY_MUSIC_ON, music);
        preferences.flush();
    }

    //score
    public static int getBestScore() {
        return preferences.getInteger(KEY_SCORE, 0);
    }

    public static void setBestScore(int score) {
        preferences.putInteger(KEY_SCORE, score);
        preferences.flush();
    }

    //play game times
    public static int getPlayGameTimes() {
        return preferences.getInteger(KEY_PLAY_GAME_TIMES, 0);
    }

    public static void setPlayGameTimes(int playGameTimes) {
        preferences.putInteger(KEY_PLAY_GAME_TIMES, playGameTimes);
        preferences.flush();
    }

}

