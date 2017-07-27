package com.teamcow.wheresmystuff.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.AsyncTask;

import android.support.v4.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.ItemType;
import com.teamcow.wheresmystuff.model.LostItem;
import com.teamcow.wheresmystuff.model.PosterType;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ItemDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LostItem lostItem;

    private FloatingActionButton fab;
    private TextView desTextView;
    private Button mapViewButton;
    private TextView categoryText;
    private TextView posterText;
    private ImageView categoryImage;
    private ImageView posterImage;
    private MapView mapView;
    private GoogleMap mMap;
    private Marker mMarker;
    private ImageView itemImageview;
    private CollapsingToolbarLayout toolbarLayout;


    private final String INTENT_ITEM = "item_to_view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);


        itemImageview = (ImageView) findViewById(R.id.item_detail_imageview);
        desTextView = (TextView) findViewById(R.id.description_textview);
        categoryText = (TextView) findViewById(R.id.categoryTextView);
        categoryImage = (ImageView) findViewById(R.id.category_image_view);
        posterText = (TextView) findViewById(R.id.posterTextView);
        posterImage = (ImageView) findViewById(R.id.poster_image_view);
        mapView = (MapView) findViewById(R.id.item_detail_mapview);
        mapView.onCreate(savedInstanceState);


        fab = (FloatingActionButton) findViewById(R.id.fab_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetailActivity.this, ItemRegActivity.class);
                intent.putExtra(INTENT_ITEM, lostItem);
                startActivity(intent);
                finish();
            }
        });

        //lostItem = new LostItem("", "", "", "", 0, 0, ItemType.ELECTRONIC, PosterType.FINDER, );

        if (getIntent().hasExtra(INTENT_ITEM)) {
            lostItem = (LostItem) getIntent().getParcelableExtra(INTENT_ITEM);
            if (lostItem != null) {
                fillDisplay();
            }
        }

        //Checks if the viewer is the poster of the post, shows or hides edit button
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (lostItem.getUser().equals(user.getUid())) {
            findViewById(R.id.fab_edit).setEnabled(true);
            findViewById(R.id.fab_edit).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.fab_edit).setEnabled(false);
            findViewById(R.id.fab_edit).setVisibility(View.INVISIBLE);
        }


    }

    private void fillDisplay() {
        //Log.d("WheresMyStuff", "FILLDISPLAY REQACHEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        //itemImageview.setImageDrawable(ImageHandler.loadImageFromURL(this, lostItem.getUri()));
        //toolbarLayout.setBackground(ImageHandler.loadImageFromURL(this, lostItem.getUri()));

        //new DownloadImageTask(itemImageview).execute();

        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(lostItem.getUri());

        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(itemImageview);

        toolbarLayout.setTitle(lostItem.getName());
        desTextView.setText(lostItem.getDescription());
        categoryText.setText(lostItem.getType().toString());
        posterText.setText(lostItem.getPoster().toString());
        setIcons();
        mapView.getMapAsync(this);
        //Log.d("WheresMyStuff", "FILLDISPLAY LEAVINGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
    }

    private void setIcons() {
        if (lostItem.getType().equals(ItemType.CLOTHES)) {
            categoryImage.setImageDrawable(getResources().getDrawable(R.drawable.black_clothingicon, null));
        } else if (lostItem.getType().equals(ItemType.ELECTRONIC)) {
            categoryImage.setImageDrawable(getResources().getDrawable(R.drawable.black_electronicsicon, null));
        } else if (lostItem.getType().equals(ItemType.JEWELRY)) {
            categoryImage.setImageDrawable(getResources().getDrawable(R.drawable.black_jewlryicon, null));
        } else if (lostItem.getType().equals(ItemType.TOILETRY)) {
            categoryImage.setImageDrawable(getResources().getDrawable(R.drawable.black_toiletryicon, null));
        }
        if (lostItem.getPoster().equals(PosterType.FINDER)) {
            posterImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_found_item_black_24dp, null));
        } else if (lostItem.getPoster().equals(PosterType.LOSER)) {
            posterImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_lost_item_black_24dp, null));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setAllGesturesEnabled(false);
        showItemOnMap();
    }

    private void showItemOnMap() {
        LatLng latLng = new LatLng(lostItem.getX_coord(), lostItem.getY_coord());
        if (mMarker == null) {
            mMarker = mMap.addMarker(new MarkerOptions().position(latLng));
        } else {
            mMarker.setPosition(latLng);
        }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                launchDetailMapView();
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                launchDetailMapView();
                return true;
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    private void launchDetailMapView() {
        Intent intent = new Intent(ItemDetailActivity.this, MapsActivity.class);
        intent.putExtra("item_to_view", lostItem);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        mapView.onStart();
        super.onStart();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        mapView.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        mapView.onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }

//    public static Bitmap loadBitmap(String url) {
//        Bitmap bitmap = null;
//        InputStream in = null;
//        BufferedOutputStream out = null;
//
//        try {
//            in = new BufferedInputStream(new URL(url).openStream(), IO_BUFFER_SIZE);
//
//            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
//            out = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);
//            copy(in, out);
//            out.flush();
//
//            final byte[] data = dataStream.toByteArray();
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            //options.inSampleSize = 1;
//
//            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,options);
//        } catch (IOException e) {
//            Log.e(TAG, "Could not load Bitmap from: " + url);
//        } finally {
//            closeStream(in);
//            closeStream(out);
//        }
//
//        return bitmap;
//    }
}
