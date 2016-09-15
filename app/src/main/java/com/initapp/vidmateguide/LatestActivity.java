package com.initapp.vidmateguide;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.LatestVideoAdapter;
import adapter.VideoListAdapter;
import model.AppData;
import util.DataURL_Awards;
import util.DataURL_Latest;

/**
 * Created by Piyush on 8/20/2016.
 */
public class LatestActivity extends Fragment {

    ProgressDialog progressDataLoad=null;
    String latestvideoData;
    DataURL_Latest dataURL=new DataURL_Latest();
    AppData appData;
    LatestVideoAdapter videolistAdapter;
    public static ListView listView;
    public static ArrayList<AppData> appDatas=new ArrayList<AppData>();
    private InterstitialAd interstitial;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.latest_video, container, false);

        interstitial = new InterstitialAd(getContext());
        interstitial.setAdUnitId("ca-app-pub-4193835134456390/3127891468");
        AdView adView = (AdView)rootView.findViewById(R.id.adView);
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

        listView=(ListView)rootView.findViewById(R.id.listView);


        LoadingData LoadData = new LoadingData();
        LoadData.execute();
        progressDataLoad = ProgressDialog.show(getContext(), "","Loading...", true);

        return rootView;
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

            latestvideoData= dataURL.getLatest();

            try {

                JSONObject jsonArray = new JSONObject(latestvideoData);
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
            videolistAdapter=new LatestVideoAdapter(getContext(),appDatas);
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
