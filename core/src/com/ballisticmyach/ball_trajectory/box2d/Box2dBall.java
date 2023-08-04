package com.ballisticmyach.ball_trajectory.box2d;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ballisticmyach.ball_trajectory.Main;

public class Box2dBall {

    private float worldWidth;
    private float worldHeight;
    private float worldScale;
    private float screenScale;

    private BodyDef bodyDef;
    private CircleShape circleShape;
    private Body ballBody;
    private FixtureDef fixtureDef;
    private float radiusBall;
    private int check = 0;


    public Box2dBall(World world, float x, float y, float radius, float worldScale) {
        worldWidth = Main.SCREEN_WIDTH * worldScale;
        worldHeight = Main.SCREEN_HEIGHT * worldScale;
        this.worldScale = worldScale;
        screenScale = 1 / worldScale;

        radiusBall = radius * worldScale;

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x * worldScale, y * worldScale);

        circleShape = new CircleShape();
        circleShape.setRadius(radiusBall);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 1f;

        ballBody = world.createBody(bodyDef);
        ballBody.createFixture(fixtureDef);
        circleShape.dispose();
    }

    public float getX() {
        return (ballBody.getPosition().x - radiusBall) * screenScale;
    }

    public float getY() {
        return (ballBody.getPosition().y - radiusBall) * screenScale;
    }

    public void setLinearVelocity(float velocityX, float velocityY) {
        ballBody.setLinearVelocity(new Vector2(velocityX * worldScale, velocityY * worldScale));
    }

    public void setLinearVelocity(Vector2 velocity) {
        ballBody.setLinearVelocity(new Vector2(velocity.x * worldScale, velocity.y * worldScale));
    }

    public void checkVelocity() {
//        System.out.println(ballBody.getLinearVelocity().x + " " + ballBody.getLinearVelocity().y);

        if (ballBody.getLinearVelocity().x == 0) {
            check++;
            if(check == 400) {
                ballBody.setLinearVelocity(-30 * worldScale, ballBody.getLinearVelocity().y);
                check = 0;
            }
        }

        if (ballBody.getLinearVelocity().y == 0) {
            check++;
            if(check == 400) {
                ballBody.setLinearVelocity(ballBody.getLinearVelocity().x, -30 * worldScale);
                check = 0;
                System.out.println("check");
            }
        }
    }
}
