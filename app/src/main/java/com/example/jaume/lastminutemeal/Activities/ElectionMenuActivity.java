package com.example.jaume.lastminutemeal.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.jaume.lastminutemeal.Fragments.FragmentGeneralOptions;
import com.example.jaume.lastminutemeal.Fragments.FragmentListMenu;
import com.example.jaume.lastminutemeal.Utils.MapUtils;
import com.example.jaume.lastminutemeal.Utils.Menu;
import com.example.jaume.lastminutemeal.Adapters.MenuAdapter;
import com.example.jaume.lastminutemeal.R;

import java.util.ArrayList;

public class ElectionMenuActivity extends FragmentActivity implements FragmentListMenu.MenuListener {

    FragmentListMenu fragmentListMenu;
    String LocalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_menu);

        Intent intent = getIntent();
        LocalName = intent.getStringExtra(MapUtils.LOCAL_NAME);
        TextView textView = (TextView) findViewById(R.id.LocalName);
        textView.setText(LocalName);

        FragmentGeneralOptions fragmentGeneral = (FragmentGeneralOptions)
                getSupportFragmentManager().findFragmentById(R.id.FrgGeneral);
        fragmentListMenu = (FragmentListMenu) getSupportFragmentManager().
                findFragmentById(R.id.FrgListado);
        fragmentListMenu.setMenuListener(this);
    }

    @Override
    public void onMenuSelect(ArrayList<Menu> menu, int position) {
        Intent i = new Intent(this, DetailMenuActivity.class);
        i.putExtra(DetailMenuActivity.EXTRA_TEXT, menu);
        i.putExtra(DetailMenuActivity.POSITION,position);
        startActivityForResult(i,1234);
    }

    public FragmentListMenu getFragmentListMenu() {
        return fragmentListMenu;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == resultCode) {
            ArrayList<Menu> menu = (ArrayList<Menu>) intent.getSerializableExtra(DetailMenuActivity.EXTRA_TEXT);
            fragmentListMenu.menuAdapter = new MenuAdapter(fragmentListMenu, menu);
            fragmentListMenu.list.setAdapter(fragmentListMenu.menuAdapter);
            fragmentListMenu.data = menu;
        }
    }

}
