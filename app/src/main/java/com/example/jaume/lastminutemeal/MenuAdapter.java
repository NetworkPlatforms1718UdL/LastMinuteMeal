package com.example.jaume.lastminutemeal;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class MenuAdapter extends ArrayAdapter<Menu> {

    private Activity context;
    private ArrayList<Menu> data;

    MenuAdapter(FragmentListMenu fragmentListMenu, ArrayList<Menu> data) {
        super(fragmentListMenu.getActivity(),R.layout.listitem_menu, data);
        this.context = fragmentListMenu.getActivity();
        this.data = data;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
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

        //TextView lbldr = (TextView)item.findViewById(R.id.Lbldr);
        //lbldr.setText(data.get(position).getDrink());

        //TextView lblc = (TextView)item.findViewById(R.id.Lblc);
        //lblc.setText("YES");

        return(item);
    }

    public void setData(ArrayList<Menu> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}