package com.example.jaume.lastminutemeal.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.jaume.lastminutemeal.Activities.ElectionMenuActivity;
import com.example.jaume.lastminutemeal.R;

import java.util.Objects;


public class FragmentGeneralOptions extends Fragment implements AdapterView.OnItemSelectedListener{

    private static final String[] NUMBER_PERSONS = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    };
    private static final String[] HOURS = {
            "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45", "22:00",
            "22:15", "22:30", "22:45",
    };
    Spinner mNumberPersons;
    Spinner mHours;
    private String pers;
    private String hor;
    private String lugar;
    private ElectionMenuActivity ema;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_general, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        ema = (ElectionMenuActivity) getActivity();

        mNumberPersons = Objects.requireNonNull(getActivity()).findViewById(R.id.personas_spinner);
        mNumberPersons.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, NUMBER_PERSONS));
        mHours = getActivity().findViewById(R.id.hora_spinner);
        mHours.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, HOURS));

        mNumberPersons.setOnItemSelectedListener(this);
        mHours.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ElectionMenuActivity activity = (ElectionMenuActivity) getActivity();
        assert activity != null;
        FragmentListMenu fragmentListMenu = activity.getFragmentListMenu();
        pers = mNumberPersons.getSelectedItem().toString();
        hor = mHours.getSelectedItem().toString();
        lugar = ema.LocalName;
        Log.d("FragmentGeneralOptLocal",ema.LocalName);
        Log.d("FragmentGeneralOptHour",hor);
        fragmentListMenu.setClients(Integer.parseInt(pers), hor, lugar);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Nothing
    }
}
