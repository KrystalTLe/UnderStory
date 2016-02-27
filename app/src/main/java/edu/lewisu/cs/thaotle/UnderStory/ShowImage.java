package edu.lewisu.cs.thaotle.UnderStory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowImage extends Activity{
    String name;
    String sciName;
    String story;
    String pic;
    String lat;
    String lon;
    Button toMapButton;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        Intent intent=getIntent();

        name=intent.getStringExtra("name");
        sciName=intent.getStringExtra("sciname");
        story=intent.getStringExtra("story");
        pic = intent.getStringExtra("image");
        lat=intent.getStringExtra("latitudes");
        lon=intent.getStringExtra("longitudes");

        TextView nameOfTree = (TextView)findViewById(R.id.treeName1);

        TextView sciNameOfTree=(TextView)findViewById(R.id.sname);
        TextView storyOfTree=(TextView)findViewById(R.id.story);
        ImageView view=(ImageView)findViewById(R.id.imageView);
        view.setRotation(90);


        //this button show the location of tree on Map
       toMapButton=(Button)findViewById(R.id.toMapButton);
        toMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toMap= new Intent(ShowImage.this, showMeTree.class);
                toMap.putExtra("treeName",name);
                toMap.putExtra("lat",lat);
                toMap.putExtra("lon",lon);
                startActivity(toMap);
            }
        });


        nameOfTree.setText(name);
        sciNameOfTree.setText(sciName);
        storyOfTree.setText(story);
        Picasso.with(ShowImage.this)
                .load(pic)
                .into(view);

    }

}
