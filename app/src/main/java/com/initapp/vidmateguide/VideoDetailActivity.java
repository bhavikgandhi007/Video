package com.initapp.vidmateguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pierfrancescosoffritti.youtubeplayer.AbstractYouTubeListener;
import com.pierfrancescosoffritti.youtubeplayer.YouTubePlayerView;

/**
 * Created by Big_Scal on 9/16/2016.
 */
public class VideoDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        if (getIntent().getStringExtra("videoid") != null) {
            final String videoID=getIntent().getStringExtra("videoid");
            Log.i("TAG", "onCreate: videoid"+getIntent().getStringExtra("videoid"));
            final YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
            youTubePlayerView.initialize(new AbstractYouTubeListener() {
                @Override
                public void onReady() {
                    youTubePlayerView.loadVideo(videoID, 0);
                }
            }, true);
        }
    }

}
