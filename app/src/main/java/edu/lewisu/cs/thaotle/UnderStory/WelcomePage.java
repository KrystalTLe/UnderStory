package edu.lewisu.cs.thaotle.UnderStory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class WelcomePage extends Activity {

    Button startButton;
    Button exitButton;
    Button showJungle;
    TextView welcomeName;
    boolean status=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        startButton=(Button)findViewById(R.id.startButton);
        welcomeName=(TextView)findViewById(R.id.welcomeName);
        Typeface font = Typeface.createFromAsset(getAssets(), "scriptina.ttf");
        welcomeName.setTypeface(font);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = isConnectedToInternet();
                if(status) {
                    startActivity(new Intent(WelcomePage.this, TreeList.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please connect your device to the Internet",Toast.LENGTH_SHORT).show();
                }

            }
        });

        showJungle=(Button)findViewById(R.id.showTreesOnCam);
        showJungle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                status=isConnectedToInternet();
                if(status) {
                    startActivity(new Intent(WelcomePage.this, Jungle.class));
                }else{
                    Toast.makeText(getApplicationContext(),"Please connect your device to the Internet",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean isConnectedToInternet(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();
            if (networkInfos != null)
                for (int i = 0; i < networkInfos.length; i++)
                    if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
