package com.deepika.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.deepika.myapplication.adapters.EventAdapter;
import com.deepika.myapplication.models.EventModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class events extends AppCompatActivity {

    RecyclerView recycler;
    private DatabaseReference mDatabase;
    EventAdapter eventAdapter;
    ArrayList<EventModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recycler = findViewById(R.id.recyclerView3);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        list = new ArrayList<>();


        eventAdapter = new EventAdapter(this, list);
        eventAdapter.setHasStableIds(true);
        recycler.setAdapter(eventAdapter);

        mDatabase = FirebaseDatabase.getInstance()
                .getReference().child("Events");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds: snapshot.getChildren())
                {
                    EventModel noticeModel = ds.getValue(EventModel.class);
                    list.add(noticeModel);
                }
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(events.this, ""+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}