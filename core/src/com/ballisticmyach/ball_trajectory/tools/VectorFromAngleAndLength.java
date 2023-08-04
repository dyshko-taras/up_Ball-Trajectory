package com.ballisticmyach.ball_trajectory.tools;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class VectorFromAngleAndLength {
    public static Vector2 getVector(float angleDegrees, float length) {
        float x = length * MathUtils.cosDeg(angleDegrees);
        float y = length * MathUtils.sinDeg(angleDegrees);
        return new Vector2(x, y);
    }

    public static float getX(float angleDegrees, float length) {
        return length * MathUtils.cosDeg(angleDegrees);
    }

    public static float getY(float angleDegrees, float length) {
        return length * MathUtils.sinDeg(angleDegrees);
    }
}