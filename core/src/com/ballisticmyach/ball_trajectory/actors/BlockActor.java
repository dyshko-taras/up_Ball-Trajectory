package com.ballisticmyach.ball_trajectory.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.ballisticmyach.ball_trajectory.box2d.B2Block;
import com.ballisticmyach.ball_trajectory.utils.LabelNum;

public class BlockActor extends Actor {
    public Image image;
    public Label label;
    private Rectangle rect;
    public float width;
    public float height;
    public B2Block b2Block;
    public World world;
    public float worldScale;

    public BlockActor(Image image, Label label, float x, float y, float width, float height, World world, float worldScale) {
        super();

        this.image = image;
        this.label = label;
        LabelNum.setNum(label);
        rect = new Rectangle();
        this.width = width;
        this.height = height;

        setBounds(x, y, width, height);
        b2Block = new B2Block(world, x, y, width, height, worldScale,this);
        this.world = world;
        this.worldScale = worldScale;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        image.setX(getX());
        image.setY(getY());
        image.setOrigin(getOriginX(), getOriginY());
        image.setRotation(getRotation());
        image.draw(batch, parentAlpha);
//
//        image.setX(b2Block.getX());
//        image.setY(b2Block.getY());
//        image.draw(batch, parentAlpha);

        label.setAlignment(Align.center);
        label.setX(image.getX() + (width - label.getWidth()) / 2);
        label.setY(image.getY() + (height - label.getHeight()) / 2);
        label.setOrigin(image.getOriginX(), image.getOriginY());
        label.setRotation(image.getRotation());
        label.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        b2Block.setPosition(x, y);
    }

    public void setPositionWithGroup(float x, float y, Group group) {
        super.setPosition(x, y);
        b2Block.setPosition(x + group.getX(), y + group.getY());
    }

    public void updateB2BlockPositionWithGroup(Group group) {
        b2Block.setPosition(getX() + group.getX(), getY() + group.getY());
    }



    public Rectangle getRect() {
        rect.x = getX();
        rect.y = getY();
        rect.width = getWidth();
        rect.height = getHeight();
        return rect;
    }

}
