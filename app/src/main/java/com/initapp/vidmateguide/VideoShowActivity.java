package com.initapp.vidmateguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import util.YouTubeFailureRecoveryActivity;

/**
 * Created by Piyush on 8/31/2016.
 */
public class VideoShowActivity extends YouTubeFailureRecoveryActivity
        implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        YouTubePlayer.OnFullscreenListener {

public static final String DEVELOPER_KEY = "AIzaSyAvZyqrZU3sbuK5BxfaV9NV5u76YgGX260";


private LinearLayout baseLayout;
private YouTubePlayerView playerView;
private YouTubePlayer player;
private Button fullscreenButton;
private CompoundButton checkbox;
private View otherViews;
private boolean fullscreen;
public static final String MyPREFERENCES = "MyPrefs" ;
        SharedPreferences animalPreferences;
        String videoValue;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.video_player);

        baseLayout = (LinearLayout) findViewById(R.id.layout);
        playerView = (YouTubePlayerView) findViewById(R.id.player);
        fullscreenButton = (Button) findViewById(R.id.fullscreen_button);
        checkbox = (CompoundButton) findViewById(R.id.landscape_fullscreen_checkbox);
        otherViews = findViewById(R.id.other_views);

        checkbox.setOnCheckedChangeListener(this);
        fullscreenButton.setOnClickListener(this);
        playerView.initialize(DEVELOPER_KEY, this);

        animalPreferences = getApplication().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        videoValue = animalPreferences.getString("Video", "");
        //	doLayout();
        }

@Override
public void onInitializationSuccess(YouTubePlayer.Provider provider,
        YouTubePlayer player, boolean wasRestored) {
        this.player = player;
        setControlsEnabled();
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        player.setOnFullscreenListener(this);
        if (!wasRestored) {
        player.cueVideo(""+videoValue);
        }
        }

@Override
protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
        }

@Override
public void onClick(View v) {
        player.setFullscreen(!fullscreen);
        }

@Override
public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int controlFlags = player.getFullscreenControlFlags();
        if (isChecked) {
        controlFlags |= YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE;
        } else {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        controlFlags &= ~YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE;
        }
        player.setFullscreenControlFlags(controlFlags);
        }

@SuppressWarnings("unused")
private void doLayout() {
        LinearLayout.LayoutParams playerParams = (LinearLayout.LayoutParams) playerView
        .getLayoutParams();
        if (fullscreen) {
        playerParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        playerParams.height = LinearLayout.LayoutParams.MATCH_PARENT;

        otherViews.setVisibility(View.GONE);
        } else {
        otherViews.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams otherViewsParams = otherViews.getLayoutParams();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
        playerParams.width = otherViewsParams.width = 0;
     //   playerParams.height = WRAP_CONTENT;
     //   otherViewsParams.height = MATCH_PARENT;
        playerParams.weight = 1;
        baseLayout.setOrientation(LinearLayout.HORIZONTAL);
        } else {
     //   playerParams.width = otherViewsParams.width = MATCH_PARENT;
     //   playerParams.height = WRAP_CONTENT;
        playerParams.weight = 0;
        otherViewsParams.height = 0;
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        }
        setControlsEnabled();
        }
        }

private void setControlsEnabled() {
        checkbox.setEnabled(player != null
        && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
        fullscreenButton.setEnabled(player != null);
        }

@Override
public void onFullscreen(boolean isFullscreen) {
        fullscreen = isFullscreen;
        //	doLayout();
        }

@Override
public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //	doLayout();
        }

        }



