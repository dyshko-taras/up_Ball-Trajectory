package com.ballisticmyach.ball_trajectory.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.ballisticmyach.ball_trajectory.utils.MyMath;

public class GroupBlocks extends Group {

    private float dx;
    private float dy;
    private float widthElement;
    private float heightElement;
    private int numRows = 0;

    private Skin skin;

    public GroupBlocks(float x, float y, float dx, float dy) {
        super();
        this.dx = dx;
        this.dy = dy;

        setPosition(x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0, -60 * delta);
        updateBody();
    }

    public void addRandomRow(Array<BlockActor> blockActors) {
        widthElement = blockActors.get(0).getWidth();
        heightElement = blockActors.get(0).getHeight();


        for (int CNum = 0; CNum < blockActors.size; CNum++) {
            if (MyMath.calculateProbability(80)) {
                addActor(blockActors.get(CNum));
                System.out.println(blockActors.get(CNum).getX() + "  1");
                blockActors.get(CNum).setPositionWithGroup(CNum * (widthElement + dx), numRows * (heightElement + dy),this);
                System.out.println(blockActors.get(CNum).getX() + "  2");
                System.out.println(blockActors.get(CNum).b2Block.getX() + "  3");
            } else {

            }
        }
        numRows++;
    }

    private void updateBody() {
        for (Actor actor : getChildren()) {
            if (actor instanceof BlockActor) {
                BlockActor blockActor = (BlockActor) actor;
                blockActor.updateB2BlockPositionWithGroup(this);
            }
        }
    }

//    private BlockActor createBlockActor() {
//        Label label = new Label("", skin, originalBlockActor.label.getStyle().font.toString());
//        LabelNum.setNum(label);
//        return new BlockActor(originalBlockActor.image,
//                label,
//                originalBlockActor.getX(),
//                originalBlockActor.getY(),
//                originalBlockActor.getWidth(),
//                originalBlockActor.getHeight(),
//                originalBlockActor.world,
//                originalBlockActor.worldScale);
//    }

}


//    private Table table;
//    private BlockActor originalBlockActor;
//
//    public TableActor(BlockActor originalBlockActor) {
//        table = new Table();
//        table.align(Align.top);
//        table.setFillParent(true);
////        table.setSize(300, 100); // Приклад значень розміру таблиці
////        table.setSize(300, 100); // Приклад значень розміру таблиці
////        table.setPosition(100, 100);
//        this.originalBlockActor = originalBlockActor;
////        addRandomRow(3);
//    }
//



//
////
////    public void addBlock(Block block) {
////        blocks.add(block); // Додаємо блок до списку блоків
////        addActor(block); // Додаємо блок як дочірній актор
////        block.setPosition(getX(), getY() + getHeight()); // Початкова позиція блока (зверху актора)
////    }
////
////    public void removeBlock(Block block) {
////        blocks.removeValue(block, true); // Видаляємо блок зі списку блоків
////        block.remove(); // Видаляємо блок з сцени
////    }
////
////    public void update(float delta) {
////        // Оновлюємо стан блоків, наприклад, можна переміщати блоки або перевіряти умови їх зникнення
////    }
////
////    private Block createNewBlock() {
////        // Створіть тут новий блок згідно вашої логіки
////    }
////
////    @Override
////    public void act(float delta) {
////        super.act(delta);
////        moveBy(0, -70 * delta); // Рухаємо актора вниз зі швидкістю 70 одиниць на секунду
////    }
////
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        super.draw(batch, parentAlpha);
//    }
//




