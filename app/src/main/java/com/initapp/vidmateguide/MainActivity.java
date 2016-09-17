package com.initapp.vidmateguide;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.initapp.vidmateguide.async.BaseRestAsyncTask;

import adapter.ViewPagerAdapter;
import com.initapp.vidmateguide.model.Result;
import com.initapp.vidmateguide.model.SearchResult;
import retrofit.RetrofitError;
import com.initapp.vidmateguide.webapi.VidmateApiService;
import com.initapp.vidmateguide.widget.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

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
    private ViewPager view_pager;
    ViewPagerAdapter adapter;
    HomeViewPagerAdapter mViewPagerAdapter;
    SlidingTabLayout mSlidingTabLayout;
    TabLayout tabLayout;
    CharSequence Titles[] = {"GENRES", "LATEST"};
    int Numboftabs = 2;
    String shareApp = "https://play.google.com/store/apps/details?id=com.initapp.vidmateguide";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
       // adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        // Set up the ViewPager with the sections adapter.
       // mViewPager = (ViewPager) findViewById(R.id.container);
      //  mViewPager.setAdapter(adapter);

       // tabLayout = (TabLayout) findViewById(R.id.tabs);
       // tabLayout.setupWithViewPager(mViewPager);


        view_pager = (ViewPager) findViewById(R.id.view_pager_home);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs_home);

        view_pager.setVisibility(View.VISIBLE);
        mViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(mViewPagerAdapter);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        Resources res = getResources();
        mSlidingTabLayout.setSelectedIndicatorColors(res.getColor(R.color.colorPrimary));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(view_pager);
        view_pager.setOffscreenPageLimit(2);
        if (mSlidingTabLayout != null) {
            mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
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

    private class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

        private int NUM_ITEMS = 2;
        private String[] content = {"LATEST", "POPULAR"};

        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return VideoListFragment.newInstance();
                case 1:
                    return VideoListFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return content[position];
        }
    }


}
