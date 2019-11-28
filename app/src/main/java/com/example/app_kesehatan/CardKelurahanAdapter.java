package com.example.app_kesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CardKelurahanAdapter extends RecyclerView.Adapter<CardKelurahanAdapter.CardViewViewHolder> {

    private ArrayList<DataKesehatan> listKelurahan;

    public CardKelurahanAdapter(ArrayList<DataKesehatan> list){
        this.listKelurahan=list;
    }
    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_kelurahan,viewGroup,false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, final int position) {
        DataKesehatan data = listKelurahan.get(position);



        holder.namaKelurahan.setText(data.getKelurahan());

        holder.namaKelurahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.namaKelurahan.getContext(), listKelurahan.get(holder.getAdapterPosition()).getKelurahan(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), LihatKeluarga.class);
               final String kelurahan = listKelurahan.get(position).getKelurahan();
                //Create the bundle
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putString("kelurahan", kelurahan);

//Add the bundle to the intent
                i.putExtras(bundle);
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listKelurahan.size();
    }

     class CardViewViewHolder extends RecyclerView.ViewHolder {
       TextView namaKelurahan;

        CardViewViewHolder(View itemView) {
            super(itemView);
            namaKelurahan = itemView.findViewById(R.id.templateKelurahan);


        }
    }
}
