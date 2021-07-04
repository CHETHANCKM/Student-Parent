package com.deepika.myapplication.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.deepika.myapplication.R;
import com.deepika.myapplication.adapters.NotesAdapter;
import com.deepika.myapplication.models.NotesModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class subject_notes extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    NotesAdapter notesAdapter;
    ArrayList<NotesModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_notes);
        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");


        mDatabase = FirebaseDatabase.getInstance()
                .getReference().child("Notes").child(subject);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();

        NotesAdapter notesAdapter = new NotesAdapter(this, list);
        notesAdapter.setHasStableIds(true);
        recyclerView.setAdapter(notesAdapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds: snapshot.getChildren())
                {
                    NotesModel schemeModel = ds.getValue(NotesModel.class);
                    list.add(schemeModel);
                }
                notesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(subject_notes.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}