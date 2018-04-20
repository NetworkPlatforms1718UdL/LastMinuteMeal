package com.example.jaume.lastminutemeal.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.jaume.lastminutemeal.R;

public class DetailValoration extends AppCompatActivity {

    private static String BAR = "bar";
    private static String COMMENT = "comment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_valoration);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(BAR));
        TextView tv = (TextView) findViewById(R.id.textView8);
        tv.setText(getIntent().getStringExtra(COMMENT));
    }
}
