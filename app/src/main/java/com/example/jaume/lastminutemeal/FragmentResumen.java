package com.example.jaume.lastminutemeal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        /*Button button = (Button) view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ElectionMenuActivity.class);
                intent.putExtra(DetailMenuActivity.EXTRA_TEXT, menuArrayList);
                getActivity().setResult(1234, intent);
                getActivity().finish();
            }
        });*/
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
