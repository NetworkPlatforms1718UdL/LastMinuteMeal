package com.example.jaume.lastminutemeal.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Valoration;

import java.util.ArrayList;

public class ValoracionesAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    ArrayList<Valoration> valList;

    public ValoracionesAdapter(Context context, ArrayList<Valoration> valList) {
        this.context = context;
        this.valList = valList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return valList.size();
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
        @SuppressLint({"ViewHolder", "InflateParams"}) final View vista = inflater.inflate(R.layout.activity_valoracion_list, null);
        TextView name = vista.findViewById(R.id.textView5);
        RatingBar coment = vista.findViewById(R.id.ratingBar);
        name.setText(valList.get(i).getRestaurant_id());
        coment.setRating(Float.parseFloat(valList.get(i).getRating()));
        return vista;
    }
}
