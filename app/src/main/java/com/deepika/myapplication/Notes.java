package com.deepika.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.deepika.myapplication.notes.subject_notes;

public class Notes extends AppCompatActivity {

    CardView kannada, english, hindi, maths, science, social;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        kannada = findViewById(R.id.kannada);
        english = findViewById(R.id.english);
        hindi = findViewById(R.id.hindi);
        maths = findViewById(R.id.maths);
        science = findViewById(R.id.science);
        social = findViewById(R.id.social);


        kannada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, subject_notes.class);
                intent.putExtra("subject", "Kannada");
                startActivity(intent);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, subject_notes.class);
                intent.putExtra("subject", "English");
                startActivity(intent);
            }
        });

        hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, subject_notes.class);
                intent.putExtra("subject", "Hindi");
                startActivity(intent);
            }
        });

        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, subject_notes.class);
                intent.putExtra("subject", "Mathematics");
                startActivity(intent);
            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, subject_notes.class);
                intent.putExtra("subject", "Science");
                startActivity(intent);
            }
        });

        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, subject_notes.class);
                intent.putExtra("subject", "Social");
                startActivity(intent);
            }
        });

    }
}