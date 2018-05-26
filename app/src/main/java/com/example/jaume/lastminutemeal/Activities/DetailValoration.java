package com.example.jaume.lastminutemeal.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.jaume.lastminutemeal.R;

public class DetailValoration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_valoration);

        Toolbar toolbar = findViewById(R.id.toolbar);
        String BAR = "bar";
        toolbar.setTitle(getIntent().getStringExtra(BAR));
        TextView tv = findViewById(R.id.textView8);
        String COMMENT = "comment";
        tv.setText(getIntent().getStringExtra(COMMENT));
    }
}
