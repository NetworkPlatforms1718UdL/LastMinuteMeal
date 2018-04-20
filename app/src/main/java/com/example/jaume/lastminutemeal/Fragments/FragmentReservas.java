package com.example.jaume.lastminutemeal.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jaume.lastminutemeal.Adapters.ReservasAdapter;
import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Menu;
import com.example.jaume.lastminutemeal.Utils.Reserva;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReservas extends Fragment {

    public ListView list;
    public ReservasAdapter rs;

    public FragmentReservas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<Reserva> reservaList = getFirebaseData();
        View view = inflater.inflate(R.layout.fragment_reservas, container, false);
        rs = new ReservasAdapter(getContext(),reservaList);
        list = (ListView)getView().findViewById(R.id.LstReservas);
        list.setAdapter(rs);
        return view;
    }

    public ArrayList<Reserva> getFirebaseData() {
        Query mQuery = FirebaseDatabase.getInstance()
                .getReference("booking")
                .orderByChild("userid")
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

        EventListener eventListener = new EventListener();
        mQuery.addListenerForSingleValueEvent(eventListener);
        while (true) {
            if (eventListener.resList != null) return eventListener.resList;
        }
    }

    public class EventListener implements ValueEventListener {

        ArrayList<Reserva> resList;

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            HashMap<String, Object> reservas =
                    (HashMap<String, Object>) dataSnapshot.getValue();
            resList = getReservasList(reservas);

        }

        private ArrayList<Reserva> getReservasList(HashMap<String, Object> reservas) {
            Object[] keys = reservas.keySet().toArray();
            ArrayList<Reserva> reservasList = new ArrayList<>();
            for (int x = 0; x < reservas.size(); x++) {
                HashMap<String, Object> temporal = (HashMap<String, Object>) reservas.get(keys[x]);
                String time = (String) temporal.get("time");
                String restaurant_id = (String) temporal.get("restaurant_id");
                String userid = (String) temporal.get("userid");
                ArrayList<HashMap<String,Object>> menus = (ArrayList<HashMap<String,Object>>) temporal.get("menu");
                ArrayList<Menu> menuArrayList = new ArrayList<>();
                for (int y = 0; y < menus.size(); y++) {
                    menuArrayList.add(new Menu(menus.get(y)));
                }
                reservasList.add(new Reserva(String.valueOf(restaurant_id), time, userid, menuArrayList));
            }
            return reservasList;
        }


        @Override
        public void onCancelled(DatabaseError databaseError) {
            //Toast.makeText(this, "There are errors...", Toast.LENGTH_SHORT).show();
            Log.d("DatabaseErrors", databaseError.getDetails());
        }
    }
}
