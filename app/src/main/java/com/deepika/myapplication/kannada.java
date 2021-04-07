package com.deepika.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class kannada extends AppCompatActivity {

    CardView m1k, m2k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kannada);

        m1k = findViewById(R.id.m1k);
        m2k = findViewById(R.id.m2k);

        m2k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/student-parent-f11ca.appspot.com/o/ud.pdf?alt=media&token=4319fd20-3a07-4c12-9300-113b3289cc33"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        m1k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/student-parent-f11ca.appspot.com/o/jan12.PDF?alt=media&token=d31701a0-851d-4d0d-963c-2c49a8cc0722"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


    }
}