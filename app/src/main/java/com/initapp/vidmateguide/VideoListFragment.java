package com.initapp.vidmateguide;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.initapp.vidmateguide.adapter.VideoListAdapter;
import com.initapp.vidmateguide.async.BaseRestAsyncTask;
import com.initapp.vidmateguide.model.Result;
import com.initapp.vidmateguide.model.SearchResult;
import com.initapp.vidmateguide.webapi.VidmateApiService;

import retrofit.RetrofitError;

/**
 * Created by Big_Scal on 9/17/2016.
 */
public class VideoListFragment extends Fragment implements VideoListAdapter.OnLoadMoreListener {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    public static VideoListFragment newInstance() {
        VideoListFragment fragment = new VideoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

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
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        GetSearchData getSearchData = new GetSearchData();
        String part = "snippet";
        String key = "AIzaSyBqvC-Gd9Fxb5opmnMZn2bzG9eh_ec2rGA";
        String order = "rating";
        String query = "tarak mehata";
        String maxresult = "5";
        getSearchData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, part, maxresult, order, query, key);
    }


    @Override
    public void onLoadMore() {

    }

    public class GetSearchData extends BaseRestAsyncTask<String, SearchResult> {

        @Override
        public void onFailure(RetrofitError error) {
            error.printStackTrace();
        }

        @Override
        public void onSuccess(SearchResult result) {
            //Utility.hideProgressDialog();
            mAdapter = new VideoListAdapter(getActivity(), result.getItems(), mRecyclerView);
            ((VideoListAdapter) mAdapter).setOnLoadMoreListener(VideoListFragment.this);
            mRecyclerView.setAdapter(mAdapter);

        }

        @Override
        protected Result<SearchResult> doInBackground(String... requestParams) {
            return VidmateApiService.getInstance().getSearch(requestParams[0], requestParams[1], requestParams[2], requestParams[3], requestParams[4], getActivity());
        }
    }


}
