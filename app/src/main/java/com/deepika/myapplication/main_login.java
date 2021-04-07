    package com.deepika.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

    public class main_login extends AppCompatActivity {
    Button parent;
    Button teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        teacher = findViewById(R.id.button2);
        parent = findViewById(R.id.button);


        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_login.this, parent_login2.class);
                startActivity(intent);
            }
        });
        
        
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_login.this, teacher_login.class);
                startActivity(intent);
            }
        });
    }
}