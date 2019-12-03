package com.example.app_kesehatan;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LihatKeluarga extends AppCompatActivity {

    DatabaseReference databaseKesehatan;


    private RecyclerView rvKeluarga;
    private ArrayList<DataKesehatan> listKeluarga = new ArrayList<>();
    private CardKeluargaAdapter cardKeluargaAdapter;
    String kel;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_keluarga);
        Bundle bundle = getIntent().getExtras();
        kel = bundle.getString("kelurahan");
        rvKeluarga =  findViewById(R.id.recycleKeluarga);
        rvKeluarga.setHasFixedSize(true);
        rvKeluarga.setLayoutManager(new LinearLayoutManager(this));
        new Keluarga().execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        kel = bundle.getString("kelurahan");
        new Keluarga().execute();

    }

    class Keluarga extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            Query query = FirebaseDatabase.getInstance().getReference("kesehatan")
                    .orderByChild("kelurahan").equalTo(kel);

            query.addValueEventListener(new ValueEventListener() {
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
            return kel;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.container_keluarga).setVisibility(View.GONE);

        }

        @Override
        protected void onPostExecute(String kelurahan) {
            super.onPostExecute(kelurahan);

           if(!listKeluarga.isEmpty()){
                Toast.makeText(LihatKeluarga.this, "Menampilkan Data "+kelurahan, Toast.LENGTH_LONG).show();
               findViewById(R.id.loader).setVisibility(View.GONE);
               findViewById(R.id.container_keluarga).setVisibility(View.VISIBLE);
            }
            else{
               findViewById(R.id.loader).setVisibility(View.GONE);
               findViewById(R.id.container_keluarga).setVisibility(View.GONE);
                Toast.makeText(LihatKeluarga.this, "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
            }



        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        kel = bundle.getString("kelurahan");
       new Keluarga().execute();
    }


    }
