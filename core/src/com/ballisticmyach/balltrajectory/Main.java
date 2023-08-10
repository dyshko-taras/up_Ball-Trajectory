package com.ballisticmyach.balltrajectory;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ballisticmyach.balltrajectory.tools.GameSettings;
import com.ballisticmyach.balltrajectory.tools.Localization;
import com.ballisticmyach.balltrajectory.screens.*;

public class Main extends Game {

	public static float SCREEN_WIDTH = 360;
	public static float SCREEN_HEIGHT = 800;

	public SpriteBatch batch;
	public Music music;


	public void create() {
		initAssets();
		setSettings();
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void dispose() {
		batch.dispose();
	}

	public void setSettings() {
		GameSettings.init();
		Localization.setLanguage(GameSettings.getLanguage());
		GameSettings.setMusicOn(true);
		playMusic(GameSettings.getMusicOn());
	}

	public void initAssets() {
		batch = new SpriteBatch();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
	}

	public void playMusic(boolean isMusicOn) {
		if (isMusicOn) {
			music.setLooping(true);
			music.play();
		} else {
			music.stop();
		}
	}
}

