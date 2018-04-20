package com.example.jaume.lastminutemeal.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.Adapters.ValoracionesAdapter;
import com.example.jaume.lastminutemeal.R;

public class ValorationActivity extends AppCompatActivity {

    ListView listView;
    private ValoracionesAdapter va;
    String[][] datos = {
            {"Bar Roma", "5/5"},
            {"Bar2", "3/5"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.reserva);

        listView = (ListView) findViewById(R.id.listValoraciones);
        va = new ValoracionesAdapter(this, datos);
        listView.setAdapter(va);

    }
}
