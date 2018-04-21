package com.example.jaume.lastminutemeal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Reserva;

import java.util.ArrayList;

public class ReservasAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    ArrayList<Reserva> data;

    public ReservasAdapter (Context context, ArrayList<Reserva> reservas){
        this.context = context;
        this.data = reservas;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.activity_reserva_list, null);
        TextView name = vista.findViewById(R.id.bookRestaurantName);
        TextView time = vista.findViewById(R.id.bookTime);
        TextView id = vista.findViewById(R.id.bookId);
        TextView people = vista.findViewById(R.id.bookPeople);
        name.setText(data.get(i).getLugar());
        time.setText(data.get(i).getHora());
        id.setText(data.get(i).getId());
        people.setText(String.valueOf(data.get(i).getPersonas()));
        return vista;
    }
}
