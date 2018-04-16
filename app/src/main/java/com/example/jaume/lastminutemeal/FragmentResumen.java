package com.example.jaume.lastminutemeal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentResumen extends Fragment {

    private Menu menu;
    private ArrayList<Menu> menuArrayList;

    public FragmentResumen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resumen, container, false);

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
        Toast.makeText(getActivity(), "Menu ok, hora: "+hora, Toast.LENGTH_SHORT).show();
        this.menuArrayList = menuArrayList;
        TextView txtDetalle = (TextView) getView().findViewById(R.id.TxtDetalle);
    }

}
