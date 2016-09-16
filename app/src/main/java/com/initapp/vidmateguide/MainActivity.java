package com.initapp.vidmateguide;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.initapp.vidmateguide.async.BaseRestAsyncTask;

import adapter.ViewPagerAdapter;
import com.initapp.vidmateguide.model.Result;
import com.initapp.vidmateguide.model.SearchResult;
import retrofit.RetrofitError;
import com.initapp.vidmateguide.webapi.VidmateApiService;

public class MainActivity extends Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    ViewPagerAdapter adapter;
    TabLayout tabLayout;
    CharSequence Titles[] = {"GENRES", "LATEST"};
    int Numboftabs = 2;
    String shareApp = "https://play.google.com/store/apps/details?id=com.initapp.vidmateguide";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        //adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        // Set up the ViewPager with the sections adapter.
       // mViewPager = (ViewPager) findViewById(R.id.container);
      //  mViewPager.setAdapter(adapter);

       // tabLayout = (TabLayout) findViewById(R.id.tabs);
       // tabLayout.setupWithViewPager(mViewPager);
        GetSearchData getSearchData=new GetSearchData();
        String part="snippet";
        String key="AIzaSyBqvC-Gd9Fxb5opmnMZn2bzG9eh_ec2rGA";
        String order="rating";
        String query="tarak mehata";
        String maxresult="5";
        getSearchData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,part,maxresult,order,query,key);

    }


    public class GetSearchData extends BaseRestAsyncTask<String, SearchResult> {

        @Override
        public void onFailure(RetrofitError error) {
            error.printStackTrace();
        }

        @Override
        public void onSuccess(SearchResult result) {
            //Utility.hideProgressDialog();

        }

        @Override
        protected Result<SearchResult> doInBackground(String... requestParams) {
            return VidmateApiService.getInstance().getSearch(requestParams[0], requestParams[1],requestParams[2],requestParams[3],requestParams[4], MainActivity.this);
        }
    }


    private boolean MyStartActivity(Intent aIntent) {
        try {
            startActivity(aIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }



}
