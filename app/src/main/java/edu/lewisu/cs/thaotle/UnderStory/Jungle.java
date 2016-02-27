package edu.lewisu.cs.thaotle.UnderStory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Jungle extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    JSONObject jsonObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jungle);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        new DownloadJson().execute();

    }
    private class DownloadJson extends AsyncTask<String, String, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... params) {
            jsonObject = JSONFunctions.getJSONfromURL("http://cs.lewisu.edu/trees/index.php");
            return jsonObject;
        }
        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                jsonArray = jsonObject.getJSONArray("alltree");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    String treeNames = jsonObject.getString("Tree_Name");
                    String Lats = jsonObject.getString("Latitudes");
                    String Lons = jsonObject.getString("Longitudes");
                    drawMarker(new LatLng(Double.parseDouble(Lats), Double.parseDouble(Lons)), treeNames);
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void drawMarker(LatLng point,String text){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point).title(text).icon(BitmapDescriptorFactory.fromResource(R.drawable.tree));
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 16));
        }

    }


