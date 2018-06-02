package com.example.jaume.lastminutemeal.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.Activities.DetailReserva;
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
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReservas extends Fragment implements ValueEventListener {

    private static String RESERVA = "reserva";
    public ListView list;
    public ReservasAdapter reservaAdapter;
    ArrayList<Reserva> resList;

    public FragmentReservas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservas, container, false);
        getFirebaseData();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.reserva);
        return view;
    }

    public void getFirebaseData() {
        Query mQuery = FirebaseDatabase.getInstance()
                .getReference("booking")
                .orderByChild("userid")
                .equalTo(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        mQuery.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        HashMap<String, Object> reservas =
                (HashMap<String, Object>) dataSnapshot.getValue();
        assert reservas != null;
        try {
            resList = getReservasList(reservas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reservaAdapter = new ReservasAdapter(Objects.requireNonNull(getContext()), resList);
        list = Objects.requireNonNull(getView()).findViewById(R.id.LstReservas);
        list.setAdapter(reservaAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                Intent intent = new Intent(getActivity(), DetailReserva.class);
                intent.putExtra(RESERVA, resList.get(pos));
                startActivity(intent);
            }
        });
    }

    private ArrayList<Reserva> getReservasList(HashMap<String, Object> reservas) throws Exception {
        try {
            Object[] keys = reservas.keySet().toArray();
            ArrayList<Reserva> reservasList = new ArrayList<>();
            for (int x = 0; x < reservas.size(); x++) {
                HashMap<String, Object> temporal = (HashMap<String, Object>) reservas.get(keys[x]);
                String id = (String) temporal.get("id");
                String time = (String) temporal.get("time");
                String restaurant_id = (String) temporal.get("restaurant_id");
                String userid = (String) temporal.get("userid");
                ArrayList<HashMap<String, Object>> menus = (ArrayList<HashMap<String, Object>>) temporal.get("menu");
                ArrayList<Menu> menuArrayList = new ArrayList<>();
                for (int y = 0; y < menus.size(); y++) {
                    menuArrayList.add(new Menu(menus.get(y)));
                }
                reservasList.add(new Reserva(id, String.valueOf(restaurant_id), time, userid, menuArrayList));
            }
            return reservasList;
        } catch (Exception e) {
            Toast.makeText(getContext(), "There are no bookings yet. Please, book in a restaurant before!", Toast.LENGTH_SHORT).show();
            return new ArrayList<Reserva>();
        }
    }


    @Override
    public void onCancelled(DatabaseError databaseError) {
        //Toast.makeText(this, "There are erroreservaAdapter...", Toast.LENGTH_SHORT).show();
        Log.d("DDBBErroreservaAdapter", databaseError.getDetails());
    }
}
