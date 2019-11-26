package com.example.app_kesehatan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LihatKeluarga extends AppCompatActivity {

    DatabaseReference databaseKesehatan;

    private RecyclerView rvKeluarga;
    private ArrayList<DataKesehatan> listKeluarga = new ArrayList<>();
    private CardKeluargaAdapter cardKeluargaAdapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_keluarga);

        databaseKesehatan = FirebaseDatabase.getInstance().getReference().child("kesehatan");
        databaseKesehatan.keepSynced(true);
        rvKeluarga =  findViewById(R.id.recycleKeluarga);
        rvKeluarga.setHasFixedSize(true);
        rvKeluarga.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseKesehatan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listKeluarga.clear();

                for(DataSnapshot dataKeluarga : dataSnapshot.getChildren()){
                    DataKesehatan data = dataKeluarga.getValue(DataKesehatan.class);
                    listKeluarga.add(data);
                }
                cardKeluargaAdapter = new CardKeluargaAdapter(LihatKeluarga.this, listKeluarga);
                rvKeluarga.setAdapter(cardKeluargaAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    }
