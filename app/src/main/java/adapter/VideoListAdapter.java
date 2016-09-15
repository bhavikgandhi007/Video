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

import com.initapp.vidmateguide.AwardsActivity;
import com.initapp.vidmateguide.BollywoodActivity;
import com.initapp.vidmateguide.CartoonActivity;
import com.initapp.vidmateguide.ComedyActivity;
import com.initapp.vidmateguide.DocumentryActivity;
import com.initapp.vidmateguide.HistoryActivity;
import com.initapp.vidmateguide.R;
import com.initapp.vidmateguide.SongsActivity;
import com.initapp.vidmateguide.SportsActivity;
import com.initapp.vidmateguide.StarPlusActivity;
import com.initapp.vidmateguide.TaarakMehtaActivity;
import com.initapp.vidmateguide.VideoShowActivity;
import com.initapp.vidmateguide.WWFActivity;
import com.initapp.vidmateguide.WildfileActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import model.AppData;

/**
 * Created by Piyush on 8/15/2016.
 */
public class VideoListAdapter extends ArrayAdapter<AppData> {

    private ArrayList<AppData> appItems = new ArrayList<AppData>();
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences animalPreferences;
    Context context;
    int data;

    public VideoListAdapter(Activity activity, ArrayList<AppData> list) {
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

        data = animalPreferences.getInt("Class", 0);
        if (data==0){
            ComedyActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 1) {
            CartoonActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 2) {
            BollywoodActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 3) {
            WildfileActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 4) {
            SongsActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 5) {
            TaarakMehtaActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 6) {
            AwardsActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 7) {
            StarPlusActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 8) {
            HistoryActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 9) {
            DocumentryActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 10) {
            WWFActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        } else if (data == 11) {
            SportsActivity.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SharedPreferences.Editor editor = animalPreferences.edit();
                    editor.putString("Video", appItems.get(position).VideoId);
                    editor.commit();
                    Intent intent=new Intent(getContext(),VideoShowActivity.class);
                    activity.startActivity(intent);
                }
            });
        }



        return rowView;
    }

}


