package com.deepika.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.deepika.myapplication.adapters.HomeWorkAdapter;
import com.deepika.myapplication.models.HomeWorkModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class
homework extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    ArrayList<HomeWorkModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        recyclerView = findViewById(R.id.recyclerView);

        mDatabase = FirebaseDatabase.getInstance()
                .getReference().child("Homeworks");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();

        HomeWorkAdapter homeWorkAdapter = new HomeWorkAdapter(this, list);
        homeWorkAdapter.setHasStableIds(true);
        recyclerView.setAdapter(homeWorkAdapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds: snapshot.getChildren())
                {
                    HomeWorkModel schemeModel = ds.getValue(HomeWorkModel.class);
                    list.add(schemeModel);
                }
                homeWorkAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(homework.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}