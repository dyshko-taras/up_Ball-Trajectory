package com.ballisticmyach.balltrajectory.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
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
import com.ballisticmyach.balltrajectory.tools.GameSettings;
import com.ballisticmyach.balltrajectory.tools.Localization;

public class SettingsScreen implements Screen {

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
    private Label labelSettings;
    private Image musicOnButton;
    private Image musicOffButton;
    private Image enButton;
    private Image brButton;
    private Image settingButton;
    private Image achievementsButton;


    public SettingsScreen(Main main) {
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
        labelSettings = new Label("SETTING", skin,"font32");
        table.add(labelSettings).expandX().align(Align.top).colspan(2);

        table.row();
        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.align(Align.top);
        horizontalGroup.space(64.0f);

        musicOnButton = new Image(skin, "musicOn");
        musicOnButton.setScaling(Scaling.fit);
        horizontalGroup.addActor(musicOnButton);

        musicOffButton = new Image(skin, "musicOff");
        musicOffButton.setScaling(Scaling.fit);
        horizontalGroup.addActor(musicOffButton);
        table.add(horizontalGroup).padTop(167.0f).expandX().align(Align.top).colspan(2);

        table.row();
        horizontalGroup = new HorizontalGroup();
        horizontalGroup.align(Align.top);
        horizontalGroup.space(40.0f);

        enButton = new Image(skin, "en");
        enButton.setScaling(Scaling.fit);
        horizontalGroup.addActor(enButton);

        brButton = new Image(skin, "br");
        brButton.setScaling(Scaling.fit);
        horizontalGroup.addActor(brButton);
        table.add(horizontalGroup).padTop(133.0f).expandX().align(Align.top).colspan(2);

        table.row();
        settingButton = new Image(skin, "setting");
        settingButton.setScaling(Scaling.fit);
        table.add(settingButton).padLeft(24.0f).padBottom(30.0f).expand().align(Align.bottomLeft);

        achievementsButton = new Image(skin, "achievements");
        achievementsButton.setScaling(Scaling.fit);
        table.add(achievementsButton).padRight(24.0f).padBottom(30.0f).expand().align(Align.bottomRight);

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

        musicOnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSettings.setMusicOn(true);
                main.playMusic(GameSettings.getMusicOn());
            }
        });

        musicOffButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSettings.setMusicOn(false);
                main.playMusic(GameSettings.getMusicOn());
            }
        });

        enButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSettings.setLanguage("en");
                Localization.setLanguage(GameSettings.getLanguage());
            }
        });

        brButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSettings.setLanguage("br");
                Localization.setLanguage(GameSettings.getLanguage());
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
        labelSettings.setText(Localization.getLoc(Localization.SETTINGS));
    }
}
