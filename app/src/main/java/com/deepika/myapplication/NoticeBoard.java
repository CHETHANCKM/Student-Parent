package com.deepika.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.deepika.myapplication.adapters.noticeadapater;
import com.deepika.myapplication.models.NoticeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NoticeBoard extends Fragment{


    RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    noticeadapater noticeadapater;
    ArrayList<NoticeModel> list;

    public NoticeBoard() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_notice_board, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();

        noticeadapater = new noticeadapater(getContext(), list);
        noticeadapater.setHasStableIds(true);
        recyclerView.setAdapter(noticeadapater);

        mDatabase = FirebaseDatabase.getInstance()
                .getReference().child("Notice Board");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds: snapshot.getChildren())
                {
                    NoticeModel noticeModel = ds.getValue(NoticeModel.class);
                    list.add(noticeModel);
                }
                noticeadapater.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }


}