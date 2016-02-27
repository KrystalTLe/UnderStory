package edu.lewisu.cs.thaotle.UnderStory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KrystalTLe on 10/3/15.
 */
public class ListAdapter extends BaseAdapter{
    //Declare Variables
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<HashMap<String,String>>hashMaps;
    HashMap<String, String> result = new HashMap<String, String>();
    static Typeface tf;
    public ListAdapter(Context context, ArrayList<HashMap<String,String>> arrayList){
        this.context=context;
        hashMaps=arrayList;


    }@Override
     public int getCount() {
        return hashMaps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        //Declare Variables
        TextView treeName;
        final ImageView imageView;
        final String name;
        final String story;
        final String sciName;
        final int lat=0;
        final int lon=0;


        tf=Typeface.createFromAsset(context.getAssets(), "bodonitown.ttf"); //refer to tf
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.gallery, parent, false);

        // Get the position
        result=hashMaps.get(position);

        //Find ids
        treeName=(TextView)itemView.findViewById(R.id.tree_name);
        treeName.setTypeface(tf);
        //locate ImageView
        imageView=(ImageView)itemView.findViewById(R.id.images);
        //imageView.setRotation(90);

        //pass results from Treelist
        treeName.setText(result.get(TreeList.TREE_NAME));
        sciName=result.get(TreeList.SCI_NAME);
        story=result.get(TreeList.STORY);

        Picasso.with(context)
                .load(result.get(TreeList.IMAGE))
                .into(imageView);
        imageView.setRotation(90);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result=hashMaps.get(position);
                Intent intent=new Intent(context,ShowImage.class);
                intent.putExtra("image",result.get(TreeList.IMAGE));
                intent.putExtra("name",result.get(TreeList.TREE_NAME));
                intent.putExtra("sciname",sciName);
                intent.putExtra("story",story);
                intent.putExtra("latitudes",result.get(TreeList.LATITUDES));
                intent.putExtra("longitudes", result.get(TreeList.LONGITUDES));
                context.startActivity(intent);


            }
        });
        return itemView;
    }



}
