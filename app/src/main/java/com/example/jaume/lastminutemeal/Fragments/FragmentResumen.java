package com.example.jaume.lastminutemeal.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jaume.lastminutemeal.Utils.Menu;
import com.example.jaume.lastminutemeal.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentResumen extends Fragment {

    private Menu menu;
    private ArrayList<Menu> menuArrayList;
    private EditText et;

    public FragmentResumen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resumen, container, false);
        et = (EditText) view.findViewById(R.id.editText);
        return view;
    }

    public void mostrarResumen(ArrayList<Menu> menuArrayList, int hora) {
        this.menuArrayList = menuArrayList;
        StringBuilder line = new StringBuilder();
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
        et.setText(line);
    }

}
