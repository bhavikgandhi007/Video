package com.initapp.vidmateguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.initapp.vidmateguide.R;
import com.initapp.vidmateguide.model.RequestParameter;

/**
 * Created by Ajay on 21/09/2016.
 */
public class CategoryFragment extends Fragment {
    RelativeLayout t_series;// bollywood video channel
    RelativeLayout sab_tv; // tv channel
    RelativeLayout tarak_mehta; //tv episode
    RelativeLayout sudh_desi; //other channel

    TextView txt_comedy;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_category, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        t_series = (RelativeLayout) view.findViewById(R.id.t_series);
        sab_tv = (RelativeLayout) view.findViewById(R.id.sab_tv);
        tarak_mehta = (RelativeLayout) view.findViewById(R.id.tarak_mehta);
        sudh_desi = (RelativeLayout) view.findViewById(R.id.sudh_desi);
        txt_comedy= (TextView) view.findViewById(R.id.txt_comedy);
        t_series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParameter requestParameter = new RequestParameter();
                requestParameter.setPart("snippet");
                requestParameter.setMaxResults(Utills.MAXRESULT);
                requestParameter.setOrder(Utills.ORDER_DATE);
                requestParameter.setChannelId("UCq-Fj5jknLsUf-MWSy4_brA");
                Intent intent = new Intent(getActivity(), VideoListActivity.class);
                intent.putExtra("reqType", "2");
                intent.putExtra("parameter", requestParameter);
                intent.putExtra("title", "T-Series");
                startActivity(intent);
            }
        });
        sab_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParameter requestParameter = new RequestParameter();
                requestParameter.setPart("snippet");
                requestParameter.setMaxResults(Utills.MAXRESULT);
                requestParameter.setOrder(Utills.ORDER_DATE);
                requestParameter.setChannelId("UC6-F5tO8uklgE9Zy8IvbdFw");
                Intent intent = new Intent(getActivity(), VideoListActivity.class);
                intent.putExtra("reqType", "2");
                intent.putExtra("parameter", requestParameter);
                intent.putExtra("title", "Sab TV");
                startActivity(intent);
            }
        });
        tarak_mehta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParameter requestParameter = new RequestParameter();
                requestParameter.setPart("snippet");
                requestParameter.setMaxResults(Utills.MAXRESULT);
                requestParameter.setOrder(Utills.ORDER_DATE);
                requestParameter.setQ("tarak mehta");
                requestParameter.setChannelId("UC6-F5tO8uklgE9Zy8IvbdFw");
                Intent intent = new Intent(getActivity(), VideoListActivity.class);
                intent.putExtra("reqType", "4");
                intent.putExtra("parameter", requestParameter);
                intent.putExtra("title", "Tarak Mehta");
                startActivity(intent);
            }
        });
        sudh_desi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParameter requestParameter = new RequestParameter();
                requestParameter.setPart("snippet");
                requestParameter.setMaxResults(Utills.MAXRESULT);
                requestParameter.setOrder(Utills.ORDER_DATE);
                requestParameter.setChannelId("UCF-aIi0zXNwZZucGtWk4pug");
                Intent intent = new Intent(getActivity(), VideoListActivity.class);
                intent.putExtra("reqType", "2");
                intent.putExtra("parameter", requestParameter);
                intent.putExtra("title", "Sudh Desi Ending");
                startActivity(intent);
            }
        });
        txt_comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParameter requestParameter = new RequestParameter();
                requestParameter.setPart("snippet");
                requestParameter.setMaxResults(Utills.MAXRESULT);
                requestParameter.setOrder(Utills.ORDER_DATE);
                requestParameter.setVideoCategoryId("23");
                requestParameter.setType("video");
                Intent intent = new Intent(getActivity(), VideoListActivity.class);
                intent.putExtra("reqType", "3");
                intent.putExtra("parameter", requestParameter);
                intent.putExtra("title", "Comedy");
                startActivity(intent);
            }
        });
    }
}
