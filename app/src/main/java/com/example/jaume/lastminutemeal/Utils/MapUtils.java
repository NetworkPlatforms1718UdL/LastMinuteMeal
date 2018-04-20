package com.example.jaume.lastminutemeal.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.Activities.ElectionMenuActivity;
import com.example.jaume.lastminutemeal.Adapters.CustomInfoWindowAdapter;
import com.example.jaume.lastminutemeal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.content.ContentValues.TAG;

public class MapUtils implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener, AdapterView.OnItemSelectedListener {

    private static final LatLng ROMA = new LatLng(41.614320, 0.619235);
    private static final LatLng ABAT = new LatLng(41.612101, 0.620149);
    private static final LatLng RAUL = new LatLng(41.612409, 0.619099);

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    public final static String LOCAL_NAME = "LocalTitle";

    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;


    public static Marker mROMA;
    public static Marker mABAT;
    public static Marker mRAUL;
    private static Marker mPosition;

    public Spinner mEstablish_type_Spinner;
    public Spinner mMeal_type_Spinner;
    public Spinner mRadius_meters_Spinner;

    private GoogleMap mMap;

    private Context context;

    private Activity activity;

    private boolean firstTime = true;

    private double longitudeGPS, latitudeGPS;

    public MapUtils(Context context) {
        this.context = context;
        this.activity = (Activity) context;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapSetters();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        mSettingsClient = LocationServices.getSettingsClient(context);

        createLocationCallback();
        createLocationRequest();
        buildLocationSettingsRequest();
        startLocationUpdates();
        updateLocationUI();
    }

    private void startLocationUpdates() {
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");
                        //noinspection MissingPermission
                        if (activity.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION,0,0) ==
                                PackageManager.PERMISSION_GRANTED)
                            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                    mLocationCallback, Looper.myLooper());
                        updateLocationUI();
                    }
                })
                .addOnFailureListener(activity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);
                                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
        updateLocationUI();
    }

    private void updateLocationUI() {
        Log.d("mCurrentLocation",String.valueOf(mCurrentLocation));
        if (mCurrentLocation != null) {
            Log.d("LongitudeGPS", String.valueOf(mCurrentLocation.getLongitude()));
            longitudeGPS = mCurrentLocation.getLongitude();
            Log.d("LongitudeGPS", String.valueOf(mCurrentLocation.getLatitude()));
            latitudeGPS = mCurrentLocation.getLatitude();
            if (firstTime) {
                addMarkersToMap();
                firstTime = false;
            } else mPosition.setPosition(new LatLng(latitudeGPS,longitudeGPS));
        }
    }

    private void createLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                mCurrentLocation = locationResult.getLastLocation();
                updateLocationUI();
            }
        };
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    private void mapSetters() {
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(context));

        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setContentDescription("Map with lots of markers.");

        mEstablish_type_Spinner.setOnItemSelectedListener(this);
        mMeal_type_Spinner.setOnItemSelectedListener(this);
        mRadius_meters_Spinner.setOnItemSelectedListener(this);
    }

    private void addMarkersToMap() {
        // Uses a colored icon.
        Log.d("LatitudeActual",String.valueOf(latitudeGPS));
        Log.d("LongitudeActual", String.valueOf(longitudeGPS));
        CameraPosition ACTUAL = new CameraPosition.Builder().target(
                new LatLng(latitudeGPS, longitudeGPS))
                .zoom(16.0f)
                .bearing(0)
                .tilt(25)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(ACTUAL));
        mPosition = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitudeGPS,longitudeGPS)));
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
        if (!marker.equals(mPosition)) {
            Intent intent = new Intent(context, ElectionMenuActivity.class);
            intent.putExtra(LOCAL_NAME,marker.getTitle());
            context.startActivity(intent);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.establish_type_Spinner) {
            Log.d("Establecimiento","Establecimiento");
            //Toast.makeText(context, "Establecimiento Cambiado", Toast.LENGTH_SHORT).show();
        } else if (parent.getId() == R.id.meal_type_Spinner) {
            Log.d("Comida","Comida");
            //Toast.makeText(context, "Tipo de Comida Cambiado", Toast.LENGTH_SHORT).show();
        } else if (parent.getId() == R.id.radius_meters_Spinner) {
            Log.d("radios","Radios");
            //Toast.makeText(context, "Radios", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
