package com.initapp.vidmateguide;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.initapp.vidmateguide.adapter.LatestVideoAdapter;
import com.initapp.vidmateguide.async.BaseRestAsyncTask;
import com.initapp.vidmateguide.model.RequestParameter;
import com.initapp.vidmateguide.model.Result;
import com.initapp.vidmateguide.model.VideoResult;
import com.initapp.vidmateguide.webapi.VidmateApiService;

import retrofit.RetrofitError;

/**
 * Created by Big_Scal on 9/22/2016.
 */
public class LatestVideoFragment extends Fragment implements LatestVideoAdapter.OnLoadMoreListener {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    GetLatestVideo getLatestVideo;
    GetLatestVideoCategory getLatestVideoCategory;

    public static LatestVideoFragment newInstance(String reqType, RequestParameter requestParameter) {
        LatestVideoFragment fragment = new LatestVideoFragment();
        Bundle args = new Bundle();
        args.putString("reqType", reqType);
        args.putSerializable("parameter", requestParameter);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_videolist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        String density=Utills.getDensityName(getActivity());
        if(density.equals("mdpi")){
            mLayoutManager = new GridLayoutManager(getActivity(), 2);
        }else{
            mLayoutManager = new GridLayoutManager(getActivity(), 1);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        retry();
    }

    public void retry() {
        String reqType = getArguments().getString("reqType");
        RequestParameter requestParameter = (RequestParameter) getArguments().getSerializable("parameter");
        if (reqType.equals("1")) {
            getLatestVideo = new GetLatestVideo();
            getLatestVideo.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Utills.SNIPPET, Utills.MAXRESULT, Utills.CHART, Utills.KeyID);
        } else if (reqType.equals("2")) {
            getLatestVideoCategory = new GetLatestVideoCategory();
            getLatestVideoCategory.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestParameter.getPart(), Utills.MAXRESULT, Utills.CHART, Utills.REGION, requestParameter.getVideoCategoryId(), Utills.KeyID);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getLatestVideo != null) {
            getLatestVideo.cancel(true);
        }
        if (getLatestVideoCategory != null) {
            getLatestVideoCategory.cancel(true);
        }
    }

    @Override
    public void onLoadMore() {

    }

    public class GetLatestVideo extends BaseRestAsyncTask<String, VideoResult> {

        @Override
        public void onFailure(RetrofitError error) {
            error.printStackTrace();
        }

        @Override
        public void onSuccess(VideoResult result) {
            //Utility.hideProgressDialog();
            mAdapter = new LatestVideoAdapter(getActivity(), result.getItems(), mRecyclerView);
            ((LatestVideoAdapter) mAdapter).setOnLoadMoreListener(LatestVideoFragment.this);
            mRecyclerView.setAdapter(mAdapter);

        }

        @Override
        protected Result<VideoResult> doInBackground(String... requestParams) {
            return VidmateApiService.getInstance().getLatestVideo(requestParams[0], requestParams[1], requestParams[2], requestParams[3], getActivity());
        }
    }

    public class GetLatestVideoCategory extends BaseRestAsyncTask<String, VideoResult> {

        @Override
        public void onFailure(RetrofitError error) {
            error.printStackTrace();
        }

        @Override
        public void onSuccess(VideoResult result) {
            //Utility.hideProgressDialog();
            mAdapter = new LatestVideoAdapter(getActivity(), result.getItems(), mRecyclerView);
            ((LatestVideoAdapter) mAdapter).setOnLoadMoreListener(LatestVideoFragment.this);
            mRecyclerView.setAdapter(mAdapter);

        }

        @Override
        protected Result<VideoResult> doInBackground(String... requestParams) {
            return VidmateApiService.getInstance().getLatestVideoCategory(requestParams[0], requestParams[1], requestParams[2], requestParams[3], requestParams[4], requestParams[5], getActivity());
        }
    }
}
