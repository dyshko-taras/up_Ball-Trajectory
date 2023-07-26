package com.ballisticmyach.ball_trajectory.tools;

public class Localization {

    public static final int SETTING = 0;
    public static final int STATISTICS = 1;
    public static final int TIMES_PLAYED = 2;
    public static final int BEST_SCORE = 3;
    public static final int SCORE = 4;
    public static final int GAME_OVER = 5;


    public static final String[] EN = {
            "SETTING",
            "STATISTICS",
            "TIMES PLAYED",
            "BEST SCORE",
            "SCORE: ",
            "GAME\nOVER",
    };

    public static final String[] BR = {
            "INSTALAÇÕES",
            "ESTATÍSTICAS",
            "TEMPOS JOGADOS",
            "MELHOR ESCORDO",
            "ESCORE: ",
            "GONHO DE\nJOGO",
    };

    public static String[] currentLanguage = EN;// def

    public static void setLanguage(String languageCode) {
        if (languageCode.equals("br")) {
            currentLanguage = BR;
        } else if (languageCode.equals("en")) {
            currentLanguage = EN;
        }
    }

    public static String getLoc(int index) {
        if (index >= 0 && index < currentLanguage.length) {
            return currentLanguage[index];
        } else {
            return "Localized text not found";
        }
    }

}

