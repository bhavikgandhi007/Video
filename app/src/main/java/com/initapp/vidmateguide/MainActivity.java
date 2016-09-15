package com.initapp.vidmateguide;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import adapter.ViewPagerAdapter;

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
    private ViewPager mViewPager;
    ViewPagerAdapter adapter;
    TabLayout tabLayout;
    CharSequence Titles[] = {"GENRES", "LATEST"};
    int Numboftabs = 2;
    String shareApp = "https://play.google.com/store/apps/details?id=com.initapp.vidmateguide";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_rateus) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //Try Google play
            intent.setData(Uri.parse("market://details?id=com.initapp.vidmateguide"));
            if (!MyStartActivity(intent)) {
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.initapp.vidmateguide"));
                if (!MyStartActivity(intent)) {
                    Toast.makeText(this, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        }
        else if(id == R.id.action_shareapp) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Free Video Application (Open it in Google Play Store to Download the Application)");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareApp);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
            return true;
        }
        else if(id == R.id.action_vaultappi) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //Try Google play
            intent.setData(Uri.parse("market://details?id=com.initapp.securevault"));
            if (!MyStartActivity(intent)) {
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.initapp.securevault"));
                if (!MyStartActivity(intent)) {
                    Toast.makeText(this, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        } else if (id == R.id.action_applocki) {

            Toast.makeText(MainActivity.this,"Coming Soon..!",Toast.LENGTH_LONG).show();

            return true;
        }


        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    private boolean MyStartActivity(Intent aIntent) {
        try {
            startActivity(aIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }



}
