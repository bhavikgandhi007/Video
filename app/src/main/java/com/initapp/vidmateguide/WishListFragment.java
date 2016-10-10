package com.initapp.vidmateguide;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.initapp.vidmateguide.adapter.LatestVideoAdapter;
import com.initapp.vidmateguide.async.BaseRestAsyncTask;
import com.initapp.vidmateguide.model.Result;
import com.initapp.vidmateguide.model.VideoResult;
import com.initapp.vidmateguide.webapi.VidmateApiService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by Big_Scal on 9/23/2016.
 */
public class WishListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RelativeLayout imageLoading;
    ArrayList<String> wishItems;
    GetWishListVideo getWishListVideo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_videolist, container, false);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        String density = Utills.getDensityName(getActivity());
        if (density.equals("mdpi")) {
            mLayoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            mLayoutManager = new GridLayoutManager(getActivity(), 1);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        imageLoading = (RelativeLayout) view.findViewById(R.id.imageLoading);
        retry();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getWishListVideo != null) {
            getWishListVideo.cancel(true);
        }
    }

    private void retry() {
        if (!Utills.getWishData(getActivity()).equals("")) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<String>>() {
            }.getType();
            wishItems = gson.fromJson(Utills.getWishData(getActivity()), type);
            Collections.reverse(wishItems);
            String wishlist = Arrays.toString(wishItems.toArray()).replace("[", "").replace("]", "");
            showProgress();
            getWishListVideo = new GetWishListVideo();
            getWishListVideo.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Utills.SNIPPET, wishlist, Utills.KeyID);
        }
    }


    public class GetWishListVideo extends BaseRestAsyncTask<String, VideoResult> {

        @Override
        public void onFailure(RetrofitError error) {
            error.printStackTrace();
        }

        @Override
        public void onSuccess(VideoResult result) {
            //Utility.hideProgressDialog();
            hideProgress();
            mAdapter = new LatestVideoAdapter(getActivity(), result.getItems(), mRecyclerView);
            mRecyclerView.setAdapter(mAdapter);

        }

        @Override
        protected Result<VideoResult> doInBackground(String... requestParams) {
            return VidmateApiService.getInstance().getWishListVideo(requestParams[0], requestParams[1], requestParams[2], getActivity());
        }
    }

    private void showProgress() {
        imageLoading.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void hideProgress() {
        imageLoading.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

}
