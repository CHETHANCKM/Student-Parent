package com.deepika.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class add_events extends AppCompatActivity {

    TextInputLayout title, subject, desc, due;
    Button post;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);

        title = findViewById(R.id.eventtitle);
        subject = findViewById(R.id.eventsubject);
        desc = findViewById(R.id.eventdesc);
        due = findViewById(R.id.eventdate);
        post = findViewById(R.id.event_post);



        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String utitle = title.getEditText().getText().toString();
                String usub = subject.getEditText().getText().toString();
                String udesc = desc.getEditText().getText().toString();
                String udue = due.getEditText().getText().toString();

                if (utitle.isEmpty() || usub.isEmpty() || udesc.isEmpty() || udue.isEmpty())
                {

                }
                else
                {
                    HashMap<Object, String> hashMap = new HashMap<>();
                    hashMap.put("Title", utitle);
                    hashMap.put("Subject", usub);
                    hashMap.put("Description", udesc);
                    hashMap.put("Eventdate", udue);
                    final String timestamp = String.valueOf(System.currentTimeMillis());

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Events");

                    reference.child(timestamp).setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(add_events.this, "Event updated", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(add_events.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}