package com.initapp.vidmateguide;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Ajay on 21/09/2016.
 */
public class CategoryActivity extends BaseActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        categoryFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, categoryFragment)
                .commit();
        final Toolbar toolbar = getActionBarToolbar();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle("CATEGORY".toUpperCase());
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
        return "Category";
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        navigationView.getMenu().findItem(R.id.nav_slideshow).setChecked(true);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
