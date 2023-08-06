package com.ballisticmyach.ball_trajectory.tools;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelNum {
    public static void setNum(Label label) {
        label.setText(MathUtils.random(1, 20));
    }

    public static void removeOne(Label label) {
        label.setText(Integer.parseInt(String.valueOf(label.getText())) - 1);
    }

    public static int getNum(Label label) {
        return Integer.parseInt(String.valueOf(label.getText()));
    }


}
