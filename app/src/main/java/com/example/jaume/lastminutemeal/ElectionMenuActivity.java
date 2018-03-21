package com.example.jaume.lastminutemeal;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

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
    public void onMenuSelect(Menu c) {
        FragmentDetailMenu fgdet = (FragmentDetailMenu) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        boolean hayDetalle = (fgdet != null && fgdet.isInLayout());

        if (hayDetalle) {
            fgdet.mostrarDetalle(c);
        }
        else {
            Intent i = new Intent(this, DetailMenuActivity.class);
            i.putExtra(DetailMenuActivity.EXTRA_TEXT, c);
            startActivity(i);
        }

    }

    public FragmentListMenu getFragmentListMenu() {
        return fragmentListMenu;
    }
}
