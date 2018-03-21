package com.example.jaume.lastminutemeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.Fragment;

public class FragmentDetailMenu extends Fragment {

    private static final String[] FDISH = {
            "Caracoles", "Macarrones", "Pizza",
    };
    Spinner fDish;
    private static final String[] SDISH = {
            "Pescado", "Carne",
    };
    Spinner sDish;
    private static final String[] DESERT = {
            "Helado", "Crepe", "Tarta de queso",
    };
    Spinner desert;

    private String fd;
    private String sd;
    private String d;

    private Menu menu;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        Button button = (Button) view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                menu.setFirstDish(fDish.getSelectedItem().toString());
                menu.setSecondDish(sDish.getSelectedItem().toString());
                menu.setDesert(desert.getSelectedItem().toString());
            }
        });
        return view;
    }

    public void mostrarDetalle(Menu menu) {
        this.menu = menu;
        TextView txtDetalle = (TextView) getView().findViewById(R.id.TxtDetalle);
        txtDetalle.setText(String.format("Menu %s", String.valueOf(menu.getPerson())));
        if (menu.getFirstDish() != null) fDish.setSelection(obtainSpinnerPosition(fDish,menu.getFirstDish()));
        if (menu.getSecondDish() != null) sDish.setSelection(obtainSpinnerPosition(sDish,menu.getSecondDish()));
        if (menu.getDesert() != null) desert.setSelection(obtainSpinnerPosition(desert,menu.getDesert()));
    }

    private int obtainSpinnerPosition(Spinner spinner, String selection) {
        int position = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(selection)) {
                position = i;
                break;
            }
        }
        return position;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        fDish = (Spinner) getActivity().findViewById(R.id.firstDish);
        sDish = (Spinner) getActivity().findViewById(R.id.seconDish);
        desert = (Spinner) getActivity().findViewById(R.id.desertt);
        fDish.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, FDISH));
        sDish.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, SDISH));
        desert.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, DESERT));
    }
}
