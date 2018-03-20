package com.example.jaume.lastminutemeal;

import android.app.Activity;
//import android.app.ListFragment;
import android.os.Bundle;
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

    /*private Menu[] data; =
            new Menu[]{
                    new Menu("Persona 1", "Menu", "Texto del menu 1"),
                    new Menu("Persona 2", "Tapas", "Texto de las tapas 2"),
                    new Menu("Persona 3", "Menu", "Texto del menu 3"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 3", "Menu", "Texto del menu 3"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 3", "Menu", "Texto del menu 3"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 3", "Menu", "Texto del menu 3"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 3", "Menu", "Texto del menu 3"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 4", "Tapas", "Texto de las tapas 4"),
                    new Menu("Persona 5", "Menu", "Texto del menu 5")};*/

    private ListView list;

    private MenuListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        list = (ListView)getView().findViewById(R.id.LstListado);
        list.setAdapter(new MenuAdapter(this));
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    listener.onMenuSelect((Menu) FragmentListMenu.this.list.getAdapter().getItem(pos));
                }
            }
        });
    }

    @Override
    public void onAttach(Activity ac) {
        super.onAttach(ac);
        try {
            listener = (MenuListener) ac;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(ac.toString() + " must implement OnCorreosListener");
        }
    }


    class MenuAdapter extends ArrayAdapter<Menu> {

        Activity context;

        MenuAdapter(FragmentListMenu fragmentListMenu) {
            super(fragmentListMenu.getActivity(),R.layout.listitem_menu, data);
            this.context = fragmentListMenu.getActivity();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_menu, null);

            TextView lblPerson = (TextView)item.findViewById(R.id.LblPerson);
            lblPerson.setText(data.get(position).getPerson());

            TextView lblfd = (TextView)item.findViewById(R.id.Lblfd);
            lblfd.setText(data.get(position).getType());

            TextView lblsd = (TextView)item.findViewById(R.id.Lblsd);
            lblsd.setText(data.get(position).getType());

            TextView lbld = (TextView)item.findViewById(R.id.Lbld);
            lbld.setText(data.get(position).getType());

            TextView lbldr = (TextView)item.findViewById(R.id.Lbldr);
            lbldr.setText(data.get(position).getType());

            TextView lblc = (TextView)item.findViewById(R.id.Lblc);
            lblc.setText(data.get(position).getType());

            return(item);
        }
    }

    public void setClients(int numTotal){
        data = new ArrayList<>();
        for (int x=1; x<numTotal+1; x++){
            data.add(new Menu("Menú "+x));
        }
        list.setAdapter(new MenuAdapter(this));
    }

    public void loadMenuClient(String pers, String fd, String sd, String d){
        if (pers != null && fd != null && sd != null && d != null){
            //data.set(0, new Menu(pers,"Menú", fd,sd,d,"aa",true));
            //data.get(0).setType(fd);
            //data.set(0,new Menu(pers));
            //
        }
    }

    public interface MenuListener {
        void onMenuSelect(Menu c);
    }

    public void setMenuListener(MenuListener listener) {
        this.listener = listener;
    }
}
