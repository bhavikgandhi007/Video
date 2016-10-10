package com.initapp.vidmateguide;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

/**
 * Created by Ajay on 24/09/2016.
 */
public class PrivacyPolicyActivity extends BaseActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        PrivacyFragment privacyFragment = new PrivacyFragment();
        Bundle bundle = new Bundle();
        privacyFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, privacyFragment)
                .commit();
        final Toolbar toolbar = getActionBarToolbar();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle("Privacy Policy".toUpperCase());
            }
        });

    }



    @Override
    protected String getSelfNavDrawerItem() {
        return "PRIVACY";
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        navigationView.getMenu().findItem(R.id.nav_term).setChecked(true);
        return true;
    }
}
