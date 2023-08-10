package com.ballisticmyach.balltrajectory.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ballisticmyach.balltrajectory.box2d.B2Ball;
import com.ballisticmyach.balltrajectory.tools.GameState;
import com.ballisticmyach.balltrajectory.utils.VectorFromAngleAndLength;

public class BallActor extends Actor {

    private Image image;
    private Circle circle;
    private float speed = 100;
    public float radius;
    public B2Ball b2Ball;
    public float degrees = 0;

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
        b2Ball.checkVelocity();
        setPosition(b2Ball.getX(), b2Ball.getY());

        super.draw(batch, parentAlpha);
        image.setX(getX());
        image.setY(getY());
        image.setOrigin(getOriginX(), getOriginY());
        image.setRotation(getRotation());
        image.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

            if (GameState.getState() == GameState.SHOOTING) {
            System.out.println("Get shooting");
            b2Ball.setLinearVelocity(VectorFromAngleAndLength.getVector(degrees, 400));
            GameState.setState(GameState.BALL_MOVING);
            System.out.println("Set moving");
        }
        if (GameState.getState() == GameState.BALL_MOVING && getY() < 29) {
            System.out.println("Get moving");
            b2Ball.setLinearVelocity(VectorFromAngleAndLength.getVector(0, 0));
            setPositionB2(getX(), 30);
            GameState.setState(GameState.BLOCKS_MOVING);
            System.out.println("Set blocks moving");
        }
    }

    public void setPositionB2(float x, float y) {
        super.setPosition(x, y);
        b2Ball.setPosition(x, y);
    }

    public Circle getCircle() {
        circle.x = getX() + radius;
        circle.y = getY() + radius;
        circle.radius = radius;
        return circle;
    }
}


