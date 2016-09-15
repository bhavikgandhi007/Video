package com.initapp.vidmateguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import adapter.VideoAdapter;

/**
 * Created by Piyush on 8/16/2016.
 */
public class ChanelActivity extends Fragment {

    private GridView videoGrid;
    VideoAdapter adapter;
    private InterstitialAd interstitial;

    String[] videoTitle = {"Comedy","Cartoon","Bollywood","Wildlife","Song","Ooltah Chashmah","Awards","A.R. Rahman","History","Documentry","WWF","Sports"};
    int[] imageId = {R.drawable.comedy,R.drawable.cartoon,R.drawable.action,R.drawable.wildlife,R.drawable.song,R.drawable.taarakmehta,R.drawable.awards,R.drawable.arheman,R.drawable.history,R.drawable.documentry,R.drawable.wwf,R.drawable.sports};
    String[] videoCount = {"50 Views","52 Views","49 Views","50 Views","54 Views","51 Views","49 Views","53 Views","50 Views","52 Views","55 Views","54 Views"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);


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

        adapter = new VideoAdapter(getActivity(), videoTitle, imageId, videoCount);
        videoGrid=(GridView)rootView.findViewById(R.id.gridView);
        videoGrid.setAdapter(adapter);

        videoGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                {
                    Intent romance=new Intent(getContext(),ComedyActivity.class);
                    startActivity(romance);

                }else if(position==1){
                    Intent romance=new Intent(getContext(),CartoonActivity.class);
                    startActivity(romance);

                }else if(position==2){
                    Intent romance=new Intent(getContext(),BollywoodActivity.class);
                    startActivity(romance);

                }else if(position==3){
                    Intent romance=new Intent(getContext(),WildfileActivity.class);
                    startActivity(romance);

                }else if(position==4){
                    Intent romance=new Intent(getContext(),SongsActivity.class);
                    startActivity(romance);

                }else if(position==5){
                    Intent romance=new Intent(getContext(),TaarakMehtaActivity.class);
                    startActivity(romance);

                }else if(position==6){
                    Intent romance=new Intent(getContext(),AwardsActivity.class);
                    startActivity(romance);

                }else if(position==7){
                    Intent romance=new Intent(getContext(),StarPlusActivity.class);
                    startActivity(romance);

                }else if(position==8){
                    Intent romance=new Intent(getContext(),HistoryActivity.class);
                    startActivity(romance);

                }else if(position==9){
                    Intent romance=new Intent(getContext(),DocumentryActivity.class);
                    startActivity(romance);

                }else if(position==10){
                    Intent romance=new Intent(getContext(),WWFActivity.class);
                    startActivity(romance);

                }else if(position==11){
                    Intent romance=new Intent(getContext(),SportsActivity.class);
                    startActivity(romance);

                }

            }
        });


        return rootView;
    }
    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

}
