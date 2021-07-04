package com.deepika.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepika.myapplication.R;
import com.deepika.myapplication.models.NoticeModel;

import java.util.ArrayList;

public class noticeadapater extends RecyclerView.Adapter<noticeadapater.MyViewHolder> {


    public noticeadapater(Context context, ArrayList<NoticeModel> list) {
        this.context = context;
        this.list = list;
    }

    Context context;
    ArrayList<NoticeModel> list;


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notice, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull noticeadapater.MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);

        NoticeModel noticeModel = list.get(position);

        String desc = noticeModel.getDescription();
        String subject = noticeModel.getEventSubject();
        String date = noticeModel.getEventdate();
        String title = noticeModel.getTitle();

        holder.date.setText(date);
        holder.subject.setText(subject);
        holder.desc.setText(desc);
        holder.tilte.setText(title);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        TextView tilte, desc, date, subject;
        public MyViewHolder(View itemView) {
            super(itemView);
            tilte = itemView.findViewById(R.id.title_notice);
            desc = itemView.findViewById(R.id.desc_notice);
            date = itemView.findViewById(R.id.date_notice);
            subject = itemView.findViewById(R.id.subject_notice);

        }
    }
}
