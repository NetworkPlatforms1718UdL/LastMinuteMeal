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

    private ArrayList<Menu> data = new ArrayList<>();
    private ListView list;
    private MenuListener listener;
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
        menuAdapter = new MenuAdapter(this);
        list = (ListView)getView().findViewById(R.id.LstListado);
        list.setAdapter(menuAdapter);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    listener.onMenuSelect(
                            (Menu) FragmentListMenu.this.list.getAdapter().getItem(pos));
                }
            }
        });
    }

    class MenuAdapter extends ArrayAdapter<Menu> {

        Activity context;

        MenuAdapter(FragmentListMenu fragmentListMenu) {
            super(fragmentListMenu.getActivity(),R.layout.listitem_menu, data);
            this.context = fragmentListMenu.getActivity();
        }

        @NonNull
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_menu, null);

            TextView lblPerson = (TextView)item.findViewById(R.id.LblPerson);
            lblPerson.setText(String.format("Menu %s", String.valueOf(data.get(position).getPerson())));

            TextView lblfd = (TextView)item.findViewById(R.id.Lblfd);
            lblfd.setText(data.get(position).getFirstDish());

            TextView lblsd = (TextView)item.findViewById(R.id.Lblsd);
            lblsd.setText(data.get(position).getSecondDish());

            TextView lbld = (TextView)item.findViewById(R.id.Lbld);
            lbld.setText(data.get(position).getDesert());

            TextView lbldr = (TextView)item.findViewById(R.id.Lbldr);
            lbldr.setText(data.get(position).getDrink());

            TextView lblc = (TextView)item.findViewById(R.id.Lblc);
            lblc.setText("YES");

            return(item);
        }
    }

    public void setClients(int numTotal){
        data = new ArrayList<>();
        for (int x=1; x<numTotal+1; x++){
            data.add(new Menu(x));
        }
        list.setAdapter(new MenuAdapter(this));
    }

    public interface MenuListener {
        void onMenuSelect(Menu c);
    }

    public void setMenuListener(MenuListener listener) {
        this.listener = listener;
    }
}
