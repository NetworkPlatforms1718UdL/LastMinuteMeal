package com.example.jaume.lastminutemeal.Fragments;

//import android.app.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.jaume.lastminutemeal.Activities.DetailResumenActivity;
import com.example.jaume.lastminutemeal.Adapters.MenuAdapter;
import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Menu;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentListMenu extends Fragment {

    public ArrayList<Menu> data = new ArrayList<>();
    public ListView list;
    public MenuAdapter menuAdapter;
    private MenuListener listener;
    private String hora, lugar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listado,
                container, false);

        Button button2 = view.findViewById(R.id.button5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DetailResumenActivity.class);
                i.putExtra(DetailResumenActivity.EXTRA_TEXT, data);
                i.putExtra(DetailResumenActivity.HORA, hora);
                i.putExtra(DetailResumenActivity.LUGAR, lugar);
                Log.d("FragmentListMenuHora",hora);
                Log.d("FragmentListMenuLugar",lugar);
                startActivityForResult(i, 1234);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        data.add(new Menu(1));
        menuAdapter = new MenuAdapter(this, data);
        list = Objects.requireNonNull(getView()).findViewById(R.id.LstListado);
        list.setAdapter(menuAdapter);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    listener.onMenuSelect(data, pos);
                }
            }
        });
    }

    public void setClients(int numTotal, String hora, String lugar) {
        data = new ArrayList<>();
        for (int x = 1; x < numTotal + 1; x++) {
            data.add(new Menu(x));
        }
        this.hora = hora;
        this.lugar = lugar;
        menuAdapter = new MenuAdapter(this, data);
        list.setAdapter(menuAdapter);
    }

    public void setMenuListener(MenuListener listener) {
        this.listener = listener;
    }

    public interface MenuListener {
        void onMenuSelect(ArrayList<Menu> c, int position);
    }
}
