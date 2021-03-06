package com.deepika.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class teacher_dashboard extends Fragment{

    CardView add_newstudent, homework, events, upload_notes,mystudents, timetable;
    TextView teacher;
    FirebaseAuth mAuth;
    public teacher_dashboard() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_teacher_dashboard, container, false);
        add_newstudent = v.findViewById(R.id.add_newstudent);
        homework = v.findViewById(R.id.homework);
        events = v.findViewById(R.id.events);
        upload_notes = v.findViewById(R.id.upload_notes);
        mystudents = v.findViewById(R.id.mystudents);
        timetable = v.findViewById(R.id.timetable);

        teacher = v.findViewById(R.id.teacher);

        mAuth = FirebaseAuth.getInstance();

        teacher.setText("Welcome\n"+mAuth.getCurrentUser().getEmail().toString());

        mystudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), students_list.class);
                startActivity(intent);
            }
        });

        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), time_table_upload.class);
                startActivity(intent);
            }
        });

        add_newstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), add_new_student.class);
                startActivity(intent);
            }
        });

        homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), upload_hw.class);
                startActivity(intent);
            }
        });


        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), add_events.class);
                startActivity(intent);
            }
        });


        upload_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), upload_notes.class);
                startActivity(intent);
            }
        });




        return v;
    }


}