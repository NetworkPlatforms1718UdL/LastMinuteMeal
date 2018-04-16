package com.example.jaume.lastminutemeal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

public class DetailResumenActivity extends FragmentActivity {

    public static final String EXTRA_TEXT = "EXTRA_TEXT";
    public static final String MENU_ADAPTER = "MENU_ADAPTER";
    public static final String HORA = "Hora";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_resumen);
        FragmentResumen resumen = (FragmentResumen) getSupportFragmentManager().
                findFragmentById(R.id.FrgResumen);
        resumen.mostrarResumen( (ArrayList<Menu>) getIntent().getSerializableExtra(EXTRA_TEXT),
                getIntent().getIntExtra(HORA,0));
    }
}
