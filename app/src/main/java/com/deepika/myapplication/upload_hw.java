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

public class upload_hw extends AppCompatActivity {
    TextInputLayout title, subject, desc, due;
    Button post;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_hw);
        title = findViewById(R.id.utitle);
        subject = findViewById(R.id.usubject);
        desc = findViewById(R.id.udesc);
        due = findViewById(R.id.udue);
        post = findViewById(R.id.add_hw);


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
                    hashMap.put("Due_Date", udue);
                    final String timestamp = String.valueOf(System.currentTimeMillis());

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Homeworks");

                    reference.child(timestamp).setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(upload_hw.this, "Home work updated", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(upload_hw.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


    }
}