package com.ballisticmyach.ball_trajectory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ballisticmyach.ball_trajectory.Main;
import com.ballisticmyach.ball_trajectory.actors.BallActor;
import com.ballisticmyach.ball_trajectory.actors.LineActor;
import com.ballisticmyach.ball_trajectory.tools.GameSettings;
import com.ballisticmyach.ball_trajectory.tools.LineAngleCalculator;
import com.ballisticmyach.ball_trajectory.tools.Localization;

public class GameScreen implements Screen, InputProcessor {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    private final Main main;
    private Viewport viewport;

    private Skin skin;
    private Stage stage;

    private Table mainTable;
    private Stack mainStack;
    private Image background;
    private Container container;
    private Table table;

    //Table
    private Image returnButton;
    private Label labelScore;
    private Label numBalls;
    private Image settingButton;
    private Image achievementsButton;

    //Container
    private Vector2 containerVector;
    public float aX = 0;
    private float aY = 0;

    //Actors
    private boolean isActorAdded = false;
    private BallActor ballActor;
    private LineActor lineActor;

    public GameScreen(Main main) {
        this.main = main;
        GameSettings.setPlayGameTimes(GameSettings.getPlayGameTimes() + 1);
    }


    public void show() {
        showCameraAndStage();

        skin = new Skin(Gdx.files.internal("skin.json"));

        mainTable = new Table();
        mainTable.setFillParent(true);

        mainStack = new Stack();

        background = new Image(skin, "background");
        background.setScaling(Scaling.fillY);
        mainStack.addActor(background);

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

        initLocalizedUI();
        setClickListeners();

        container.setActor(table);
        mainStack.addActor(container);
        mainTable.add(mainStack);
        stage.addActor(mainTable);
        Gdx.input.setInputProcessor(this);
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

        stage.act();
        stage.draw();

        update(delta);
    }

    public void update(float delta) {
        setContainerPosition();
        addMyActors();

    }

    public void resize(int width, int height) {
        resizeCamera(width, height);
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
        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
    }

    private void renderCamera() {
        ScreenUtils.clear(0, 0, 0, 1);
        viewport.apply();
        main.batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    private void resizeCamera(int width, int height) {
        viewport.update(width, height, true);
        stage.getViewport().update(width, height, true);
    }
    ////////

    private void initLocalizedUI() {
        labelScore.setText(Localization.getLoc(Localization.SCORE));
    }

    private void setContainerPosition() {
        containerVector = container.localToStageCoordinates(new Vector2(0, 0));

        if (aX != containerVector.x) {
            aX = containerVector.x;
        }

        if (aY != containerVector.y) {
            aY = containerVector.y;
        }
    }

    public void addMyActors() {
        if (!isActorAdded) {
            ballActor = new BallActor(new Image(skin, "ball"),containerVector,156,30,24);
            stage.addActor(ballActor);

            isActorAdded = true;
            System.out.println("Added");
        }
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


    private float circleTouchX, circleTouchY;
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Touch");
        Vector2 touch = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
        float x = touch.x;
        float y = touch.y;



//
        if (ballActor.getCircle().contains(x, y)) {
//            circleTouchX = x;
//            circleTouchY = y;
            lineActor = new LineActor(new Image(skin, "line"),ballActor.getCircle().x,ballActor.getCircle().y,2000,4,0,2);
            stage.addActor(lineActor);
            lineActor.setRotation(LineAngleCalculator.calculateAngleWithXAxis(lineActor.getX() + lineActor.getOriginX(), lineActor.getY() + lineActor.getOriginY(), x, y));
            System.out.println(ballActor.getCircle().x + " " + ballActor.getCircle().y);
//
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (lineActor != null) {
            lineActor.setRotation(0.0f);
            lineActor.remove();
        }
//        if(lineActor != null) {
//            lineActor.remove();
//        }
        return true;
    }


    private float dragX, dragY;
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (lineActor != null) {
            Vector2 stageCoords = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            float x = stageCoords.x;
            float y = stageCoords.y;
            lineActor.setRotation(LineAngleCalculator.calculateAngleWithXAxis(lineActor.getX() + lineActor.getOriginX(), lineActor.getY() + lineActor.getOriginY(), x, y));
        }
//        // Оновлення координат кінця лінії
//        lineActor.setEnd(x, y);
//
//        // Оновлення останніх координат переміщення пальця
//        dragX = x;
//        dragY = y;

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
}
