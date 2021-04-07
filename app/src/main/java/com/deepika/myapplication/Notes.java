package com.deepika.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Notes extends AppCompatActivity {

    CardView kannada, english;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        kannada = findViewById(R.id.kannada);
        english = findViewById(R.id.english);


        kannada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, kannada.class);
                startActivity(intent);
            }
        });


        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, english.class);
                startActivity(intent);
            }
        });
    }
}