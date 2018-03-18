package com.example.jaume.lastminutemeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class FragmentGeneralOptions extends Fragment {

    private static final String[] NUMBER_PERSONS = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
    };
    Spinner mNumberPersons;
    private static final String[] HOURS = {
            "20:00",
            "20:15",
            "20:30",
            "20:45",
            "21:00",
            "21:15",
            "21:30",
            "21:45",
            "22:00",
            "22:15",
            "22:30",
            "22:45",
    };
    Spinner mHours;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_general, container, false);
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
