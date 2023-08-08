package com.ballisticmyach.ball_trajectory;

import android.os.Bundle;
import android.webkit.WebView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ballisticmyach.ball_trajectory.Main;

public class AndroidLauncher extends AndroidApplication {
	WebView webView;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		initialize(new Main(), config);
	}
}
