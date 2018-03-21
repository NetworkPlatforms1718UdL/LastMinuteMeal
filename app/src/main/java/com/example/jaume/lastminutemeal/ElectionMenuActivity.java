package com.example.jaume.lastminutemeal;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class ElectionMenuActivity extends FragmentActivity implements FragmentListMenu.MenuListener {

    FragmentListMenu fragmentListMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_menu);
        FragmentGeneralOptions fragmentGeneral = (FragmentGeneralOptions)
                getSupportFragmentManager().findFragmentById(R.id.FrgGeneral);
        fragmentListMenu = (FragmentListMenu) getSupportFragmentManager().
                findFragmentById(R.id.FrgListado);
        fragmentListMenu.setMenuListener(this);
    }

    @Override
    public void onMenuSelect(ArrayList<Menu> menu,int position) {
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
