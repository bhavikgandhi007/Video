package com.initapp.vidmateguide;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Big_Scal on 9/23/2016.
 */
public class WishListActivity extends BaseActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        WishListFragment wishListFragment = new WishListFragment();
        Bundle bundle = new Bundle();
        wishListFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, wishListFragment)
                .commit();
        final Toolbar toolbar = getActionBarToolbar();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle("MY WISHLIST".toUpperCase());
            }
        });

    }




    @Override
    protected String getSelfNavDrawerItem() {
        return "WishList";
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        navigationView.getMenu().findItem(R.id.nav_wishlist).setChecked(true);
        return true;
    }

}
