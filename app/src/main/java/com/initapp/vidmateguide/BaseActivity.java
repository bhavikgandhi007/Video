package com.initapp.vidmateguide;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.initapp.vidmateguide.widget.DividerItemDecoration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
//        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
//        navMenuView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
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
        if (id == R.id.action_search) {
            startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            if (getSelfNavDrawerItem() != null) {
                if (getSelfNavDrawerItem().equalsIgnoreCase("HOME")) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            }
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        } else if (id == R.id.nav_categories) {
            if (getSelfNavDrawerItem() != null) {
                if (getSelfNavDrawerItem().equalsIgnoreCase("Category")) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            }
            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            finish();
        } else if (id == R.id.nav_wishlist) {
            if (getSelfNavDrawerItem() != null) {
                if (getSelfNavDrawerItem().equalsIgnoreCase("MY WISHLIST")) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            }
            startActivity(new Intent(getApplicationContext(), WishListActivity.class));
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            finish();
        } else if (id == R.id.nav_about) {
            if (getSelfNavDrawerItem() != null) {
                if (getSelfNavDrawerItem().equalsIgnoreCase("ABOUT")) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            }
            startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            finish();
        } else if (id == R.id.nav_term) {
            if (getSelfNavDrawerItem() != null) {
                if (getSelfNavDrawerItem().equalsIgnoreCase("PRIVACY")) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            }
            startActivity(new Intent(getApplicationContext(), PrivacyPolicyActivity.class));
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            finish();
        } else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_rate) {
            rateApp();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void shareApp() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Vidmate Guide-Free Video");
            String sAux = "\nLet me recommend you this application\n\n";
            sAux = sAux + " Vidmate , Best Video App for Latest Videos,Trailers, Video song,Tv -Show and many more..\nDownload and Play Videos!\n \n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.initapp.vidmateguide \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "Share App"));
        } catch (Exception e) { //e.toString();
        }
    }
    private void rateApp(){
        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
        }
    }


}
