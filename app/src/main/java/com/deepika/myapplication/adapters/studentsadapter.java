package com.deepika.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepika.myapplication.R;
import com.deepika.myapplication.models.StudentsModel;

import java.util.ArrayList;

public class studentsadapter extends RecyclerView.Adapter<studentsadapter.MyViewHolder> {


    public studentsadapter(Context context, ArrayList<StudentsModel> list) {
        this.context = context;
        this.list = list;
    }

    Context context;
    ArrayList<StudentsModel> list;


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.students, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull studentsadapter.MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        StudentsModel studentsModel = list.get(position);

        String uid = studentsModel.getUid();
        String id = studentsModel.getStudent_id();
        String name = studentsModel.getName();
        String parent = studentsModel.getParent();

        holder.uid.setText(uid);
        holder.id.setText(id);
        holder.name.setText(name);
        holder.parent.setText(parent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, parent, id, uid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.student_name);
            parent = itemView.findViewById(R.id.student_parent);
            uid = itemView.findViewById(R.id.student_uid);
            id = itemView.findViewById(R.id.student_id);

        }
    }
}
