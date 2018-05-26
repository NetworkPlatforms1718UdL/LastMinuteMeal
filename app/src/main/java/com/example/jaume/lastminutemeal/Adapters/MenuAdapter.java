package com.example.jaume.lastminutemeal.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jaume.lastminutemeal.Fragments.FragmentListMenu;
import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Menu;

import java.util.ArrayList;
import java.util.Objects;

public class MenuAdapter extends ArrayAdapter<Menu> {

    private Activity context;
    private ArrayList<Menu> data;

    public MenuAdapter(FragmentListMenu fragmentListMenu, ArrayList<Menu> data) {
        super(Objects.requireNonNull(fragmentListMenu.getActivity()), R.layout.listitem_menu, data);
        this.context = fragmentListMenu.getActivity();
        this.data = data;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"})
        View item = inflater.inflate(R.layout.listitem_menu, null);

        TextView lblPerson = item.findViewById(R.id.LblPerson);
        lblPerson.setText(String.format("Menu %s", String.valueOf(data.get(position).getPerson())));

        TextView lblfd = item.findViewById(R.id.Lblfd);
        lblfd.setText(data.get(position).getFirstDish());

        TextView lblsd = item.findViewById(R.id.Lblsd);
        lblsd.setText(data.get(position).getSecondDish());

        TextView lbld = item.findViewById(R.id.Lbld);
        lbld.setText(data.get(position).getDesert());

        TextView lbldr = item.findViewById(R.id.Lbldr);
        lbldr.setText(data.get(position).getDrink());

        TextView lblc = item.findViewById(R.id.Lblc);
        lblc.setText(data.get(position).getCoffee());

        return (item);
    }

    public void setData(ArrayList<Menu> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}