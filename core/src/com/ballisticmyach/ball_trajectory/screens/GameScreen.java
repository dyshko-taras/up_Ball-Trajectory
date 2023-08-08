package com.ballisticmyach.ball_trajectory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ballisticmyach.ball_trajectory.Main;
import com.ballisticmyach.ball_trajectory.actors.BallActor;
import com.ballisticmyach.ball_trajectory.actors.BlockActor;
import com.ballisticmyach.ball_trajectory.actors.GroupBlocks;
import com.ballisticmyach.ball_trajectory.actors.LineActor;
import com.ballisticmyach.ball_trajectory.box2d.B2Screen;
import com.ballisticmyach.ball_trajectory.box2d.ListenerClass;
import com.ballisticmyach.ball_trajectory.tools.GameSettings;
import com.ballisticmyach.ball_trajectory.utils.LabelNum;
import com.ballisticmyach.ball_trajectory.utils.LineAngleCalculator;
import com.ballisticmyach.ball_trajectory.tools.Localization;
import com.ballisticmyach.ball_trajectory.utils.VectorFromAngleAndLength;

import java.util.Iterator;

public class GameScreen implements Screen {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    //Viewports
    private final Main main;
    private Viewport viewport;
    private Viewport viewportBackground;
    private Skin skin;
    private Stage stage;
    private Stage stageBackground;
    private Table mainTable;
    private Image background;
    private Container container;
    private Table table;

    //Table
    private Image returnButton;
    private Label labelScore;
    private Label numBalls;
    private Image settingButton;
    private Image achievementsButton;

    //Actors
    private BallActor ballActor;
    private Image imageBallActor;
    private LineActor lineActor;
    private Image imageLineActor;
    private float degrees = 0;
//    private BlockActor blockActor;
    private Image imageBlockActor;
    private GroupBlocks groupBlocks;


    //Box2D
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Viewport viewportBox2D;
    private float worldScale = 0.01f;
    private ListenerClass listenerClass;
    private Array<BlockActor> blocksActorToRemove = new Array<BlockActor>();
    private B2Screen b2Screen;


    public GameScreen(Main main) {
        this.main = main;
        GameSettings.setPlayGameTimes(GameSettings.getPlayGameTimes() + 1);
    }


    public void show() {
        showCameraAndStage();
        showBox2D();

        skin = new Skin(Gdx.files.internal("skin.json"));

        mainTable = new Table();
        mainTable.setFillParent(true);

        container = new Container();
        container.minWidth(360.0f);
        container.minHeight(800.0f);
        container.maxWidth(360.0f);
        container.maxHeight(800.0f);

        table = new Table();
        table.align(Align.top);

        returnButton = new Image(skin, "return");
        returnButton.setScaling(Scaling.fit);
        table.add(returnButton).padLeft(24.0f).padTop(30.0f).expand().align(Align.topLeft);

        labelScore = new Label("Score:\n"
                + "24", skin, "font24");
        table.add(labelScore).padRight(24.0f).padTop(24.0f).expand().align(Align.topRight);

        table.row();
        numBalls = new Label("1x", skin, "font24_white");
        numBalls.setAlignment(Align.center);
        table.add(numBalls).padBottom(14.0f).expandX().colspan(2);

        table.row();
        settingButton = new Image(skin, "setting");
        settingButton.setScaling(Scaling.fit);
        table.add(settingButton).padLeft(24.0f).padBottom(30.0f).expandX().align(Align.bottomLeft);

        achievementsButton = new Image(skin, "achievements");
        achievementsButton.setScaling(Scaling.fit);
        table.add(achievementsButton).padRight(24.0f).padBottom(30.0f).expandX().align(Align.bottomRight);


        container.setActor(table);
        mainTable.add(container);
        stage.addActor(mainTable);

        addBackground();
        initLocalizedUI();
        initAssets();
        addMyActors();
        setClickListeners();
        initInputProcessor();
    }

