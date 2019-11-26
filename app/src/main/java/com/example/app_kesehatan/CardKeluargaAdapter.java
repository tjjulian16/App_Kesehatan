package com.example.app_kesehatan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CardKeluargaAdapter extends RecyclerView.Adapter<CardKeluargaAdapter.CardViewViewHolder> {

    private ArrayList<DataKesehatan> listKeluarga;
    private Context context;

    public CardKeluargaAdapter(Context context, ArrayList<DataKesehatan> list){
        this.listKeluarga=list;
        this.context = context;
    }
    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_keluarga,viewGroup,false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        DataKesehatan data = listKeluarga.get(position);

        holder.namaKeluarga.setText(data.getNamaKeluarga());
        holder.alamat.setText(data.getAlamat());

        holder.namaKeluarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.namaKeluarga.getContext(), listKeluarga.get(holder.getAdapterPosition()).getNamaKeluarga(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listKeluarga.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView namaKeluarga,alamat;

        CardViewViewHolder(View itemView) {
            super(itemView);
            namaKeluarga = itemView.findViewById(R.id.nama_keluarga);
            alamat = itemView.findViewById(R.id.alamat);
        }
    }
}
