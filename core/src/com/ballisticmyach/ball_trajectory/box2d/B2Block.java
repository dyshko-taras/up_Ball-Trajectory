package com.ballisticmyach.ball_trajectory.box2d;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ballisticmyach.ball_trajectory.Main;
import com.ballisticmyach.ball_trajectory.actors.BlockActor;

public class B2Block {
    private float worldWidth;
    private float worldHeight;
    private float worldScale;
    private float screenScale;

    private BodyDef bodyDef;
    private PolygonShape boxShape;
    private Body body;
    private float widthBox;
    private float heightBox;

    public B2Block(World world, float x, float y, float width, float height, float worldScale, BlockActor blockActorUserData) {
        worldWidth = Main.SCREEN_WIDTH * worldScale;
        worldHeight = Main.SCREEN_HEIGHT * worldScale;
        this.worldScale = worldScale;
        screenScale = 1 / worldScale;

        widthBox = width * worldScale / 2;
        heightBox = height * worldScale / 2;

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set((x + width / 2) * worldScale, (y + height / 2) * worldScale);

        boxShape = new PolygonShape();
        boxShape.setAsBox(widthBox, heightBox);

        body = world.createBody(bodyDef);
        body.createFixture(boxShape, 0);

        boxShape.dispose();
        body.setUserData(blockActorUserData);
    }

    public float getX() {
        return (body.getPosition().x - widthBox) * screenScale;
    }

    public float getY() {
        return (body.getPosition().y - heightBox) * screenScale;
    }

    public Body getBody() {
        return body;
    }
}
