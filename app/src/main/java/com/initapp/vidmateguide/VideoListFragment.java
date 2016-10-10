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

import com.initapp.vidmateguide.adapter.VideoListAdapter;
import com.initapp.vidmateguide.async.BaseRestAsyncTask;
import com.initapp.vidmateguide.model.Items;
import com.initapp.vidmateguide.model.RequestParameter;
import com.initapp.vidmateguide.model.Result;
import com.initapp.vidmateguide.model.SearchResult;
import com.initapp.vidmateguide.model.VideoResult;
import com.initapp.vidmateguide.webapi.VidmateApiService;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by Big_Scal on 9/17/2016.
 */
public class VideoListFragment extends Fragment implements VideoListAdapter.OnLoadMoreListener {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private GetSearchData getSearchData;
    private GetSearchChannelID getSearchChannelID;
    private GetCategoryVideos getCategoryVideos;
    private GetEpisodeByChannelID getEpisodeByChannelID;
    private ArrayList<Items> itemses = new ArrayList<>();
    private String nextPageToken;
    private RelativeLayout imageLoading;
    private int pageCount;

    public static VideoListFragment newInstance() {
        VideoListFragment fragment = new VideoListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /*
    * get search result by channel id
    * */
    public static VideoListFragment newInstance(String reqType, RequestParameter requestParameter) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle args = new Bundle();
        args.putString("reqType", reqType);
        args.putSerializable("parameter", requestParameter);
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
    public void onLoadMore() {
        if (itemses.size() < 100) {
            String reqType = getArguments().getString("reqType");
            RequestParameter requestParameter = (RequestParameter) getArguments().getSerializable("parameter");
            requestParameter.setNextPageToken(nextPageToken);
            Items emptyItem = new Items();
            emptyItem = null;
            itemses.add(emptyItem);
            mAdapter.notifyItemInserted(itemses.size() - 1);
            if (reqType.equals("1")) {
                getSearchData = new GetSearchData();
                getSearchData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestParameter.getPart(),
                        Utills.MAXRESULT, requestParameter.getOrder(), requestParameter.getQ(), Utills.KeyID, nextPageToken);
            } else if (reqType.equals("2")) {
                getSearchChannelID = new GetSearchChannelID();
                getSearchChannelID.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestParameter.getPart(),
                        requestParameter.getMaxResults(), requestParameter.getOrder(), requestParameter.getChannelId(), Utills.KeyID, nextPageToken);
            } else if (reqType.equals("3")) {
                getCategoryVideos = new GetCategoryVideos();
                getCategoryVideos.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestParameter.getPart(),
                        requestParameter.getMaxResults(), requestParameter.getOrder(),
                        requestParameter.getVideoCategoryId(), requestParameter.getType(), Utills.KeyID, nextPageToken);
            } else if (reqType.equals("4")) {
                getEpisodeByChannelID = new GetEpisodeByChannelID();
                getEpisodeByChannelID.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestParameter.getPart(),
                        requestParameter.getMaxResults(), requestParameter.getOrder(),
                        requestParameter.getQ(), requestParameter.getChannelId(), Utills.KeyID, nextPageToken);
            }
        }
    }

    private void retry() {
        String reqType = getArguments().getString("reqType");
        RequestParameter requestParameter = (RequestParameter) getArguments().getSerializable("parameter");
        showProgress();
        if (reqType.equals("1")) {
            getSearchData = new GetSearchData();
            getSearchData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestParameter.getPart(),
                    Utills.MAXRESULT, requestParameter.getOrder(), requestParameter.getQ(), Utills.KeyID, "");
        } else if (reqType.equals("2")) {
            getSearchChannelID = new GetSearchChannelID();
            getSearchChannelID.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestParameter.getPart(),
                    requestParameter.getMaxResults(), requestParameter.getOrder(), requestParameter.getChannelId(), Utills.KeyID, "");
        } else if (reqType.equals("3")) {
            getCategoryVideos = new GetCategoryVideos();
            getCategoryVideos.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestParameter.getPart(),
                    requestParameter.getMaxResults(), requestParameter.getOrder(),
                    requestParameter.getVideoCategoryId(), requestParameter.getType(), Utills.KeyID, "");
        } else if (reqType.equals("4")) {
            getEpisodeByChannelID = new GetEpisodeByChannelID();
            getEpisodeByChannelID.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, requestParameter.getPart(),
                    requestParameter.getMaxResults(), requestParameter.getOrder(),
                    requestParameter.getQ(), requestParameter.getChannelId(), Utills.KeyID, "");
        }
    }

    public class GetSearchData extends BaseRestAsyncTask<String, SearchResult> {

        @Override
        public void onFailure(RetrofitError error) {
            error.printStackTrace();
        }

        @Override
        public void onSuccess(SearchResult result) {
            //Utility.hideProgressDialog();
            hideProgress();

            if (result.getItems() != null) {
                if (pageCount == 0) {
                    mAdapter = new VideoListAdapter(getActivity(), result.getItems(), mRecyclerView);
                    ((VideoListAdapter) mAdapter).setOnLoadMoreListener(VideoListFragment.this);
                    mRecyclerView.setAdapter(mAdapter);
                    itemses = result.getItems();
                    nextPageToken = result.getNextPageToken();
                    pageCount++;
                } else {
                    //Remove loading item
                    itemses.remove(itemses.size() - 1);
                    mAdapter.notifyItemRemoved(itemses.size());
                    nextPageToken = result.getNextPageToken();
                    itemses.addAll(result.getItems());
                    mAdapter.notifyDataSetChanged();
                    ((VideoListAdapter) mAdapter).setLoaded();
                }
            }

        }

        @Override
        protected Result<SearchResult> doInBackground(String... requestParams) {
            return VidmateApiService.getInstance().getSearch(requestParams[0], requestParams[1], requestParams[2], requestParams[3], requestParams[4], requestParams[5], getActivity());
        }
    }

    public class GetSearchChannelID extends BaseRestAsyncTask<String, SearchResult> {

        @Override
        public void onFailure(RetrofitError error) {
            error.printStackTrace();
        }

        @Override
        public void onSuccess(SearchResult result) {
            //Utility.hideProgressDialog();
            hideProgress();
            if (result.getItems() != null) {
                if (pageCount == 0) {
                    mAdapter = new VideoListAdapter(getActivity(), result.getItems(), mRecyclerView);
                    ((VideoListAdapter) mAdapter).setOnLoadMoreListener(VideoListFragment.this);
                    mRecyclerView.setAdapter(mAdapter);
                    itemses = result.getItems();
                    nextPageToken = result.getNextPageToken();
                    pageCount++;
                } else {
                    //Remove loading item
                    itemses.remove(itemses.size() - 1);
                    mAdapter.notifyItemRemoved(itemses.size());
                    nextPageToken = result.getNextPageToken();
                    itemses.addAll(result.getItems());
                    mAdapter.notifyDataSetChanged();
                    ((VideoListAdapter) mAdapter).setLoaded();
                }
            }

        }

        @Override
        protected Result<SearchResult> doInBackground(String... requestParams) {
            return VidmateApiService.getInstance().getSearchChannelID(requestParams[0], requestParams[1], requestParams[2], requestParams[3], requestParams[4], requestParams[5], getActivity());
        }
    }

    public class GetCategoryVideos extends BaseRestAsyncTask<String, SearchResult> {

        @Override
        public void onFailure(RetrofitError error) {
            error.printStackTrace();
        }

        @Override
        public void onSuccess(SearchResult result) {
            //Utility.hideProgressDialog();
            hideProgress();
            if (result.getItems() != null) {
                if (pageCount == 0) {
                    mAdapter = new VideoListAdapter(getActivity(), result.getItems(), mRecyclerView);
                    ((VideoListAdapter) mAdapter).setOnLoadMoreListener(VideoListFragment.this);
                    mRecyclerView.setAdapter(mAdapter);
                    itemses = result.getItems();
                    nextPageToken = result.getNextPageToken();
                    pageCount++;
                } else {
                    //Remove loading item
                    itemses.remove(itemses.size() - 1);
                    mAdapter.notifyItemRemoved(itemses.size());
                    nextPageToken = result.getNextPageToken();
                    itemses.addAll(result.getItems());
                    mAdapter.notifyDataSetChanged();
                    ((VideoListAdapter) mAdapter).setLoaded();
                }
            }

        }

        @Override
        protected Result<SearchResult> doInBackground(String... requestParams) {
            return VidmateApiService.getInstance().getCategoryVideos(requestParams[0], requestParams[1], requestParams[2], requestParams[3], requestParams[4], requestParams[5], requestParams[6], getActivity());
        }
    }

    public class GetEpisodeByChannelID extends BaseRestAsyncTask<String, SearchResult> {

        @Override
        public void onFailure(RetrofitError error) {
            error.printStackTrace();
        }

        @Override
        public void onSuccess(SearchResult result) {
            //Utility.hideProgressDialog();
            hideProgress();
            if (result.getItems() != null) {
                if (pageCount == 0) {
                    mAdapter = new VideoListAdapter(getActivity(), result.getItems(), mRecyclerView);
                    ((VideoListAdapter) mAdapter).setOnLoadMoreListener(VideoListFragment.this);
                    mRecyclerView.setAdapter(mAdapter);
                    itemses = result.getItems();
                    nextPageToken = result.getNextPageToken();
                    pageCount++;
                } else {
                    //Remove loading item
                    itemses.remove(itemses.size() - 1);
                    mAdapter.notifyItemRemoved(itemses.size());
                    nextPageToken = result.getNextPageToken();
                    itemses.addAll(result.getItems());
                    mAdapter.notifyDataSetChanged();
                    ((VideoListAdapter) mAdapter).setLoaded();
                }
            }

        }

        @Override
        protected Result<SearchResult> doInBackground(String... requestParams) {
            return VidmateApiService.getInstance().getEpisodeByChannelID(requestParams[0], requestParams[1], requestParams[2], requestParams[3], requestParams[4], requestParams[5], requestParams[6], getActivity());
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
