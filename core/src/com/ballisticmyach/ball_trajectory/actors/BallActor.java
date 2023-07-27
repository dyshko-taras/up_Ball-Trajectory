package com.ballisticmyach.ball_trajectory.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BallActor extends Actor {
    private float aX;
    private float aY;

    private Image image;
    private Circle circle;
    private float speed = 100;
    public float radius;


    public BallActor(Image image, Vector2 containerVector, float x, float y, float radius) {
        super();

        aX = containerVector.x;
        aY = containerVector.y;

        this.image = image;
        circle = new Circle();

        setPosition(aX + x, aY + y);
        setSize(radius * 2, radius * 2);

        this.radius = radius;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        image.setX(getX());
        image.setY(getY());
        image.setOrigin(getOriginX(), getOriginY());
        image.setRotation(getRotation());
        image.draw(batch, parentAlpha);
    }

    public Circle getCircle() {
        circle.x = getX() + radius;
        circle.y = getY() + radius;
        circle.radius = radius;
        return circle;
    }

}


