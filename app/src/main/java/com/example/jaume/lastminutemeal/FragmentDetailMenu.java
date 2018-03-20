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
import android.widget.Toast;

public class FragmentDetailMenu extends Fragment {

    private static final String EXTRA = "EXTRA";
    private static final String FD = "FD";
    private static final String SD = "SD";
    private static final String D = "D";

    private static final String[] FDISH = {
            "Caracoles",
            "Macarrones",
            "Pizza",
    };
    Spinner fDish;
    private static final String[] SDISH = {
            "Pescado",
            "Carne",
            "Verdura",
    };
    Spinner sDish;
    private static final String[] DESERT = {
            "Helado",
            "Crepe",
            "Tarta de queso",
    };
    Spinner desert;

    private String pers;
    private String fd;
    private String sd;
    private String d;

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
                fd = sDish.getSelectedItem().toString();
                sd = fDish.getSelectedItem().toString();
                d = desert.getSelectedItem().toString();
                Intent intent = new Intent(getActivity(), ElectionMenuActivity.class);
                intent.putExtra(EXTRA,pers);
                intent.putExtra(FD,fd);
                intent.putExtra(SD,sd);
                intent.putExtra(D,d);
                getActivity().startActivity(intent);
                /*FragmentListMenu fragmentListMenu = (FragmentListMenu) getActivity().getSupportFragmentManager().
                        findFragmentById(R.id.FrgListado);
                //fragmentListMenu.setMenuListener((FragmentListMenu.MenuListener) getActivity());

                fragmentListMenu.loadMenuClient(fd,sd,d);*/
                //fragmentListMenu.setClients(Integer.parseInt(pers));
            }
        });
        return view;
        //return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    public void mostrarDetalle(String texto) {
        TextView txtDetalle = (TextView) getView().findViewById(R.id.TxtDetalle);
        pers = texto;
        txtDetalle.setText(texto);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        fDish = (Spinner) getActivity().findViewById(R.id.firstDish);
        fDish.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, FDISH));
        sDish = (Spinner) getActivity().findViewById(R.id.seconDish);
        sDish.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, SDISH));
        desert = (Spinner) getActivity().findViewById(R.id.desertt);
        desert.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, DESERT));
    }
}
