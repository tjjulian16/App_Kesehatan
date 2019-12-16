package com.example.app_kesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LihatKelurahan extends AppCompatActivity {
    private RecyclerView rvKelurahan;
    private ImageView homeBtn;
    private ArrayList<DataKesehatan> list = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kelurahan);

        rvKelurahan = findViewById(R.id.recycleKelurahan);
        rvKelurahan.setHasFixedSize(true);
        homeBtn = findViewById(R.id.home);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(LihatKelurahan.this, MenuUtama.class);
                startActivity(home);
            }
        });
        list.addAll(DataKelurahan.getListData());
        showRecyclerList();
    }


    private void showRecyclerList(){
        rvKelurahan.setLayoutManager(new LinearLayoutManager(this));
        CardKelurahanAdapter adapterKelurahan = new CardKelurahanAdapter(list);
        rvKelurahan.setAdapter(adapterKelurahan);


    }


}
