package com.ballisticmyach.ball_trajectory.screens;

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
import com.ballisticmyach.ball_trajectory.Main;
import com.ballisticmyach.ball_trajectory.tools.GameSettings;
import com.ballisticmyach.ball_trajectory.tools.Localization;

public class AchievScreen implements Screen {

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
    private Label labelStatistics;
    private Image starImage1;
    private Label numTimesPlayed;
    private Label labelTimesPlayed;
    private Image starImage2;
    private Label numBestScore;
    private Label labelBestScore;
    private Image settingButton;
    private Image achievementsButton;


    public AchievScreen(Main main) {
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

        table = new Table();
        table.align(Align.top);

        returnButton = new Image(skin, "return");
        returnButton.setScaling(Scaling.fit);
        table.add(returnButton).padLeft(24.0f).padTop(30.0f).expandX().align(Align.topLeft).colspan(2);

        table.row();
        labelStatistics = new Label("STATISTICS", skin, "font32");
        table.add(labelStatistics).expandX().align(Align.top).colspan(2);

        table.row();
        Stack stack1 = new Stack();

        starImage1 = new Image(skin, "star");
        starImage1.setScaling(Scaling.fit);
        stack1.addActor(starImage1);

        numTimesPlayed = new Label("5", skin, "font32_white_border");
        numTimesPlayed.setAlignment(Align.center);
        stack1.addActor(numTimesPlayed);
        table.add(stack1).padTop(104.0f).expandX().align(Align.top).colspan(2);

        table.row();
        labelTimesPlayed = new Label("TIMES PLAYED", skin, "font32_white");
        table.add(labelTimesPlayed).padTop(10.0f).expandX().align(Align.top).colspan(2);

        table.row();
        stack1 = new Stack();

        starImage2 = new Image(skin, "star");
        starImage2.setScaling(Scaling.fit);
        stack1.addActor(starImage2);

        numBestScore = new Label("5", skin, "font32_white_border");
        numBestScore.setAlignment(Align.center);
        stack1.addActor(numBestScore);
        table.add(stack1).padTop(86.0f).expandX().align(Align.top).colspan(2);

        table.row();
        labelBestScore = new Label("BEST SCORE", skin, "font32_white");
        table.add(labelBestScore).padTop(10.0f).expandX().align(Align.top).colspan(2);

        table.row();
        settingButton = new Image(skin, "setting");
        settingButton.setScaling(Scaling.fit);
        table.add(settingButton).padLeft(24.0f).padBottom(30.0f).expand().align(Align.bottomLeft);

        achievementsButton = new Image(skin, "achievements");
        achievementsButton.setScaling(Scaling.fit);
        table.add(achievementsButton).padRight(24.0f).padBottom(30.0f).expand().align(Align.bottomRight);

        setData();
        setClickListeners();

        container.setActor(table);
        mainStack.addActor(container);
        mainTable.add(mainStack);
        stage.addActor(mainTable);
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
    }

    public void render(float delta) {
        renderCamera();
        initLocalizedUI();
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

    private void initLocalizedUI() {
        labelStatistics.setText(Localization.getLoc(Localization.STATISTICS));
        labelTimesPlayed.setText(Localization.getLoc(Localization.TIMES_PLAYED));
        labelBestScore.setText(Localization.getLoc(Localization.BEST_SCORE));
    }


    private void setData() {//установка даних в GameSettings
        numTimesPlayed.setText(GameSettings.getPlayGameTimes());
        numBestScore.setText(GameSettings.getBestScore());
    }
}
