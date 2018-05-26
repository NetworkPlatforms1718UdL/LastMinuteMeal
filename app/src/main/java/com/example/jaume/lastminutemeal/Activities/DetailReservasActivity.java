package com.example.jaume.lastminutemeal.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

import com.example.jaume.lastminutemeal.Fragments.FragmentReservas;
import com.example.jaume.lastminutemeal.R;

public class DetailReservasActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        FragmentReservas reservas = (FragmentReservas) getSupportFragmentManager().
                findFragmentById(R.id.FrgReservas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.bookings);
    }
}
