package com.initapp.vidmateguide;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.initapp.vidmateguide.async.BaseRestAsyncTask;
import com.initapp.vidmateguide.model.Result;
import com.initapp.vidmateguide.model.VideoResult;
import com.initapp.vidmateguide.webapi.VidmateApiService;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by Big_Scal on 9/23/2016.
 */
public class VideoDetailFragment extends Fragment {
/*
    private GetVideoDetails getVideoDetails;
    TextView txt_video_title, txt_video_desc;
    ImageView img_wishlist, img_share, img_download;
    ArrayList<String> wishItems;
    Gson gson;
    String videoID;
    YouTubePlayerView youTubePlayerView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_video_detail, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_video_title = (TextView) view.findViewById(R.id.txt_video_title);
        txt_video_desc = (TextView) view.findViewById(R.id.txt_video_desc);
        img_wishlist = (ImageView) view.findViewById(R.id.img_wishlist);
        img_share = (ImageView) view.findViewById(R.id.img_download);
        img_download = (ImageView) view.findViewById(R.id.img_download);
        gson = new Gson();
        if (getArguments().getString("videoid") != null) {
            videoID = getArguments().getString("videoid");
            Log.i("TAG", "onCreate: videoid" + getArguments().getString("videoid"));
            youTubePlayerView = (YouTubePlayerView) view.findViewById(R.id.youtube_player_view);
            youTubePlayerView.initialize(new AbstractYouTubeListener() {
                @Override
                public void onReady() {

                    youTubePlayerView.loadVideo(videoID, 0);

                }
            }, true);
        }
        img_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Type type = new TypeToken<List<String>>() {
                }.getType();

                if (Utills.getWishData(getActivity()) == "") {
                    wishItems = new ArrayList<>();

                } else {
                    wishItems = gson.fromJson(Utills.getWishData(getActivity()), type);
                }
                if (!wishItems.contains(String.valueOf(videoID))) {
                    wishItems.add(videoID);
                    Toast.makeText(getActivity(), "Sucessfully Added", Toast.LENGTH_LONG).show();
                    Utills.setWishData(getActivity(), gson.toJson(wishItems));
                }
            }
        });
        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        img_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Video Can't download", Toast.LENGTH_SHORT).show();
            }
        });
        retry();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getVideoDetails != null) {
            getVideoDetails.cancel(true);
        }
        if (youTubePlayerView != null) {
            youTubePlayerView.release();
        }

    }

    private void retry() {
        getVideoDetails = new GetVideoDetails();
        getVideoDetails.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Utills.SNIPPET, getArguments().getString("videoid"), Utills.KeyID);
    }

    public class GetVideoDetails extends BaseRestAsyncTask<String, VideoResult> {

        @Override
        public void onFailure(RetrofitError error) {
            error.printStackTrace();
        }

        @Override
        public void onSuccess(VideoResult result) {
            //Utility.hideProgressDialog();
            if (result.getItems() != null) {
                txt_video_title.setText(result.getItems().get(0).getSnippet().getTitle());
                txt_video_desc.setText(result.getItems().get(0).getSnippet().getDescription());
            }

        }

        @Override
        protected Result<VideoResult> doInBackground(String... requestParams) {
            return VidmateApiService.getInstance().getVideoDetails(requestParams[0], requestParams[1], requestParams[2], getActivity());
        }
    }
    */
}
