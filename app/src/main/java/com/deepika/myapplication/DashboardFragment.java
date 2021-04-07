package com.deepika.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class DashboardFragment extends Fragment {
    TextView studentw;
    FirebaseAuth mAuth;
    CardView s_notes,even_s, homework, tt, fees;


    public DashboardFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        studentw = v.findViewById(R.id.student);
        mAuth = FirebaseAuth.getInstance();
        s_notes = v.findViewById(R.id.s_notes);
        even_s = v.findViewById(R.id.even_s);
        homework = v.findViewById(R.id.home_works);
        studentw.setText("Welcome\n"+mAuth.getCurrentUser().getEmail().toString());

        s_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Notes.class);
                startActivity(intent);
            }
        });


        homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), homework.class);
                startActivity(intent);
            }
        });

        even_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), events.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
