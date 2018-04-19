package com.example.jaume.lastminutemeal.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jaume.lastminutemeal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReservas extends Fragment {

    public ListView list;
    //private General g;
    //public ReservasAdapter rs;

    public FragmentReservas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservas, container, false);
        /*rs = new ReservasAdapter(this,g);
        list = (ListView)getView().findViewById(R.id.LstReservas);
        list.setAdapter(rs);*/
        return view;
    }

}
