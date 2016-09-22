package com.initapp.vidmateguide;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

import util.ConnectionDetector;

public class SplashScreenActivity extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;
	boolean isRunning;
	/* flag for Internet connection status*/
	Boolean isInternetPresent = false;

	/*Connection detector class*/
	ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		/*creating connection detector class instance*/
		cd = new ConnectionDetector(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		if (isInternetPresent) {

			setSplash();
		} else {

			Toast.makeText(getApplicationContext(),"You don't have internet connection",Toast.LENGTH_LONG).show();
		}

	}

	public void setSplash() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(SplashScreenActivity.this,
						HomeActivity.class);
				startActivity(i);
				finish();

			}
		}, SPLASH_TIME_OUT);
	}
}
