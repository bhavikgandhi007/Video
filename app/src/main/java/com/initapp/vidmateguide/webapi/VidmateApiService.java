package com.initapp.vidmateguide.webapi;


import android.content.Context;

import com.initapp.vidmateguide.model.Result;
import com.initapp.vidmateguide.model.SearchResult;
import com.initapp.vidmateguide.model.VideoResult;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;

import java.util.Arrays;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;


public class VidmateApiService {

    private static final EnvironmentConfig CONFIG = new EnvironmentConfig();
    private static VidmateApiService vidmateApiService = new VidmateApiService();
    private VidmateApiClient service;

    public static VidmateApiService getInstance() {
        if (vidmateApiService == null)
            return new VidmateApiService();
        else
            return vidmateApiService;
    }


    public Result<SearchResult> getSearch(String part, String maxresult, String order, String query, String key, Context context) {
        try {
            return new Result<>(getService(context).getSearch(part, maxresult, order, query, key));
        } catch (RetrofitError error) {
            return new Result<>(error);
        }
    }

    public Result<VideoResult> getLatestVideo(String part, String maxresult, String chart, String key, Context context) {
        try {
            return new Result<>(getService(context).getLatestVideo(part, maxresult, chart, key));
        } catch (RetrofitError error) {
            return new Result<>(error);
        }
    }

    public Result<SearchResult> getSearchChannelID(String part, String maxresult, String order, String channelID, String key, Context context) {
        try {
            return new Result<>(getService(context).getSearchChannelID(part, maxresult, order, channelID, key));
        } catch (RetrofitError error) {
            return new Result<>(error);
        }
    }

    public Result<SearchResult> getCategoryVideos(String part, String maxresult, String order, String videocategoryID, String type, String key, Context context) {
        try {
            return new Result<>(getService(context).getCategoryVideos(part, maxresult, order, videocategoryID, type, key));
        } catch (RetrofitError error) {
            return new Result<>(error);
        }
    }

    public Result<VideoResult> getVideoDetails(String part, String id, String key, Context context) {
        try {
            return new Result<>(getService(context).getVideoDetails(part, id, key));
        } catch (RetrofitError error) {
            return new Result<>(error);
        }
    }

    public Result<SearchResult> getEpisodeByChannelID(String part, String maxresult, String order, String query, String channelID, String key, Context context) {
        try {
            return new Result<>(getService(context).getEpisodeByChannelID(part, maxresult, order, query, channelID, key));
        } catch (RetrofitError error) {
            return new Result<>(error);
        }
    }

    private VidmateApiClient getService(final Context context) {
        if (service == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
            RestAdapter retrofit = new RestAdapter.Builder()
                    .setEndpoint(EnvironmentConfig.getBaseUrl())
                    .setLogLevel(CONFIG.getRetrofitLogLevel())
                    .setClient(new OkClient(okHttpClient))
                    .build();

            service = retrofit.create(VidmateApiClient.class);
        }
        return service;
    }

}
