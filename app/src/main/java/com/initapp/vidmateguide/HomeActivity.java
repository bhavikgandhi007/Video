package com.initapp.vidmateguide;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Big_Scal on 9/22/2016.
 */
public class HomeActivity extends BaseActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        homeFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, homeFragment)
                .commit();
        final Toolbar toolbar = getActionBarToolbar();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle("HOME".toUpperCase());
            }
        });

    }



    @Override
    protected String getSelfNavDrawerItem() {
        return "HOME";
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogCancel = new AlertDialog.Builder(HomeActivity.this);
        alertDialogCancel.setTitle(R.string.app_name);
        alertDialogCancel.setIcon(R.mipmap.ic_launcher);// Set Alert dialog title here
        alertDialogCancel.setMessage("Are you sure to quit?");
        alertDialogCancel.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                        finish();
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        //boolean widgetWasActive = stopService(new Intent(ProductsActivity.this, ChatWidgetService.class));
                    }
                });
        alertDialogCancel.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
        AlertDialog cancelFailedAlertDialog = alertDialogCancel.create();
        cancelFailedAlertDialog.setCancelable(true);
        cancelFailedAlertDialog.show();
    }
}
