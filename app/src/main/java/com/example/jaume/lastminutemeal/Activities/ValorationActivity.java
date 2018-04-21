package com.example.jaume.lastminutemeal.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.Adapters.ValoracionesAdapter;
import com.example.jaume.lastminutemeal.R;

public class ValorationActivity extends AppCompatActivity {

    private static String BAR = "bar";
    private static String COMMENT = "comment";

    ListView listView;
    private ValoracionesAdapter va;
    String[][] datos = {
            {"Bar Roma", "Bravas perfectas, recomendable 100%, un bar autentico perfecto para tapear entre horass, sin duda excelente", "4"},
            {"Bar2", "Encontre una cucaracha", "3"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.valoraciones);

        listView = (ListView) findViewById(R.id.listValoraciones);
        va = new ValoracionesAdapter(this, datos);
        listView.setAdapter(va);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                Intent intent = new Intent(getApplicationContext(),DetailValoration.class);
                intent.putExtra(BAR,datos[pos][0]);
                intent.putExtra(COMMENT,datos[pos][1]);
                startActivity(intent);
            }
        });

    }
}
