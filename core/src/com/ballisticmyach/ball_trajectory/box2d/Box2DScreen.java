package com.ballisticmyach.ball_trajectory.box2d;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ballisticmyach.ball_trajectory.Main;

public class Box2DScreen {
    private float worldWidth;
    private float worldHeight;
    private float worldScale;
    private float screenScale;


    public Box2DScreen(World world, float worldScale) {
        worldWidth = Main.SCREEN_WIDTH * worldScale;
        worldHeight = Main.SCREEN_HEIGHT * worldScale;
        this.worldScale = worldScale;
        screenScale = 1 / worldScale;

        BodyDef screenBodyDef = new BodyDef();
        screenBodyDef.type = BodyDef.BodyType.StaticBody;
        screenBodyDef.position.set(0, 0);
        Body screenBody = world.createBody(screenBodyDef);

        EdgeShape screenShape = new EdgeShape();
        FixtureDef screenFixtureDef = new FixtureDef();
        System.out.println(screenFixtureDef.friction);
        screenFixtureDef.friction = 0;
        screenFixtureDef.restitution = 1;

        screenShape.set(0, 0, worldWidth, 0);
        screenFixtureDef.shape = screenShape;
        screenFixtureDef.friction = 0;
        screenBody.createFixture(screenFixtureDef);

        screenShape.set(worldWidth, 0, worldWidth, worldHeight);
        screenFixtureDef.shape = screenShape;
        screenBody.createFixture(screenFixtureDef);

        screenShape.set(0, worldHeight, worldWidth, worldHeight);
        screenFixtureDef.shape = screenShape;
        screenBody.createFixture(screenFixtureDef);

        screenShape.set(0, 0, 0, worldHeight);
        screenFixtureDef.shape = screenShape;
        screenBody.createFixture(screenFixtureDef);

        screenShape.dispose();
    }
}
