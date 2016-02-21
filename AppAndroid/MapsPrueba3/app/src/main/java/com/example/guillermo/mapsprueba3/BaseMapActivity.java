package com.example.guillermo.mapsprueba3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Guillermo on 21/02/2016.
 */
public abstract class BaseMapActivity extends AppCompatActivity {

    protected LatLng mCenterLocation = new LatLng(39.7392, -104.9903);

    protected GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getMapLayoutId());
        initMapIfNecessary();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMapIfNecessary();
    }

    protected void initMapIfNecessary() {
        if (mGoogleMap != null) {
            return;
        }

        mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        initMapSettings();
        initCamera();
    }

    protected void initCamera() {
        CameraPosition position = CameraPosition.builder()
                .target(mCenterLocation)
                .zoom(getInitialMapZoomLevel())
                .build();

        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
    }

    protected int getMapLayoutId() {
        return R.layout.activity_map;
    }

    protected float getInitialMapZoomLevel() {
        return 12.0f;
    }

    @Override
    protected void initMapSettings() {
        ArrayList<LatLng> locations = generateLocations();
        mProvider = new HeatmapTileProvider.Builder().data( locations ).build();
        mProvider.setRadius( HeatmapTileProvider.DEFAULT_RADIUS );
        mGoogleMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }

    private ArrayList<LatLng> generateLocations() {
        ArrayList<LatLng> locations = new ArrayList<LatLng>();
        double lat;
        double lng;
        Random generator = new Random();
        for( int i = 0; i < 1000; i++ ) {
            lat = generator.nextDouble() / 3;
            lng = generator.nextDouble() / 3;
            if( generator.nextBoolean() ) {
                lat = -lat;
            }
            if( generator.nextBoolean() ) {
                lng = -lng;
            }
            locations.add(new LatLng(mCenterLocation.latitude + lat, mCenterLocation.longitude + lng));
        }

        return locations;
    }
}