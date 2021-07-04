package com.deepika.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class teacher_noticeboard extends Fragment {
    TextInputLayout eventtitle, eventdesc, eventdate, eventsubject;
    Button event_post;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;


    public teacher_noticeboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_teacher_noticeboard, container, false);

        eventtitle = v.findViewById(R.id.eventtitle);
        eventsubject = v.findViewById(R.id.eventsubject);
        eventdesc = v.findViewById(R.id.eventdesc);
        eventdate = v.findViewById(R.id.eventdate);
        event_post = v.findViewById(R.id.event_post);



        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        event_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String utitle = eventtitle.getEditText().getText().toString();
                String udesc = eventdesc.getEditText().getText().toString();
                String udue = eventdate.getEditText().getText().toString();
                String esubject = eventsubject.getEditText().getText().toString();

                if (utitle.isEmpty() || esubject.isEmpty() || udesc.isEmpty() || udue.isEmpty())
                {

                }
                else
                {
                    HashMap<Object, String> hashMap = new HashMap<>();
                    hashMap.put("Title", utitle);
                    hashMap.put("Description", udesc);
                    hashMap.put("EventSubject", esubject);
                    hashMap.put("Eventdate", udue);
                    final String timestamp = String.valueOf(System.currentTimeMillis());

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notice Board");

                    reference.child(timestamp).setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Event updated", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        return v;

    }
}