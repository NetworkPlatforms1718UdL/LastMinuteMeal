package com.example.jaume.lastminutemeal.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

import com.example.jaume.lastminutemeal.Fragments.FragmentDetailMenu;
import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Menu;

import java.util.ArrayList;

public class DetailMenuActivity extends FragmentActivity {

    public static final String EXTRA_TEXT = "EXTRA_TEXT";
    public static final String POSITION = "Position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.chooseMenu);
        FragmentDetailMenu detalle = (FragmentDetailMenu) getSupportFragmentManager().
                findFragmentById(R.id.FrgDetalle);
        detalle.mostrarDetalle((ArrayList<Menu>) getIntent().getSerializableExtra(EXTRA_TEXT),
                getIntent().getIntExtra(POSITION, 0));
    }
}
