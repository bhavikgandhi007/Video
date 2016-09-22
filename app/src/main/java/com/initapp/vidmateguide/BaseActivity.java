package com.initapp.vidmateguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Big_Scal on 9/17/2016.
 */
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected Toolbar toolbar;
    public static final String NAVDRAWER_ITEM_INVALID = "NAVDRAWER_ITEM_INVALID";
    public DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        //NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        String selfItem = getSelfNavDrawerItem();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer == null) {
            return;
        }
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (selfItem == NAVDRAWER_ITEM_INVALID) {
            if (navigationView != null) {
                ((ViewGroup) drawer.getParent()).removeView(navigationView);
            }
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            drawer = null;
            return;
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.mipmap.ic_drawer);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v("TAG", "onDrawerOpened: " + getSelfNavDrawerItem() + "");
                    drawer.openDrawer(Gravity.LEFT);
                    Log.v("TAG", "onDrawerOpened: " + getSelfNavDrawerItem() + "");
                    if (getSelfNavDrawerItem() != null) {
                        if (getSelfNavDrawerItem().equalsIgnoreCase("Category")) {
                            navigationView.setCheckedItem(R.id.nav_slideshow);
                        }
                    }
                }
            });
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.i("TAG", "onDrawerOpened: " + getSelfNavDrawerItem() + "");
                if (getSelfNavDrawerItem() != null) {
                    if (getSelfNavDrawerItem().equalsIgnoreCase("Category")) {
                        navigationView.setCheckedItem(R.id.nav_slideshow);
                    }
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        drawer.setDrawerListener(actionBarDrawerToggle);
        toggle.syncState();

    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }

    protected String getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_INVALID;
    }

    protected Toolbar getActionBarToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                getActionBarTextView();
            }
        }
        return toolbar;
    }

    protected TextView getActionBarTextView() {
        TextView titleTextView = null;
        try {
            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(toolbar);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return titleTextView;
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.nav_slideshow) {
            if (getSelfNavDrawerItem() != null) {
                if (getSelfNavDrawerItem().equalsIgnoreCase("Category")) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            }
            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            navigationView.setCheckedItem(R.id.nav_slideshow);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
