package com.example.jaume.lastminutemeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


public class FragmentGeneralOptions extends Fragment {

    private String pers;
    private String hor;
    private FragmentListMenu flm;

    private static final String[] NUMBER_PERSONS = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    };
    Spinner mNumberPersons;
    private static final String[] HOURS = {
            "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00",
            "22:15", "22:30", "22:45",
    };
    Spinner mHours;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_general,
                container, false);
        Button button = (Button) view.findViewById(R.id.button2);
        final LinearLayout Ll = (LinearLayout) view.findViewById(R.id.capa_reserva);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ElectionMenuActivity activity = (ElectionMenuActivity) getActivity();
                FragmentListMenu fragmentListMenu = activity.getFragmentListMenu();
                pers = mNumberPersons.getSelectedItem().toString();
                hor = mHours.getSelectedItem().toString();
                Ll.setVisibility(View.VISIBLE);
                fragmentListMenu.setClients(Integer.parseInt(pers));
            }
        });

        Button button2 = (Button) view.findViewById(R.id.button5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                Toast.makeText(getActivity(), "PEDIDO ENVIADO!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        mNumberPersons = (Spinner) getActivity().findViewById(R.id.personas_spinner);
        mNumberPersons.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, NUMBER_PERSONS));
        mHours = (Spinner) getActivity().findViewById(R.id.hora_spinner);
        mHours.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, HOURS));
    }
}
