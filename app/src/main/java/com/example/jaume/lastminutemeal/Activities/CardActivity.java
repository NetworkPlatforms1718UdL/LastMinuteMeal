package com.example.jaume.lastminutemeal.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.jaume.lastminutemeal.R;

public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        WebView myWebView = (WebView) findViewById(R.id.webView);
        String id = getIntent().getStringExtra("ID");
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("https://desolate-peak-27380.herokuapp.com/card?id="+id);
        Log.d("URL","https://desolate-peak-27380.herokuapp.com/card?id="+id);
    }
}
