package com.example.app_kesehatan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class listKelurahanAdapter extends RecyclerView.Adapter<listKelurahanAdapter.ListViewHolder> {

    private ArrayList<dataKesehatan> listKelurahan;

    public listKelurahanAdapter(ArrayList<dataKesehatan> list){
        this.listKelurahan = list;
    }
    private OnItemClickCallback onItemClickCallback;

    public class ListViewHolder extends RecyclerView.ViewHolder {


        TextView  namaKelurahan;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            namaKelurahan = itemView.findViewById(R.id.templateKelurahan);

        }
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_lihat_kelurahan, viewGroup, false);
        return new ListViewHolder(view);

    }


    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        dataKesehatan data = listKelurahan.get(position);


        holder.namaKelurahan.setText(data.getKelurahan());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listKelurahan.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKelurahan.size();
    }



    public interface OnItemClickCallback {
        void onItemClicked(dataKesehatan data);
    }
}
