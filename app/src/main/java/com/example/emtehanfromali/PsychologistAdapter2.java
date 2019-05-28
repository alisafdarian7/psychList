package com.example.emtehanfromali;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PsychologistAdapter2 extends RecyclerView.Adapter<PsychologistAdapter2.ViewHolder> {
    private List<Psychologists> psychologistsList;
    Context context;

    public PsychologistAdapter2(List<Psychologists> psychologistsList){
        this.psychologistsList=psychologistsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name2,prof,email,ostan,shahr,address,rooykard,telegram,tel1,tel2,point,details,madrak,id,insta;
        public ImageView photo2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name2=itemView.findViewById(R.id.name2);
            photo2=itemView.findViewById(R.id.photo2);
            prof=itemView.findViewById(R.id.prof);
            email=itemView.findViewById(R.id.email);
            ostan=itemView.findViewById(R.id.ostan);
            shahr=itemView.findViewById(R.id.shahr);
            address=itemView.findViewById(R.id.address);
            rooykard=itemView.findViewById(R.id.rooykard);
            telegram=itemView.findViewById(R.id.telegram);
            tel1=itemView.findViewById(R.id.tel1);
            tel2=itemView.findViewById(R.id.tel2);
            point=itemView.findViewById(R.id.point);
            details=itemView.findViewById(R.id.details);
            madrak=itemView.findViewById(R.id.madrak);
            id=itemView.findViewById(R.id.id);
            insta=itemView.findViewById(R.id.insta);


        }


    }


    @NonNull
    @Override
    public PsychologistAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.complete_psy_profiles,viewGroup,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull PsychologistAdapter2.ViewHolder viewHolder, int i) {
        Psychologists psychologists=psychologistsList.get(i);
        viewHolder.name2.setText(psychologists.getP_name());
        viewHolder.prof.setText(psychologists.getP_prof());
        viewHolder.email.setText(psychologists.getP_email());
        viewHolder.ostan.setText(psychologists.getP_province());
        viewHolder.shahr.setText(psychologists.getP_city());
        viewHolder.address.setText(psychologists.getP_address());
        viewHolder.rooykard.setText(psychologists.getP_rooy());
        viewHolder.telegram.setText(psychologists.getP_telegram());
        viewHolder.tel1.setText(psychologists.getP_telephone1());
        viewHolder.tel2.setText(psychologists.getP_telephone2());
        viewHolder.point.setText(psychologists.getP_point());
        viewHolder.details.setText(psychologists.getP_details());
        viewHolder.madrak.setText(psychologists.getP_cert());
        viewHolder.id.setText(psychologists.getP_ID());
        viewHolder.insta.setText(psychologists.getP_instagram());

        String url=psychologists.getP_image();

        Glide.with(context).load(url).into(viewHolder.photo2);









    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
