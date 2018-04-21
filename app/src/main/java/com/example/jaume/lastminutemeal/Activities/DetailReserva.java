package com.example.jaume.lastminutemeal.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Reserva;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DetailReserva extends AppCompatActivity {

    private static String BAR = "bar";
    private static String RESERVA = "reserva";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_reserva);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Resumen reserva");
        TextView tv = (TextView) findViewById(R.id.title);
        TextView tv2 = (TextView) findViewById(R.id.resumen_reserva);
        final Reserva reserva = (Reserva) getIntent().getParcelableExtra(RESERVA);
        tv.setText(reserva.getLugar());
        StringBuilder line = new StringBuilder();
        line.append("Hora: "+reserva.getHora()+"\n");
        line.append("Total personas: "+reserva.getPersonas()+"\n\n\n");
        for(int x=1; x<=reserva.getMenu().size(); x++) {
            line.append("Menú "+x+"\n");
            line.append("Primer plato: " + reserva.getMenu().get(x-1).getFirstDish() + "\n");
            line.append("Segundo plato: " + reserva.getMenu().get(x-1).getSecondDish() + "\n");
            line.append("Postre: " + reserva.getMenu().get(x-1).getDesert() + "\n");
            line.append("Bebida: " + reserva.getMenu().get(x-1).getDrink() + "\n");
            line.append("Café: " + reserva.getMenu().get(x-1).getCoffee() + "\n");
            line.append("\n");
            line.append("----------------------");
            line.append("\n");
        }
        tv2.setText(line);


        Button button = (Button) findViewById(R.id.valorar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),ValorarReserva.class);
                intent.putExtra(BAR,reserva.getLugar());
                startActivity(intent);
            }
        });


    }
}
