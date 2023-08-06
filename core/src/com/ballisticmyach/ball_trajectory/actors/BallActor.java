package com.ballisticmyach.ball_trajectory.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ballisticmyach.ball_trajectory.box2d.B2Ball;

public class BallActor extends Actor {

    private Image image;
    private Circle circle;
    private float speed = 100;
    public float radius;
    public B2Ball b2Ball;

    public BallActor(Image image, float x, float y, float radius, World world, float worldScale) {
        super();

        this.image = image;
        circle = new Circle();
        this.radius = radius;

        setPosition(x, y);
        setSize(radius * 2, radius * 2);
        b2Ball = new B2Ball(world, x, y, radius, worldScale, this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        image.setX(getX());
//        image.setY(getY());
//        image.setOrigin(getOriginX(), getOriginY());
//        image.setRotation(getRotation());
//        image.draw(batch, parentAlpha);
        b2Ball.checkVelocity();
        image.setX(b2Ball.getX());
        image.setY(b2Ball.getY());
        image.draw(batch, parentAlpha);
    }

    public Circle getCircle() {
        circle.x = getX() + radius;
        circle.y = getY() + radius;
        circle.radius = radius;
        return circle;
    }

}


