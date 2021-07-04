package com.deepika.myapplication.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.deepika.myapplication.R;

public class Webpage extends AppCompatActivity {
    WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        webView1 = findViewById(R.id.webView);

        String doc="<iframe src='http://docs.google.com/gview?embedded=true&url="+url+"width='100%' height='100%' style='border: none;'></iframe>";
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.getSettings().setAllowFileAccess(true);
        webView1.loadData( doc , "text/html", "UTF-8");

    }
}