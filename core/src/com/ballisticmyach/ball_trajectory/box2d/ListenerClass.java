package com.ballisticmyach.ball_trajectory.box2d;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.ballisticmyach.ball_trajectory.actors.BallActor;
import com.ballisticmyach.ball_trajectory.actors.BlockActor;

public class ListenerClass implements ContactListener {
    private Fixture fixtureA;
    private Fixture fixtureB;
    private BlockActor blockActor;
    private BallActor ballActor;

    private Array<BlockActor> blocksActorToRemove;

    public ListenerClass(Array<BlockActor> blocksToRemove) {
        this.blocksActorToRemove = blocksToRemove;
    }

    @Override
    public void beginContact(Contact contact) {
        fixtureA = contact.getFixtureA();
        fixtureB = contact.getFixtureB();

        if (fixtureA.getBody().getUserData() instanceof BlockActor && fixtureB.getBody().getUserData() instanceof BallActor) {
            blockActor = (BlockActor) fixtureA.getBody().getUserData();
            ballActor = (BallActor) fixtureB.getBody().getUserData();
            blocksActorToRemove.add(blockActor);
        } else if (fixtureB.getBody().getUserData() instanceof BlockActor && fixtureA.getBody().getUserData() instanceof BallActor) {
            blockActor = (BlockActor) fixtureB.getBody().getUserData();
            ballActor = (BallActor) fixtureA.getBody().getUserData();
            blocksActorToRemove.add(blockActor);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
