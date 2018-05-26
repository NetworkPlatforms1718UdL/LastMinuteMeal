package com.example.jaume.lastminutemeal.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Valoration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ValorarReserva extends AppCompatActivity {

    private static String BAR = "bar";
    RatingBar rb;
    EditText et;
    private DatabaseReference mDatabase;
    private Valoration valoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valorar_reserva);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.resumen_pedido);

        TextView tv = findViewById(R.id.title_val);
        rb = findViewById(R.id.ratingBar2);
        et = findViewById(R.id.editTextVal);

        tv.setText(getIntent().getStringExtra(BAR));

        Button button = findViewById(R.id.addVal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                String key = mDatabase.child("valoration").push().getKey();
                valoration = new Valoration(key, getIntent().getStringExtra(BAR), String.valueOf(rb.getRating()), String.valueOf(et.getText()), uid);
                Map<String, Object> postValues = valoration.uploadToDatabase();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/valorations/" + key, postValues);
                mDatabase.updateChildren(childUpdates);
                Toast.makeText(getApplicationContext(), R.string.rateDone, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
