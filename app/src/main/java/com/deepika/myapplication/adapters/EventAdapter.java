package com.deepika.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deepika.myapplication.R;
import com.deepika.myapplication.models.EventModel;
import com.deepika.myapplication.models.HomeWorkModel;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyHolder>
{
    Context context;
    ArrayList<EventModel> list;

    public EventAdapter(Context context, ArrayList<EventModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.event_list, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  EventAdapter.MyHolder holder, int position) {
        holder.setIsRecyclable(false);
        EventModel eventModel = list.get(position);

        String titl = eventModel.getTitle();
        String descc = eventModel.getDescription();
        String dattte = eventModel.getEventdate();

        holder.title.setText(titl);
        holder.description.setText(descc);
        holder.eventdate.setText(dattte);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends  RecyclerView.ViewHolder{

        TextView title, eventdate, description;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.e_tilte);
            eventdate = itemView.findViewById(R.id.e_date);
            description = itemView.findViewById(R.id.e_desc);
        }
    }
}
