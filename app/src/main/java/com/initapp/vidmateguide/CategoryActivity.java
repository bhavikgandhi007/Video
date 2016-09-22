package com.initapp.vidmateguide;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Ajay on 21/09/2016.
 */
public class CategoryActivity extends BaseActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        categoryFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, categoryFragment)
                .commit();

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
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void onBackPressed() {

    }
}
