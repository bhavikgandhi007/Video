package com.initapp.vidmateguide;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.initapp.vidmateguide.model.RequestParameter;

/**
 * Created by Big_Scal on 9/23/2016.
 */
public class VideoListActivity extends BaseActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        VideoListFragment videoListFragment = new VideoListFragment();
        Bundle bundle = new Bundle();
        videoListFragment.setArguments(bundle);
        if (getIntent().getStringExtra("keyword") != null) {
            RequestParameter requestParameter = new RequestParameter();
            requestParameter.setQ(getIntent().getStringExtra("keyword"));
            requestParameter.setPart("snippet");
            requestParameter.setOrder("rating");
            bundle.putSerializable("parameter", requestParameter);
            bundle.putString("reqType", "1");
        } else {
            if (getIntent().getStringExtra("reqType") != null) {
                bundle.putSerializable("parameter", getIntent().getSerializableExtra("parameter"));
                bundle.putString("reqType", getIntent().getStringExtra("reqType"));
            }
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, videoListFragment)
                .commit();
        final Toolbar toolbar = getActionBarToolbar();
        toolbar.setNavigationIcon(R.drawable.ic_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (getIntent().getStringExtra("keyword") != null) {
                    toolbar.setTitle(getIntent().getStringExtra("keyword").toUpperCase());
                } else if (getIntent().getStringExtra("title") != null) {
                    toolbar.setTitle(getIntent().getStringExtra("title").toUpperCase());
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        return true;
    }


    @Override
    protected String getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_INVALID;
    }


}
