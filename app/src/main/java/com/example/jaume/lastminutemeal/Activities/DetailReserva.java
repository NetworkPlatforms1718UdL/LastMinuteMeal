package com.example.jaume.lastminutemeal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Reserva;

public class DetailReserva extends AppCompatActivity {

    private static String BAR = "bar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_reserva);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Order summary");
        TextView tv = findViewById(R.id.title);
        TextView tv2 = findViewById(R.id.resumen_reserva);
        String RESERVA = "reserva";
        final Reserva reserva = getIntent().getParcelableExtra(RESERVA);
        tv.setText(reserva.getLugar());
        StringBuilder line = new StringBuilder();
        line.append("Time: ").append(reserva.getHora()).append("\n");
        line.append("Number of people: ").append(reserva.getPersonas()).append("\n\n\n");
        for (int x = 1; x <= reserva.getMenu().size(); x++) {
            line.append("Menu ").append(x).append("\n");
            line.append("First dish: ").append(reserva.getMenu().get(x - 1).getFirstDish()).append("\n");
            line.append("Second dish: ").append(reserva.getMenu().get(x - 1).getSecondDish()).append("\n");
            line.append("Desert: ").append(reserva.getMenu().get(x - 1).getDesert()).append("\n");
            line.append("Drink: ").append(reserva.getMenu().get(x - 1).getDrink()).append("\n");
            line.append("Coffee: ").append(reserva.getMenu().get(x - 1).getCoffee()).append("\n");
            line.append("\n");
            line.append("----------------------");
            line.append("\n");
        }
        tv2.setText(line);


        Button button = findViewById(R.id.valorar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ValorarReserva.class);
                intent.putExtra(BAR, reserva.getLugar());
                startActivity(intent);
            }
        });

        Button bidi = findViewById(R.id.bidi);
        bidi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                intent.putExtra("ID", reserva.getId());
                startActivity(intent);
            }
        });


    }
}
