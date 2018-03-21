package com.example.jaume.lastminutemeal;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DetailMenuActivity extends FragmentActivity {

    public static final String EXTRA_TEXT = "EXTRA_TEXT";
    public static final String MENU_ADAPTER = "MENU_ADAPTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        FragmentDetailMenu detalle = (FragmentDetailMenu) getSupportFragmentManager().
                findFragmentById(R.id.FrgDetalle);
        detalle.mostrarDetalle((Menu) getIntent().getParcelableExtra(EXTRA_TEXT));
    }
}
