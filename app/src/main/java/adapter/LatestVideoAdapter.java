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

import com.initapp.vidmateguide.ComedyActivity;
import com.initapp.vidmateguide.LatestActivity;
import com.initapp.vidmateguide.R;
import com.initapp.vidmateguide.VideoShowActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.AppData;

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

        LatestActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = animalPreferences.edit();
                editor.putString("Video", appItems.get(position).VideoId);
                editor.commit();
                Intent intent=new Intent(getContext(),VideoShowActivity.class);
                activity.startActivity(intent);
            }
        });


        return rowView;
    }
}
