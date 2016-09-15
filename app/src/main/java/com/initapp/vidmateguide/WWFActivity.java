package com.initapp.vidmateguide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.VideoListAdapter;
import model.AppData;
import util.DataURL_Bollywood;
import util.DataURL_WWF;

/**
 * Created by Piyush on 8/30/2016.
 */
public class WWFActivity extends Activity {


    ProgressDialog progressDataLoad=null;
    String wwfData;
    DataURL_WWF dataURL=new DataURL_WWF();
    AppData appData;
    VideoListAdapter videolistAdapter;
    public static ListView listView;
    public static ArrayList<AppData> appDatas=new ArrayList<AppData>();
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences videoPreferences;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_list);

        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-4193835134456390/3127891468");
        AdView adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
 		/*// Add a test device to show Test Ads
 		 .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
 		 .addTestDevice("")*/
                .build();
        adView.loadAd(adRequest);
        interstitial.loadAd(adRequest);
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                displayInterstitial();
            }
        });

        videoPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = videoPreferences.edit();
        editor.putInt("Class", 10);
        editor.commit();

        listView=(ListView)findViewById(R.id.listView);

        LoadingData LoadData = new LoadingData();
        LoadData.execute();
        progressDataLoad = ProgressDialog.show(WWFActivity.this, "","Please wait loading data", true);

    }

    class LoadingData extends AsyncTask<Void, Void, String>
    {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub

            wwfData= dataURL.getWWF();

            try {

                JSONObject jsonArray = new JSONObject(wwfData);
                JSONArray jsonARRAY=jsonArray.getJSONArray("items");

                for (int i = 0; i < jsonARRAY.length(); i++) {
                    appData=new AppData();
                    JSONObject jsOBJECT=jsonARRAY.getJSONObject(i);

                    // VideoID
                    JSONObject objectVideoID=jsOBJECT.getJSONObject("id");
                    String id = objectVideoID.getString("videoId");
                    appData.VideoId = id;

                    // Video Title
                    JSONObject objectVideoTitle=jsOBJECT.getJSONObject("snippet");
                    String vTitle = objectVideoTitle.getString("title");
                    appData.Title = vTitle;

                    // Video Image
                    JSONObject objectVideoImage=jsOBJECT.getJSONObject("snippet");
                    JSONObject objectVideoImage1=objectVideoImage.getJSONObject("thumbnails");
                    JSONObject objectVideoImage2=objectVideoImage1.getJSONObject("high");
                    String vImage = objectVideoImage2.getString("url");
                    appData.ImageUrl = vImage;


                    appDatas.add(appData);

                }

            } catch (Exception e) {
                // TODO: handle exception
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            progressDataLoad.dismiss();
            videolistAdapter=new VideoListAdapter(WWFActivity.this,appDatas);
            listView.setAdapter(videolistAdapter);
            super.onPostExecute(result);
        }
    }
    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}