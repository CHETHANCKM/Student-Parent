package com.deepika.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.deepika.myapplication.Notes;
import com.deepika.myapplication.R;
import com.deepika.myapplication.models.NotesModel;
import com.deepika.myapplication.notes.Webpage;
import com.deepika.myapplication.notes.subject_notes;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    Context context;
    ArrayList<NotesModel> list;

    public NotesAdapter(Context context, ArrayList<NotesModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notes_pdf, parent, false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  NotesAdapter.MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);

        NotesModel notes = list.get(position);

        String url = notes.getFileurl();
        String title = notes.getTilte();
        String desc = notes.getDescription();


        holder.notes_tile.setText(title);
        holder.notes_desc.setText(desc);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+url, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), Webpage.class);
                intent.putExtra("url", url);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView notes_tile, notes_desc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            notes_tile = itemView.findViewById(R.id.notes_title);
            notes_desc = itemView.findViewById(R.id.notes_description);
            cardView = itemView.findViewById(R.id.card_open);
        }
    }
}
