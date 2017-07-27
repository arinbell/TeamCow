package com.teamcow.wheresmystuff.controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LostItem;
import com.teamcow.wheresmystuff.model.LostItemData;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private LostItemData lid = LostItemData.getInstance();
    private ArrayList<LostItem> lostList = lid.getItemList();
    private ArrayList<LostItem> showList;
    private LostItem lostItem;
    private ArrayList<Marker> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        showList = new ArrayList<>();

        if (getIntent() != null && getIntent().hasExtra("item_to_view")) {
            lostItem = (LostItem) getIntent().getParcelableExtra("item_to_view");
            showList.add(lostItem);
        } else {
            showList = lostList;
        }

        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        googleMap.clear();

        LatLng loc;
        markers = new ArrayList<>();
        Marker mark;
        //LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LostItem i : showList) {
            loc = new LatLng(i.getX_coord(), i.getY_coord());
            googleMap.addMarker(new MarkerOptions().position(loc)).setTitle(i.getName());
            //mark.setTag(i);
            //mark.setTitle(i.getName());
            //markers.add(mark);
            //builder.include(loc);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }



//        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                LostItem item = (LostItem) marker.getTag();
////                LostItem item = markers.get(marker);
//                Intent intent = new Intent(MapsActivity.this, ItemDetailActivity.class);
//                intent.putExtra("item_to_view", item);
//                if (item != null) {
//                    startActivity(intent);
//                }
//                return true;
//            }
//        });

//        try {
//            googleMap.setMyLocationEnabled(true);
//            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
//        } catch (SecurityException e) {
//            LatLngBounds bounds = builder.build();
//
//            int padding = 20;
//            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
//
//            googleMap.animateCamera(cu);
//        }


//        if (!lostList.isEmpty()) {
//            for (LostItem lostItem : lostList) {
//                LatLng current = new LatLng(lostItem.getX_Coord(), lostItem.getY_Coord());
//                mMap.addMarker(new MarkerOptions().position(current).title(lostItem.getName()).
//                        snippet(lostItem.getDescription()));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
//            }
//        }
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
