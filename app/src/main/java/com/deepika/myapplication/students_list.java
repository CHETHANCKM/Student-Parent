package com.deepika.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.deepika.myapplication.adapters.studentsadapter;
import com.deepika.myapplication.models.StudentsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class students_list extends AppCompatActivity {
    RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    ArrayList<StudentsModel> list;
    studentsadapter studentsadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        recyclerView = findViewById(R.id.recyclerView);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();

        studentsadapter = new studentsadapter(this, list);
        studentsadapter.setHasStableIds(true);
        recyclerView.setAdapter(studentsadapter);

        mDatabase = FirebaseDatabase.getInstance()
                .getReference().child("Students");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds: snapshot.getChildren())
                {
                    StudentsModel studentsModel = ds.getValue(StudentsModel.class);
                    list.add(studentsModel);
                }
                studentsadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}