    private void setClickListeners() {
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MainMenuScreen(main));
            }
        });

        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new SettingsScreen(main));
            }
        });

        achievementsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new AchievScreen(main));
            }
        });

        numBalls.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new GameOverScreen(main));
            }
        });
    }

    public void render(float delta) {
        renderCamera();
        renderCameraForBox2D();

        update(delta);
    }

    public void update(float delta) {
//        ballActor.setPosition(box2DBall.getX(), box2DBall.getY());
//        box2DBall.checkVelocity();

//        blockActor.setPosition(box2DBlock.getX(), box2DBlock.getY());
        removeBlocksActor();
    }

    public void resize(int width, int height) {
        resizeCamera(width, height);
        resizeCameraForBox2D(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
        main.batch.dispose();
    }

    /////Camera
    private void showCameraAndStage() {
        viewportBackground = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT); //FitViewport or ExtendViewport
        stageBackground = new Stage(viewportBackground);

        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(viewport);
//        Gdx.input.setInputProcessor(stage);
    }

    private void renderCamera() {
        ScreenUtils.clear(Color.BLACK);

        viewportBackground.apply(true);
        stageBackground.draw();

        viewport.apply();
        stage.act();
        stage.draw();
    }

    private void resizeCamera(int width, int height) {
        viewportBackground.update(width, height);
        viewport.update(width, height, true);
    }
    ////////


    private void addBackground() {
        background = new Image(skin, "background");
        background.setFillParent(true);
        background.setScaling(Scaling.fillY);
        stageBackground.addActor(background);
    }

    private void initLocalizedUI() {
        labelScore.setText(Localization.getLoc(Localization.SCORE));
    }


    public void initAssets() {
        imageBallActor = new Image(skin, "ball");
        imageLineActor = new Image(skin, "line");
        imageBlockActor = new Image(skin, "box");
    }

    public void addMyActors() {
        ballActor = new BallActor(imageBallActor, 156, 30, 24, world, worldScale);
        stage.addActor(ballActor);

//        BlockActor blockActor = new BlockActor(imageBlockActor, new Label("", skin, "font24_white"), 0, 0, 60, 60, world, worldScale);
//        stage.addActor(blockActor);

        for(int i = 0; i < 3; i++) {

        }
//        groupBlocks = new GroupBlocks(18, 632, 72,60);
        groupBlocks = new GroupBlocks(18, 632, 72,60);
        groupBlocks.addRandomRow(addArrayBlocksActor(3));
        groupBlocks.addRandomRow(addArrayBlocksActor(3));
        groupBlocks.addRandomRow(addArrayBlocksActor(3));
        groupBlocks.addRandomRow(addArrayBlocksActor(3));
        groupBlocks.addRandomRow(addArrayBlocksActor(3));
        stage.addActor(groupBlocks);
//
//        blockActor = new BlockActor(imageBlockActor, new Label("", skin, "font24_white"), 200, 200, 60, 60, world, worldScale);
//        stage.addActor(blockActor);
//        TableActor tableActor = new TableActor(blockActor);
//        TableActor tableActor = new TableActor(new BlockActor(imageBlockActor, new Label("", skin, "font24_white"), 0, 0, 60, 60, world, worldScale));
//        stage.addActor(tableActor);
//        addTableActor();
    }

    private Array<BlockActor> addArrayBlocksActor(int num) {
        Array<BlockActor> array = new Array<BlockActor>();
        for (int i = 0; i < num; i++) {
            BlockActor blockActor = new BlockActor(imageBlockActor, new Label("", skin, "font24_white"), 0, 0, 60, 60, world, worldScale);
            array.add(blockActor);
        }
        return array;
    }

    private void initInputProcessor() {
        //init InputProcessor. The call order of the processors depends on the order you provide them!!!!
        InputMultiplexer multiplexer = new InputMultiplexer(stage, new InputProcessor() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                System.out.println("Touch");
                Vector2 touch = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
                float x = touch.x;
                float y = touch.y;

                if (ballActor.getCircle().contains(x, y)) {
                    lineActor = new LineActor(imageLineActor,
                            ballActor.getCircle().x,
                            ballActor.getCircle().y,
                            2000,
                            4,
                            0,
                            2);
                    stage.addActor(lineActor);

                    degrees = LineAngleCalculator.getDegrees(
                            lineActor.getX() + lineActor.getOriginX(),
                            lineActor.getY() + lineActor.getOriginY(),
                            x,
                            y);
//                    lineActor.setRotation(degrees);
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                System.out.println("TouchUp");
                if (lineActor != null) {
                    lineActor.setRotation(0.0f);
                    lineActor.remove();
//                    System.out.println(degrees);
                    System.out.println("KURWA");
                    ballActor.b2Ball.setLinearVelocity(VectorFromAngleAndLength.getVector(degrees, 400));
                }
                return true;
            }


            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (lineActor != null) {
                    Vector2 stageCoords = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
                    float x = stageCoords.x;
                    float y = stageCoords.y;
                    degrees = LineAngleCalculator.getDegrees(
                            lineActor.getX() + lineActor.getOriginX(),
                            lineActor.getY() + lineActor.getOriginY(),
                            x,
                            y);
                    lineActor.setRotation(degrees);
                }
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                return false;
            }

            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }
        });
        Gdx.input.setInputProcessor(multiplexer);
    }


    ///// Box2D
    private void showBox2D() {
        //init Box2D
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        viewportBox2D = new FitViewport(SCREEN_WIDTH * worldScale, SCREEN_HEIGHT * worldScale); //FitViewport or ExtendViewport
        World.setVelocityThreshold(0.1f);
        listenerClass = new ListenerClass(blocksActorToRemove);
        world.setContactListener(listenerClass);
        addBodyBox2D();
    }

    private void renderCameraForBox2D() {
        viewportBox2D.apply();
        world.step(1 / 60f, 6, 2);
        debugRenderer.render(world, viewportBox2D.getCamera().combined);
    }

    private void resizeCameraForBox2D(int width, int height) {
        viewportBox2D.update(width, height, true);
    }


    private void addBodyBox2D() {
        b2Screen = new B2Screen(world, worldScale);
    }
    ////////

    private void removeBlocksActor() {
        if (blocksActorToRemove != null && blocksActorToRemove.size > 0) {
            System.out.println(blocksActorToRemove.size);
            for (Iterator<BlockActor> it = blocksActorToRemove.iterator(); it.hasNext(); ) {
                BlockActor actor = it.next();
                if (LabelNum.getNum(actor.label) > 1) {
                    LabelNum.removeOne(actor.label);
                } else {
                    world.destroyBody(actor.b2Block.getBody());
                    actor.remove();
                }
                it.remove();
            }
        }
    }



//    private void addTableActor() {
//        Table tableMyActor = new Table();
//        tableMyActor.align(Align.bottomLeft);
//        tableMyActor.setFillParent(true);
//
//        BlockActor blockActor1 = new BlockActor(imageBlockActor, new Label("", skin, "font24_white"), 18, 632, 60, 60, world, worldScale);
//        BlockActor blockActor2 = new BlockActor(imageBlockActor, new Label("", skin, "font24_white"), 150, 632, 60, 60, world, worldScale);
//        BlockActor blockActor3 = new BlockActor(imageBlockActor, new Label("", skin, "font24_white"), 282, 632, 60, 60, world, worldScale);
//
//        tableMyActor.add(blockActor1);
//        tableMyActor.add(blockActor2);
//        tableMyActor.add(blockActor3);
//
//        tableMyActor.row();
//        tableMyActor.setPosition(0, 0);
////        tableMyActor.addAction(Actions.moveBy(0, 100, 0.5f));
//        stage.addActor(tableMyActor);
//
//    }
}


