package com.initapp.vidmateguide;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

/**
 * Created by Ajay on 24/09/2016.
 */
public class AboutUsActivity extends BaseActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        AboutUsFragment aboutUsFragment = new AboutUsFragment();
        Bundle bundle = new Bundle();
        aboutUsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, aboutUsFragment)
                .commit();
        final Toolbar toolbar = getActionBarToolbar();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle("About Us".toUpperCase());
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
        return "ABOUT";
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        navigationView.getMenu().findItem(R.id.nav_about).setChecked(true);
        return true;
    }
}
