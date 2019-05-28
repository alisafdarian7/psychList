package com.example.emtehanfromali;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PsychologistAdapter extends RecyclerView.Adapter<PsychologistAdapter.ViewHolder> {
    private List<Psychologists> psychologistsList;
    Context context;

    public PsychologistAdapter(List<Psychologists> psychologistsList,Context context){
        this.psychologistsList=psychologistsList;
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nameOfPsy;
        public ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfPsy=itemView.findViewById(R.id.name);
            photo=itemView.findViewById(R.id.photo);

        }


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.psy_profile,viewGroup,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Psychologists psychologists=psychologistsList.get(i);
        viewHolder.nameOfPsy.setText(psychologists.getP_name());
        String url=psychologists.getP_image();

        Glide.with(context).load(url).into(viewHolder.photo);









    }

    @Override
    public int getItemCount() {
        return psychologistsList.size();
    }
}
