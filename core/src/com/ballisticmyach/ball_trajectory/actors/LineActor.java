package com.ballisticmyach.ball_trajectory.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class LineActor extends Actor {
    private Image image;
    private float width;
    private float height;
    private float degrees;


    public LineActor(Image image, float x, float y, float width, float height,float originX, float originY) {
        super();

        this.image = image;

        setPosition(x,y);
        setSize(width, height);
        setOrigin(originX, originY);
        setRotation(-90f);
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




//
//    @Override
//    public void act(float delta) {
//        super.act(delta);
//    }
}
