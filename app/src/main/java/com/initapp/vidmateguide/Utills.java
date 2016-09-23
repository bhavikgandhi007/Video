package com.initapp.vidmateguide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Big_Scal on 9/22/2016.
 */
public class Utills {

    public static String KeyID = "AIzaSyBqvC-Gd9Fxb5opmnMZn2bzG9eh_ec2rGA";
    public static String MAXRESULT = "5";
    public static String SNIPPET = "snippet";

    public static String ORDER_DATE = "date";
    public static String ORDER_RATE = "rate";

    public static String CHART = "mostpopular";
    public static String REGION = "IN";

    public static final String PREF_WISH_DATA = "pref_wish_data";
    private static final String TAG = Utills.class.getSimpleName();

    static ProgressDialog progress;

    public static String getDensityName(Context context) {
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            return "mdpi";
        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            return "hdpi";
        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            return "hdpi";
        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            return "mdpi";
        } else {
            return "hdpi";
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    public static void showProgressDialog(Context context, String message) {
        progress = new ProgressDialog(context);
        progress.setMessage(message);
        progress.setIndeterminate(false);
        progress.setCancelable(true);
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }

    public static void hideProgressDialog() {
        try {
            if (progress != null && progress.isShowing())
                progress.dismiss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getWishData(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(PREF_WISH_DATA, "");
    }

    public static void setWishData(Context context, String wishData) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_WISH_DATA, wishData).commit();
    }


}
