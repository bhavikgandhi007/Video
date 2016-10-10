package com.initapp.vidmateguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Ajay on 21/09/2016.
 */
public class SearchFragment extends Fragment {

    TextView button_search;
    EditText edit_search;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_search, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button_search = (TextView) view.findViewById(R.id.button_search);
        edit_search = (EditText) view.findViewById(R.id.edit_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_search.getText().toString() != null) {
                    if (!edit_search.getText().toString().trim().equalsIgnoreCase("")) {
                        Intent videointent=new Intent(getActivity(),VideoListActivity.class);
                        videointent.putExtra("keyword",edit_search.getText().toString().trim());
                        startActivity(videointent);
                        getActivity().overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                        getActivity().finish();
                    }
                }
            }
        });
    }
}
