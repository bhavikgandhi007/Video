package com.initapp.vidmateguide.webapi;


import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import com.initapp.vidmateguide.model.Result;
import com.initapp.vidmateguide.model.SearchResult;
import com.squareup.okhttp.Protocol;

import java.util.ArrayList;
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

    public Result<SearchResult> getSearch2(String part, Context context) {
        try {
            return new Result<>(getService(context).getSearch2(part));
        } catch (RetrofitError error) {
            return new Result<>(error);
        }
    }

    private VidmateApiClient getService(final Context context) {
        if (service == null) {
            OkHttpClient okHttpClient=new OkHttpClient();
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
