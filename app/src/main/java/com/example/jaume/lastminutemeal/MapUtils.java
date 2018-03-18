package com.example.jaume.lastminutemeal;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapUtils implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener, AdapterView.OnItemSelectedListener {

    private static final LatLng ROMA = new LatLng(41.614320, 0.619235);
    private static final LatLng ABAT = new LatLng(41.612101, 0.620149);
    private static final LatLng RAUL = new LatLng(41.612409, 0.619099);

    static Marker mROMA;
    static Marker mABAT;
    static Marker mRAUL;

    Spinner mEstablish_type_Spinner;
    Spinner mMeal_type_Spinner;
    Spinner mRadius_meters_Spinner;


    private GoogleMap mMap;

    private Context context;

    MapUtils(Context context) {
        this.context = context;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addMarkersToMap();
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(context));

        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setContentDescription("Map with lots of markers.");

        mEstablish_type_Spinner.setOnItemSelectedListener(this);
        mMeal_type_Spinner.setOnItemSelectedListener(this);
        mRadius_meters_Spinner.setOnItemSelectedListener(this);


        CameraPosition LLEIDA = new CameraPosition.Builder().target(
                new LatLng(41.613492, 0.619827))
                .zoom(16.0f)
                .bearing(0)
                .tilt(25)
                .build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(LLEIDA));
    }

    private void addMarkersToMap() {
        // Uses a colored icon.
        mROMA = mMap.addMarker(new MarkerOptions()
                .position(ROMA)
                .title("Bar Roma")
                .snippet("Les millors tapes")
                .icon(BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_AZURE)));

        mABAT = mMap.addMarker(new MarkerOptions()
                .position(ABAT)
                .title("Restaurant Abat")
                .snippet("Els millors entrepans")
                .icon(BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_GREEN)));

        mRAUL = mMap.addMarker(new MarkerOptions()
                .position(RAUL)
                .title("Cafeteria Raul")
                .snippet("El millor cafe")
                .icon(BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_MAGENTA)));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(context, ElectionMenuActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("Marker ID:",marker.getId());
        //Intent intent = new Intent(context, ElectionMenuActivity.class);
        //intent.
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.establish_type_Spinner) {
            Log.d("Establecimiento","Establecimiento");
            Toast.makeText(context, "Establecimiento Cambiado", Toast.LENGTH_SHORT).show();
        } else if (parent.getId() == R.id.meal_type_Spinner) {
            Log.d("Comida","Comida");
            Toast.makeText(context, "Tipo de Comida Cambiado", Toast.LENGTH_SHORT).show();
        } else if (parent.getId() == R.id.radius_meters_Spinner) {
            Log.d("radios","Radios");
            Toast.makeText(context, "Radios", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
