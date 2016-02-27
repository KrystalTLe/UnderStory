package edu.lewisu.cs.thaotle.UnderStory;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class showMeTree extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double treeLat;
    Double treeLong;
    String tree;
    Double mylat;
    Double mylon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_me_tree);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        treeLat = Double.parseDouble(intent.getStringExtra("lat"));
        treeLong = Double.parseDouble(intent.getStringExtra("lon"));
        tree = intent.getStringExtra("treeName");
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location myLocation = null;
        for (int i = 0; i < providers.size(); i++) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            myLocation = locationManager.getLastKnownLocation(providers.get(i));
            if(myLocation!=null){
                mylat=myLocation.getLatitude();
                mylon=myLocation.getLongitude();
            }

        }
        if(mMap!=null){
            LatLng latLng = new LatLng(mylat,mylon);
            Marker currentLocation = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(mylat, mylon))
                    .title("You")
                    .icon((BitmapDescriptorFactory.fromResource(R.drawable.person))));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }

        //tree location
        LatLng treeLocation = new LatLng(treeLat, treeLong);
        Marker singleTree=mMap.addMarker(new MarkerOptions()
                .position(treeLocation)
                .title(tree)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)));
        singleTree.showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(treeLocation,15));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(14));


    }
}
