package com.initapp.vidmateguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.initapp.vidmateguide.adapter.LatestVideoAdapter;
import com.initapp.vidmateguide.async.BaseRestAsyncTask;
import com.initapp.vidmateguide.model.Result;
import com.initapp.vidmateguide.model.VideoResult;
import com.initapp.vidmateguide.webapi.VidmateApiService;


import retrofit.RetrofitError;

/**
 * Created by Big_Scal on 9/16/2016.
 */
public class VideoDetailActivity extends YouTubeBaseActivity implements  YouTubePlayer.OnInitializedListener  {
    private Handler mHandler = new Handler();

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    // YouTube player view
    private YouTubePlayerView youTubeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
//        VideoDetailFragment videoDetailFragment = new VideoDetailFragment();
//        Bundle bundle = new Bundle();
//        if (getIntent().getStringExtra("videoid") != null)
//            bundle.putString("videoid", getIntent().getStringExtra("videoid"));
//        videoDetailFragment.setArguments(bundle);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, videoDetailFragment)
//                .commit();
//        final Toolbar toolbar = getActionBarToolbar();
//        toolbar.setNavigationIcon(R.drawable.ic_up);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//            }
//        });
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                toolbar.setTitle("VIDEO DETAIL".toUpperCase());
//            }
//        });
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        // Initializing video player with developer key
        youTubeView.initialize(Utills.KeyID,this);
        getYouTubePlayerProvider().initialize(Utills.KeyID, this);

    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(getIntent().getStringExtra("videoid"));

            // Hiding player controls
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Utills.KeyID, this);
        }
    }
    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayer.Provider) findViewById(R.id.youtube_view);
    }

}
