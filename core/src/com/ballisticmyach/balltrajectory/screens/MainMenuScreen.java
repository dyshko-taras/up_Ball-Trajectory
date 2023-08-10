package com.ballisticmyach.balltrajectory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ballisticmyach.balltrajectory.Main;

public class MainMenuScreen implements Screen{

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
    private Stack stack2;
    private Table table2;
    private Image lineImage;
    private Label labelGame;
    private Image playButton;
    private Image settingButton;
    private Image achievementsButton;


    public MainMenuScreen(Main main) {
        this.main = main;
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

        stack2 = new Stack();

        table2 = new Table();

        lineImage = new Image(skin, "line_menu");
        lineImage.setScaling(Scaling.fit);
        table2.add(lineImage).expand().align(Align.top);
        stack2.addActor(table2);

        table = new Table();
        table.align(Align.top);

        labelGame = new Label("BALL\n"
                + "TRAJECTORY", skin);
        labelGame.setAlignment(Align.center);
        table.add(labelGame).padTop(121.0f).expandX().align(Align.top).colspan(2);

        table.row();
        playButton = new Image(skin, "play");
        playButton.setScaling(Scaling.fit);
        table.add(playButton).padTop(154.0f).expandX().align(Align.top).colspan(2);

        table.row();
        settingButton = new Image(skin, "setting");
        settingButton.setScaling(Scaling.fit);
        table.add(settingButton).padLeft(24.0f).padBottom(30.0f).expand().align(Align.bottomLeft);

        achievementsButton = new Image(skin, "achievements");
        achievementsButton.setScaling(Scaling.fit);
        table.add(achievementsButton).padRight(24.0f).padBottom(30.0f).expand().align(Align.bottomRight);

        setClickListeners();

        stack2.addActor(table);
        container.setActor(stack2);
        mainStack.addActor(container);
        mainTable.add(mainStack);
        stage.addActor(mainTable);
    }

    private void setClickListeners() {
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new GameScreen(main));
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
    }

    public void render(float delta) {
        renderCamera();

        stage.act();
        stage.draw();
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
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        main.batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    private void resizeCamera(int width, int height) {
        viewport.update(width, height, true);
        stage.getViewport().update(width, height, true);
    }
    ////////

}
