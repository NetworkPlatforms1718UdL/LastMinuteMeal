package com.example.jaume.lastminutemeal.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Menu;
import com.example.jaume.lastminutemeal.Utils.Reserva;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentResumen extends Fragment {

    private ArrayList<Menu> menuArrayList;
    private TextView tv, tv2;
    private String hora, lugar;
    private Reserva reserva;
    private DatabaseReference mDatabase;

    public FragmentResumen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resumen, container, false);
        tv = view.findViewById(R.id.textView3);
        tv2 = view.findViewById(R.id.textView4);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                String key = mDatabase.child("booking").push().getKey();
                reserva = new Reserva(key, lugar, hora, uid, menuArrayList);
                Map<String, Object> postValues = reserva.uploadToDataBase();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/booking/" + key, postValues);
                mDatabase.updateChildren(childUpdates);
                mDatabase.child("confirmation").child(uid).setValue(true);
                Toast.makeText(getActivity(), "RESERVA REALIZADA", Toast.LENGTH_SHORT).show();
                Objects.requireNonNull(getActivity()).finish();
            }
        });
        return view;
    }

    @SuppressLint("SetTextI18n")
    public void mostrarResumen(ArrayList<Menu> menuArrayList, String hora, String lugar) {
        this.menuArrayList = menuArrayList;
        this.hora = hora;
        this.lugar = lugar;
        Log.d("FragmentResumenHora",hora);
        Log.d("FragmentResumenLugar",lugar);
        StringBuilder line = new StringBuilder();
        tv2.setText("Where: " + lugar + "\n" + "When: " + hora);
        for (int x = 1; x <= menuArrayList.size(); x++) {
            line.append("Menu ").append(x).append("\n");
            line.append("First dish: ").append(menuArrayList.get(x - 1).getFirstDish()).append("\n");
            line.append("Second dish: ").append(menuArrayList.get(x - 1).getSecondDish()).append("\n");
            line.append("Desert: ").append(menuArrayList.get(x - 1).getDesert()).append("\n");
            line.append("Drink: ").append(menuArrayList.get(x - 1).getDrink()).append("\n");
            line.append("Coffee: ").append(menuArrayList.get(x - 1).getCoffee()).append("\n");
            line.append("\n");
            line.append("----------------------");
            line.append("\n");
        }
        tv.setText(line);
    }

}
