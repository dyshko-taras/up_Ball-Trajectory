package com.ballisticmyach.ball_trajectory.tools;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class VectorUtils {
    public static Vector2 createVectorFromAngleAndLength(float angleDegrees, float length) {
        float x = length * MathUtils.sinDeg(angleDegrees);
        float y = length * MathUtils.cosDeg(angleDegrees);
        return new Vector2(x, y);
    }
}