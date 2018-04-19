package com.example.jaume.lastminutemeal.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.Activities.LoginActivity;
import com.example.jaume.lastminutemeal.Activities.MainActivity;
import com.example.jaume.lastminutemeal.Utils.MapUtils;
import com.example.jaume.lastminutemeal.Utils.Menu;
import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Reserva;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentResumen extends Fragment {

    private Menu menu;
    private ArrayList<Menu> menuArrayList;
    private TextView tv, tv2;
    private String hora, lugar;
    private Reserva reserva;
    private DatabaseReference mDatabase;

    public FragmentResumen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resumen, container, false);
        tv = (TextView) view.findViewById(R.id.textView3);
        tv2 = (TextView) view.findViewById(R.id.textView4);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                reserva = new Reserva(lugar,hora,menuArrayList);

                mDatabase = FirebaseDatabase.getInstance().getReference();
                String key = mDatabase.child("booking").push().getKey();
                Map<String,Object> postValues = reserva.uploadToDataBase();
                Map<String,Object> childUpdates = new HashMap<>();
                childUpdates.put("/booking/"+key, postValues);
                mDatabase.updateChildren(childUpdates);
                //TODO Añadir la reserva a la base de datos

                Toast.makeText(getActivity(), "RESERVA REALIZADA", Toast.LENGTH_SHORT).show();

                getActivity().finish();
            }
        });
        return view;
    }

    public void mostrarResumen(ArrayList<Menu> menuArrayList, String hora, String lugar) {
        this.menuArrayList = menuArrayList;
        this.hora = hora;
        this.lugar = lugar;
        StringBuilder line = new StringBuilder();
        tv2.setText("Lugar: "+lugar+"\n"+"Hora: "+hora);
        for(int x=1; x<=menuArrayList.size(); x++) {
            line.append("Menú "+x+"\n");
            line.append("Primer plato: " + menuArrayList.get(x-1).getFirstDish() + "\n");
            line.append("Segundo plato: " + menuArrayList.get(x-1).getSecondDish() + "\n");
            line.append("Postre: " + menuArrayList.get(x-1).getDesert() + "\n");
            line.append("Bebida: " + menuArrayList.get(x-1).getDrink() + "\n");
            line.append("Café: " + menuArrayList.get(x-1).getCoffee() + "\n");
            line.append("\n");
            line.append("----------------------");
            line.append("\n");
        }
        tv.setText(line);
    }

}
