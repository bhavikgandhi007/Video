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

    public static LatestVideoFragment newInstance() {
        LatestVideoFragment fragment = new LatestVideoFragment();
        Bundle args = new Bundle();
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
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        retry();
    }

    public void retry() {
        getLatestVideo = new GetLatestVideo();
        getLatestVideo.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Utills.SNIPPET, Utills.MAXRESULT, Utills.CHART, Utills.KeyID);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getLatestVideo != null) {
            getLatestVideo.cancel(true);
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
}
