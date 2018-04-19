package com.example.jaume.lastminutemeal.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.BuildConfig;
import com.example.jaume.lastminutemeal.Utils.MapUtils;
import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Menu;
import com.example.jaume.lastminutemeal.Utils.Reserva;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private com.example.jaume.lastminutemeal.Utils.MapUtils MapUtils = new MapUtils(this);
    private static final int[] TYPE_OF_ESTABLISHMENT = {
            R.string.establishment_all,
            R.string.establishment_restaurant, // Default
            R.string.establishment_bar,
            R.string.establishment_cafe,
    };
    private static final int[] TYPE_OF_MEAL = {
            R.string.meal_0, // Default
            R.string.meal_1,
            R.string.meal_2,
            R.string.meal_3,
            R.string.meal_4,
            R.string.meal_5,
    };
    private static final int[] RADIUS_METERS = {
            R.string.distance_0, //Default
            R.string.distance_1,
            R.string.distance_2,
            R.string.distance_3,
            R.string.distance_4,
    };

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        initActionBar();
        initSpinners();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!checkPermissions()) {
            requestPermissions();
        } else {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(MapUtils);
        }
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");

            showSnackbar(R.string.permission_rationale, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            startLocationPermissionRequest();
                        }
                    });

        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest();
        }
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(this, "PERMISSION NOT GRANTED", Toast.LENGTH_LONG).show();
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.

                showSnackbar(R.string.permission_rationale, R.string.settings,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, "null");
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
            }
        }
    }


    private void showSnackbar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener) {
        Snackbar.make(
                findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initSpinners() {
        MapUtils.mEstablish_type_Spinner = (Spinner) findViewById(R.id.establish_type_Spinner);
        MapUtils.mEstablish_type_Spinner.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                getResourceStrings(TYPE_OF_ESTABLISHMENT)));

        MapUtils.mMeal_type_Spinner = (Spinner) findViewById(R.id.meal_type_Spinner);
        MapUtils.mMeal_type_Spinner.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                getResourceStrings(TYPE_OF_MEAL)));

        MapUtils.mRadius_meters_Spinner = (Spinner) findViewById(R.id.radius_meters_Spinner);
        MapUtils.mRadius_meters_Spinner.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                getResourceStrings(RADIUS_METERS)));
    }

    private String[] getResourceStrings(int[] resourceIds) {
        String[] strings = new String[resourceIds.length];
        for (int i = 0; i < resourceIds.length; i++) {
            strings[i] = getString(resourceIds[i]);
        }
        return strings;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this, "Not Implemented Yet! Keep Calm!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_reserva) {
            Intent intent = new Intent(this, DetailReservasActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_valoraciones) {

            Query mQuery = FirebaseDatabase.getInstance()
                    .getReference("booking")
                    .orderByChild("userid")
                    .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

            mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    HashMap<String, Object> reservas =
                            (HashMap<String, Object>) dataSnapshot.getValue();
                    List<Reserva> reservasList = getReservasList(reservas);
                }

                private List<Reserva> getReservasList(HashMap<String, Object> reservas) {
                    List<Reserva> reservasList = new ArrayList<>();
                    for (int x = 0; x < reservas.size(); x++) {
                        //HashMap<String, Object> banana = (HashMap<String, Object>) reservas.get(x);
                        HashMap<String, Object> temporal = (HashMap<String, Object>) reservas.get("-LAV6W3S-RK7CMWm8opN");
                        String time = (String) temporal.get(3);
                        int restaurant_id = (int) temporal.get("restaurant_id");
                        String userid = (String) temporal.get("userid");
                        HashMap<String, Object> menus = (HashMap<String, Object>) temporal.get("menus");
                        ArrayList<Menu> menuList = new ArrayList<>();
                        for (int y = 0; y < menus.size(); y++) {
                            HashMap<String, Object> temporal2 = (HashMap<String, Object>) menus.get(y);
                            String first = (String) temporal2.get("first");
                            String second = (String) temporal2.get("second");
                            String desert = (String) temporal2.get("desert");
                            String drink = (String) temporal2.get("drink");
                            boolean coffee = (boolean) temporal2.get("coffee");
                            menuList.add(new Menu(y, first, second, desert, drink, coffee));
                        }
                        reservasList.add(new Reserva(String.valueOf(restaurant_id), time, userid, menuList));
                    }
                    return reservasList;
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //Toast.makeText(this, "There are errors...", Toast.LENGTH_SHORT).show();
                    Log.d("DatabaseErrors", databaseError.getDetails());
                }
            });
        } else if (id == R.id.nav_logout) {
            Toast.makeText(this, "Not Implemented Yet! Keep Calm!", Toast.LENGTH_SHORT).show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
