package com.example.jaume.lastminutemeal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class ElectionMenuActivity extends FragmentActivity implements FragmentListMenu.MenuListener {

    private static final String EXTRA = "EXTRA";
    private static final String FD = "FD";
    private static final String SD = "SD";
    private static final String D = "D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_menu);
        FragmentGeneralOptions fragmentGeneral = (FragmentGeneralOptions)
                getSupportFragmentManager().findFragmentById(R.id.FrgGeneral);
        if (getIntent().getStringExtra(EXTRA).equals("detail"));

        FragmentListMenu fragmentListMenu = (FragmentListMenu) getSupportFragmentManager().
                findFragmentById(R.id.FrgListado);
        fragmentListMenu.loadMenuClient(getIntent().getStringExtra(EXTRA),getIntent().getStringExtra(FD),getIntent().getStringExtra(SD),getIntent().getStringExtra(D));
    }

    @Override
    public void onMenuSelect(Menu c) {
        FragmentDetailMenu fgdet = (FragmentDetailMenu) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        boolean hayDetalle = (fgdet != null && fgdet.isInLayout());

        if (hayDetalle) {
            fgdet.mostrarDetalle(c.getContent());
        }
        else {
            Intent i = new Intent(this, DetailMenuActivity.class);
            i.putExtra(DetailMenuActivity.EXTRA_TEXT, c.getPerson());
            startActivity(i);
        }

    }
}
