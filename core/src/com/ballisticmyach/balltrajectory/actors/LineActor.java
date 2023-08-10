package com.ballisticmyach.balltrajectory.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LineActor extends Actor {
    private Image image;

    public LineActor(Image image, float x, float y, float width, float height,float originX, float originY) {
        super();

        this.image = image;

        setPosition(x,y);
        setSize(width, height);
        setOrigin(originX, originY);
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


//    @Override
//    public void act(float delta) {
//        super.act(delta);
//        rotateBy(degrees);
//    }
}
