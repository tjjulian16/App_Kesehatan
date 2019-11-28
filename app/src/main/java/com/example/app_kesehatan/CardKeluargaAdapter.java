package com.example.app_kesehatan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.app_kesehatan.DataKesehatan;
import com.example.app_kesehatan.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, final int position) {
        DataKesehatan data = listKeluarga.get(position);

        holder.namaKeluarga.setText(data.getNamaKeluarga());
        holder.alamat.setText(data.getAlamat());
        holder.status.setText(data.getStatus());

        final String id = listKeluarga.get(position).getId();

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DeleteKeluarga(id);
                notifyItemRemoved(position);
                Toast.makeText(holder.namaKeluarga.getContext(), "Berhasil Menghapus " + listKeluarga.get(holder.getAdapterPosition()).getNamaKeluarga() , Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), EditKeluarga.class);

//Create the bundle
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putString("id", id);

//Add the bundle to the intent
                i.putExtras(bundle);

//Fire that second activity

               view.getContext().startActivity(i);
            }
        });

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), LihatProfilKeluarga.class);

//Create the bundle
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putString("id", id);

//Add the bundle to the intent
                i.putExtras(bundle);

//Fire that second activity

                view.getContext().startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return listKeluarga.size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView namaKeluarga,alamat, status;
        Button btnView, btnHapus,btnEdit;

        CardViewViewHolder(View itemView) {
            super(itemView);
            namaKeluarga = itemView.findViewById(R.id.nama_keluarga);
            status = itemView.findViewById(R.id.isi_status);
            alamat = itemView.findViewById(R.id.alamat);
            btnHapus = itemView.findViewById(R.id.btnHapus);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnView = itemView.findViewById(R.id.btnLihat);
        }
    }


    private boolean DeleteKeluarga(String id){
        //mendapat spesifik keluarga

        DatabaseReference DeleteKeluarga = FirebaseDatabase.getInstance().getReference("kesehatan").child(id);
        //menghapus
        DeleteKeluarga.removeValue();

        return true;
    }
}