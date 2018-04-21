package com.example.jaume.lastminutemeal.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Reserva;
import com.example.jaume.lastminutemeal.Utils.Valoration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ValorarReserva extends AppCompatActivity {

    private static String BAR = "bar";
    private DatabaseReference mDatabase;
    private Valoration valoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valorar_reserva);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Resumen reserva");

        TextView tv = (TextView) findViewById(R.id.title_val);
        final RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
        final EditText et = (EditText) findViewById(R.id.editTextVal);

        tv.setText(getIntent().getStringExtra(BAR));

        Button button = (Button) findViewById(R.id.addVal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                String key = mDatabase.child("valoration").push().getKey();
                valoration = new Valoration(key,getIntent().getStringExtra(BAR),"3.0",et.getText().toString(),uid);
                Map<String,String> postValues = valoration.uploadToDatabase();
                Map<String,Object> childUpdates = new HashMap<>();
                childUpdates.put("/valorations/"+key, postValues);
                mDatabase.updateChildren(childUpdates);
                Toast.makeText(getApplicationContext(), "RESERVA REALIZADA", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
