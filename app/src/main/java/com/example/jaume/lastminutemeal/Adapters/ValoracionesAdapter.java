package com.example.jaume.lastminutemeal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaume.lastminutemeal.R;

import org.w3c.dom.Text;

public class ValoracionesAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    String[][] datos;

    public ValoracionesAdapter (Context context, String[][] datos){
        this.context = context;
        this.datos = datos;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
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
        final View vista = inflater.inflate(R.layout.activity_valoracion_list, null);
        TextView name = (TextView) vista.findViewById(R.id.textView5);
        TextView coment = (TextView) vista.findViewById(R.id.textView6);
        name.setText(datos[i][0]);
        coment.setText(datos[i][1]);
        Toast.makeText(context, "Carga vista", Toast.LENGTH_SHORT).show();
        return vista;
    }
}
