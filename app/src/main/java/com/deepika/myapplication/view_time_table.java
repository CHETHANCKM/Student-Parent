package com.deepika.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class view_time_table extends AppCompatActivity {
    ImageView time_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table);

        time_image = findViewById(R.id.time_image);

            final String[] url = {null};
            DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                    .getReference("timetable")
                    .child("filepath");



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                url[0] = snapshot.getValue(String.class);

                if (url[0]==null)
                {
                    Toast.makeText(view_time_table.this, "Loading... Try again", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Picasso.get().load(url[0]).into(time_image);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}