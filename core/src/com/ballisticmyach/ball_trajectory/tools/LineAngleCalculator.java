package com.ballisticmyach.ball_trajectory.tools;

import com.badlogic.gdx.math.MathUtils;

public class LineAngleCalculator {

    //calculateAngleWithXAxis
    public static float getDegrees(float x1, float y1, float x2, float y2) {
        float slope = (y2 - y1) / (x2 - x1);
        float angleRad = (float) Math.atan(slope);
        float angleDeg = MathUtils.radiansToDegrees * angleRad;

        angleDeg = angleDeg - 180;

        // Angle must be between 0 and 360 (atan)
        if (x2 < x1) {
            angleDeg = angleDeg + 180;
        }
        if (angleDeg < 0) {
            angleDeg = 360 + angleDeg;
        }


        //Limit  angle
        if (angleDeg > 180 && angleDeg < 270) {
            angleDeg = 180;
        }
        if (angleDeg > 270 && angleDeg < 360) {
            angleDeg = 0;
        }

        System.out.println(angleDeg);
        return angleDeg;
    }
}
