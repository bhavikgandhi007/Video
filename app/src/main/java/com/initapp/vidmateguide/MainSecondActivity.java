package com.initapp.vidmateguide;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class MainSecondActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        MainFragment filterFragment = new MainFragment();
        Bundle bundle = new Bundle();
        filterFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, filterFragment)
                .commit();
        final Toolbar toolbar = getActionBarToolbar();
//        toolbar.setNavigationIcon(R.drawable.ic_up);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//            }
//        });
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle("Filter".toUpperCase());
            }
        });


    }
    @Override
    protected String getSelfNavDrawerItem() {
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        return true;
    }
}