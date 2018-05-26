package com.example.jaume.lastminutemeal.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jaume.lastminutemeal.Activities.DetailMenuActivity;
import com.example.jaume.lastminutemeal.Activities.ElectionMenuActivity;
import com.example.jaume.lastminutemeal.R;
import com.example.jaume.lastminutemeal.Utils.Menu;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentDetailMenu extends Fragment {

    private static final String[] FDISH = {
            "Tomato Soup", "French Onion Soup", "Tomato Salad", "Chicken Salad",
    };
    private static final String[] SDISH = {
            "German sausage and chips", "Grilled fish and potatoes", "Italian cheese & tomato pizza",
            "Thai chicken and rice", "Vegetable pasta", "Roast chicken and potatoes",
    };
    private static final String[] DESERT = {
            "Fruit salad and cream", "Ice cream", "Lemon cake", "Chocolate cake", "Cheese and biscuits",
    };
    private static final String[] DRINK = {
            "Mineral water", "Fresh orange juice", "Soft drinks", "English Tea", "Irish Cream Coffee",
    };
    private static final String[] COFFE = {
            "No", "Café Latte", "Mocha Latte", "Espresso", "Hot Chocolate", "Café Au Lait",
    };
    Spinner fDish;
    Spinner sDish;
    Spinner desert;
    Spinner drink;
    Spinner coffee;

    private Menu menu;
    private ArrayList<Menu> menuArrayList;
    private int position;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        Button button = view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setFirstDish(fDish.getSelectedItem().toString());
                menu.setSecondDish(sDish.getSelectedItem().toString());
                menu.setDesert(desert.getSelectedItem().toString());
                menu.setDrink(drink.getSelectedItem().toString());
                menu.setCoffee(coffee.getSelectedItem().toString());
                menuArrayList.set(position, menu);
                Intent intent = new Intent(getActivity(), ElectionMenuActivity.class);
                intent.putExtra(DetailMenuActivity.EXTRA_TEXT, menuArrayList);
                Objects.requireNonNull(getActivity()).setResult(1234, intent);
                getActivity().finish();
            }
        });
        return view;
    }

    public void mostrarDetalle(ArrayList<Menu> menuArrayList, int position) {
        this.position = position;
        this.menuArrayList = menuArrayList;
        this.menu = menuArrayList.get(position);
        TextView txtDetalle = Objects.requireNonNull(getView()).findViewById(R.id.TxtDetalle);
        txtDetalle.setText(String.format("Menu %s", String.valueOf(menu.getPerson())));
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        fDish = Objects.requireNonNull(getActivity()).findViewById(R.id.firstDish);
        sDish = getActivity().findViewById(R.id.seconDish);
        desert = getActivity().findViewById(R.id.desertt);
        drink = getActivity().findViewById(R.id.drink);
        coffee = getActivity().findViewById(R.id.coffe);
        fDish.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, FDISH));
        sDish.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, SDISH));
        desert.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, DESERT));
        drink.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, DRINK));
        coffee.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, COFFE));
    }
}
