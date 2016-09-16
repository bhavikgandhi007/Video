package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.initapp.vidmateguide.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.initapp.vidmateguide.model.AppData;

/**
 * Created by Piyush on 8/31/2016.
 */
public class LatestVideoAdapter extends ArrayAdapter<AppData> {

    private ArrayList<AppData> appItems = new ArrayList<AppData>();
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences animalPreferences;

    public LatestVideoAdapter(Context activity, ArrayList<AppData> list) {
        super(activity, 0, list);
        this.appItems = list;
        animalPreferences = activity.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        final Activity activity = (Activity) getContext();
        LayoutInflater inflater = activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_video_list, null);

        TextView videoTitle = (TextView) rowView.findViewById(R.id.video_name);
        ImageView videoImage = (ImageView) rowView.findViewById(R.id.video_photo);

        videoTitle.setText(appItems.get(position).Title);
        Picasso.with(getContext()).load(appItems.get(position).ImageUrl).placeholder(R.drawable.pi).error(R.drawable.pi).into(videoImage);




        return rowView;
    }
}
