package com.example.jaume.lastminutemeal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jaume.lastminutemeal.Adapters.ValoracionesAdapter;
import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Valoration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ValorationActivity extends AppCompatActivity implements ValueEventListener {

    private static String BAR = "bar";
    private static String COMMENT = "comment";

    ArrayList<Valoration> valList;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.valoraciones);
        getFirebaseData();
    }

    public void getFirebaseData() {
        Query mQuery = FirebaseDatabase.getInstance()
                .getReference("valorations")
                .orderByChild("userid")
                .equalTo(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        mQuery.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        HashMap<String, Object> valorations =
                (HashMap<String, Object>) dataSnapshot.getValue();
        assert valorations != null;
        valList = getValorationList(valorations);

        listView = findViewById(R.id.listValoraciones);
        ValoracionesAdapter va = new ValoracionesAdapter(this, valList);
        listView.setAdapter(va);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailValoration.class);
                intent.putExtra(BAR, valList.get(pos).getRestaurant_id());
                intent.putExtra(COMMENT, valList.get(pos).getComment());
                startActivity(intent);
            }
        });
    }

    private ArrayList<Valoration> getValorationList(HashMap<String, Object> valorations) {
        Object[] keys = valorations.keySet().toArray();
        ArrayList<Valoration> valorationsList = new ArrayList<>();
        for (int x = 0; x < valorations.size(); x++) {
            HashMap<String, Object> temporal = (HashMap<String, Object>) valorations.get(keys[x]);
            String id = (String) temporal.get("id");
            String restaurant_id = (String) temporal.get("restaurant_id");
            String rating = (String) temporal.get("rating");
            String comment = (String) temporal.get("comment");
            String userid = (String) temporal.get("userid");
            valorationsList.add(new Valoration(id, String.valueOf(restaurant_id), rating, comment, userid));
        }
        return valorationsList;
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
