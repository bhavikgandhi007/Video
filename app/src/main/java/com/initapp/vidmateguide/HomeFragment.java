package com.initapp.vidmateguide;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.initapp.vidmateguide.model.RequestParameter;
import com.initapp.vidmateguide.widget.SlidingTabLayout;

import adapter.ViewPagerAdapter;

/**
 * Created by Big_Scal on 9/22/2016.
 */
public class HomeFragment extends Fragment {

    private ViewPager view_pager;
    ViewPagerAdapter adapter;
    HomeViewPagerAdapter mViewPagerAdapter;
    SlidingTabLayout mSlidingTabLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view_pager = (ViewPager) view.findViewById(R.id.view_pager_home);
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs_home);
        view_pager.setVisibility(View.VISIBLE);
        mViewPagerAdapter = new HomeViewPagerAdapter(getActivity().getSupportFragmentManager());
        view_pager.setAdapter(mViewPagerAdapter);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        Resources res = getResources();
        mSlidingTabLayout.setSelectedIndicatorColors(res.getColor(R.color.colorPrimary));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(view_pager);
        view_pager.setOffscreenPageLimit(2);

    }

    private class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

        private int NUM_ITEMS = 2;
        private String[] content = {"LATEST", "Trailers"};

        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    RequestParameter requestParameter=new RequestParameter();
                    return LatestVideoFragment.newInstance("1",requestParameter);
                case 1:
                    RequestParameter requestParameter1=new RequestParameter();
                    requestParameter1.setPart("snippet");
                    requestParameter1.setVideoCategoryId("1");
                    return LatestVideoFragment.newInstance("2",requestParameter1);
                  //  return VideoListFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;

        }
//Latest Video Song
        // https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&maxResults=10&regionCode=IN&videoCategoryId=10&key={YOUR_API_KEY}

        //latest trailers
        //https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&maxResults=10&regionCode=IN&videoCategoryId=1&key={YOUR_API_KEY}

        //latest Shows
        //https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&maxResults=10&regionCode=IN&videoCategoryId=24&key={YOUR_API_KEY}
        @Override
        public CharSequence getPageTitle(int position) {
            return content[position];
        }
    }
}
