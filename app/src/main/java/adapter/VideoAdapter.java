package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.initapp.vidmateguide.R;


/**
 * Created by Piyush on 8/13/2016.
 */
public class VideoAdapter extends BaseAdapter {
    private Context mContext;
    private final int[] Imageid;
    private String[] videoTitle;
    private String[] videoCount;
    ImageView imageView;

    public VideoAdapter(Context c, String[] videoTitle, int[] Imageid,String[] videoCount ) {
        mContext = c;
        this.Imageid = Imageid;
        this.videoTitle = videoTitle;
        this.videoCount = videoCount;
    }


    public int getCount() {
        return Imageid.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.card_view_list, null);



        }else{
            grid = convertView;
        }
        imageView = (ImageView)grid.findViewById(R.id.Chanel_photo);
        TextView chanelName = (TextView)grid.findViewById(R.id.Chanel_name);
        TextView chanelCount = (TextView)grid.findViewById(R.id.Chanel_view);

        imageView.setImageResource(Imageid[position]);
        chanelName.setText(videoTitle[position]);
        chanelCount.setText(videoCount[position]);



        return grid;
    }
}
