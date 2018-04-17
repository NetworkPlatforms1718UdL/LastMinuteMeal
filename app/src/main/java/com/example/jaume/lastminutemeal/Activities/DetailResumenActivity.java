package com.example.jaume.lastminutemeal.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.jaume.lastminutemeal.Fragments.FragmentResumen;
import com.example.jaume.lastminutemeal.Utils.Menu;
import com.example.jaume.lastminutemeal.R;

import java.util.ArrayList;

public class DetailResumenActivity extends FragmentActivity {

    public static final String EXTRA_TEXT = "EXTRA_TEXT";
    public static final String LUGAR = "Lugar";
    public static final String HORA = "Hora";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_resumen);
        FragmentResumen resumen = (FragmentResumen) getSupportFragmentManager().
                findFragmentById(R.id.FrgResumen);
        resumen.mostrarResumen( (ArrayList<Menu>) getIntent().getSerializableExtra(EXTRA_TEXT),
                getIntent().getStringExtra(HORA),getIntent().getStringExtra(LUGAR));
    }
}
