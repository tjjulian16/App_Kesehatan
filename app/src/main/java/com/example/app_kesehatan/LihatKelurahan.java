package com.example.app_kesehatan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LihatKelurahan extends AppCompatActivity {
    private RecyclerView rvKelurahan;
    private ArrayList<DataKesehatan> list = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kelurahan);

        rvKelurahan = findViewById(R.id.recycleKelurahan);
        rvKelurahan.setHasFixedSize(true);

        list.addAll(DataKelurahan.getListData());
        showRecyclerList();
    }


    private void showRecyclerList(){
        rvKelurahan.setLayoutManager(new LinearLayoutManager(this));
        CardKelurahanAdapter adapterKelurahan = new CardKelurahanAdapter(list);
        rvKelurahan.setAdapter(adapterKelurahan);


    }

  /*  private void showKelurahan(DataKesehatan datakesehatan) {
        Toast.makeText(this, "Kamu memilih " + datakesehatan.getKelurahan(), Toast.LENGTH_SHORT).show();
    } */
}
