package com.example.app_kesehatan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class cardKelurahanAdapter extends RecyclerView.Adapter<cardKelurahanAdapter.CardViewViewHolder> {

    private ArrayList<dataKesehatan> listKelurahan;

    public cardKelurahanAdapter(ArrayList<dataKesehatan> list){
        this.listKelurahan=list;
    }
    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_kelurahan,viewGroup,false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        dataKesehatan data = listKelurahan.get(position);



        holder.namaKelurahan.setText(data.getKelurahan());

    }






    @Override
    public int getItemCount() {
        return listKelurahan.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
       TextView namaKelurahan;

        CardViewViewHolder(View itemView) {
            super(itemView);
            namaKelurahan = itemView.findViewById(R.id.templateKelurahan);
        }
    }
}
