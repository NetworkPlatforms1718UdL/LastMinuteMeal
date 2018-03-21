package com.example.jaume.lastminutemeal;

import android.app.Activity;
//import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentListMenu extends Fragment {

    ArrayList<Menu> data = new ArrayList<>();
    public ListView list;
    private MenuListener listener;
    private FragmentListMenu fragmentListMenu;
    public MenuAdapter menuAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        data.add(new Menu(1));
        menuAdapter = new MenuAdapter(this,data);
        list = (ListView)getView().findViewById(R.id.LstListado);
        list.setAdapter(menuAdapter);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    listener.onMenuSelect(data,pos);
                }
            }
        });
    }

    public void setClients(int numTotal){
        data = new ArrayList<>();
        for (int x=1; x<numTotal+1; x++){
            data.add(new Menu(x));
        }
        menuAdapter = new MenuAdapter(this, data);
        list.setAdapter(menuAdapter);
    }

    public interface MenuListener {
        void onMenuSelect(ArrayList<Menu> c, int position);
    }

    public void setMenuListener(MenuListener listener) {
        this.listener = listener;
    }
}
