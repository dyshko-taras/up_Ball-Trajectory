package com.ballisticmyach.balltrajectory.actors;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.ballisticmyach.balltrajectory.tools.GameState;
import com.ballisticmyach.balltrajectory.utils.MyMath;

import java.util.Iterator;

public class GroupBlocks extends Group {

    private final float dx;
    private final float dy;
    private final World world;
    private float widthElement;
    private float heightElement;
    private int numRows = 0;
    private int numColumn = 0;

    //Act
    private float durationAct = 0.5f;
    private float distanceYAct = 120;
    private float elapsedTime = 0;

    public GroupBlocks(float x, float y, float dx, float dy, World world) {
        super();
        this.dx = dx;
        this.dy = dy;
        this.world = world;

        setPosition(x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(GameState.getState() == GameState.BLOCKS_MOVING) {
            System.out.println("Get blocks moving");
            elapsedTime += delta;
            System.out.println("------- elapsedTime = " + elapsedTime);
            if(elapsedTime <= durationAct) {
                moveBy(0,-distanceYAct/durationAct * delta);
                updateB2Positions();
            } else {
                GameState.setState(GameState.STOP_BLOCKS);
                elapsedTime = 0;
                System.out.println("Set stop blocks");
            }
        }
    }


    int i = 0;

    public void addRandomRow(Array<BlockActor> blockActors) {
        widthElement = blockActors.get(0).getWidth();
        heightElement = blockActors.get(0).getHeight();

        for (Iterator<BlockActor> it = blockActors.iterator(); it.hasNext(); ) {
            BlockActor actor = it.next();
            if (MyMath.calculateProbability(70)) {
                addActor(actor);
                actor.setPositionB2(numColumn * (widthElement + dx), numRows * (heightElement + dy), this);
                System.out.println(actor.getX());
            } else {
                world.destroyBody(actor.b2Block.getBody());
                actor.remove();
            }
            numColumn++;
        }
        blockActors.clear();
        numColumn = 0;
        numRows++;
    }

    private void updateB2Positions() {
        for (Actor actor : getChildren()) {
            if (actor instanceof BlockActor) {
                BlockActor blockActor = (BlockActor) actor;
                blockActor.updateB2Position(this);
            }
        }
    }
}




