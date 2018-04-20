package com.example.jaume.lastminutemeal.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.jaume.lastminutemeal.Fragments.FragmentReservas;
import com.example.jaume.lastminutemeal.Fragments.FragmentResumen;
import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Menu;

import java.util.ArrayList;

public class DetailReservasActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        FragmentReservas reservas = (FragmentReservas) getSupportFragmentManager().
                findFragmentById(R.id.FrgReservas);
    }
}
