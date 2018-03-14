package com.example.jaume.lastminutemeal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        GoogleMap.OnInfoWindowClickListener {

    private static final LatLng ROMA = new LatLng(41.614320, 0.619235);
    private static final LatLng ABAT = new LatLng(41.612101, 0.620149);
    private static final LatLng RAUL = new LatLng(41.612409, 0.619099);

    static Marker mROMA;
    static Marker mABAT;
    static Marker mRAUL;

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

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}

class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    // These are both viewgroups containing an ImageView with id "badge" and two TextViews
    // with id "title" and "snippet".
    private final View mWindow;

    private final View mContents;

    @SuppressLint("InflateParams")
    CustomInfoWindowAdapter(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        mWindow = inflater.inflate(R.layout.custom_info_window, null);
        mContents = inflater.inflate(R.layout.custom_info_contents, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        render(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        render(marker, mContents);
        return mContents;
    }

    private void render(Marker marker, View view) {
        int badge;
        // Use the equals() method on a Marker to check for equals.  Do not use ==.
        if (marker.equals(MapUtils.mROMA)) {
            badge = R.drawable.badge_qld;
        } else if (marker.equals(MapUtils.mABAT)) {
            badge = R.drawable.badge_sa;
        } else if (marker.equals(MapUtils.mRAUL)) {
            badge = R.drawable.badge_nsw;
        } else {
            // Passing 0 to setImageResource will clear the image view.
            badge = 0;
        }
        ((ImageView) view.findViewById(R.id.badge)).setImageResource(badge);

        String title = marker.getTitle();
        TextView titleUi = ((TextView) view.findViewById(R.id.title));
        if (title != null) {
            // Spannable string allows us to edit the formatting of the text.
            SpannableString titleText = new SpannableString(title);
            titleText.setSpan(new ForegroundColorSpan(Color.RED),
                    0, titleText.length(), 0);
            titleUi.setText(titleText);
        } else {
            titleUi.setText("");
        }

        String snippet = marker.getSnippet();
        TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
        if (snippet != null && snippet.length() > 12) {
            SpannableString snippetText = new SpannableString(snippet);
            snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA),
                    0, 10, 0);
            snippetText.setSpan(new ForegroundColorSpan(Color.BLUE),
                    12, snippet.length(), 0);
            snippetUi.setText(snippetText);
        } else {
            snippetUi.setText("");
        }
    }
}
