package com.example.app_kesehatan;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class lihatKelurahan extends AppCompatActivity {
    private RecyclerView rvKelurahan;
    private ArrayList<dataKesehatan> list = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kelurahan);

        rvKelurahan = findViewById(R.id.recycleKelurahan);
        rvKelurahan.setHasFixedSize(true);

        list.addAll(dataKelurahan.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvKelurahan.setLayoutManager(new LinearLayoutManager(this));
        listKelurahanAdapter adapterKelurahan = new listKelurahanAdapter(list);
        rvKelurahan.setAdapter(adapterKelurahan);

        adapterKelurahan.setOnItemClickCallback(new listKelurahanAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(dataKesehatan data) {
                showKelurahan(data);
            }
        }); 
    }

    private void showKelurahan(dataKesehatan datakesehatan) {
        Toast.makeText(this, "Kamu memilih " + datakesehatan.getKelurahan(), Toast.LENGTH_SHORT).show();
    }
}
