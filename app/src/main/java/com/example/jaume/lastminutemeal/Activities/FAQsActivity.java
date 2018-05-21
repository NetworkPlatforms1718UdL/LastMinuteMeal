package com.example.jaume.lastminutemeal.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.jaume.lastminutemeal.R;

public class FAQsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("https://tranquil-lowlands-76974.herokuapp.com/cool");
    }
}
