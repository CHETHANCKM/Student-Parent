package com.deepika.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepika.myapplication.R;
import com.deepika.myapplication.models.HomeWorkModel;

import java.util.ArrayList;

public class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkAdapter.MyHolder> {

    Context context;

    public HomeWorkAdapter(Context context, ArrayList<HomeWorkModel> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<HomeWorkModel> list;


    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.homeworkpage, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeWorkAdapter.MyHolder holder, int position) {
        holder.setIsRecyclable(false);
        HomeWorkModel homeWorkModel = list.get(position);

        String tilte1 = homeWorkModel.getTitle();
        String date1 = homeWorkModel.getDue_Date();
        String subject1 = homeWorkModel.getSubject();
        String descr1 = homeWorkModel.getDescription();

        holder.subject.setText(subject1);
        holder.tilte.setText(tilte1);
        holder.date.setText(date1);
        holder.desc.setText(descr1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView tilte, desc, date, subject;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tilte = itemView.findViewById(R.id.hw_tilte);
            desc = itemView.findViewById(R.id.hw_description);
            date = itemView.findViewById(R.id.hw_due_date);
            subject = itemView.findViewById(R.id.hw_subject_name);
        }
    }
}
