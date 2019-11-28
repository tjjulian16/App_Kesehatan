package com.example.app_kesehatan;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

public class LihatProfilKeluarga extends FragmentActivity implements OnMapReadyCallback {

   private TextView tampilNamakeluarga, tampilKelurahan, tampilKecamatan, tampilAlamat, tampilStatus, tampilKeterangan;
    private GoogleMap mMap;
    private String getAlamat;
    private  DatabaseReference databaseKesehatan;
    SupportMapFragment mapFragment;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_keluarga);
        Bundle bundle = getIntent().getExtras();

//Extract the data…

        final String id = bundle.getString("id");
        databaseKesehatan = FirebaseDatabase.getInstance().getReference().child("kesehatan/"+id);

        tampilNamakeluarga = findViewById(R.id.isi_nama_keluarga);
        tampilAlamat = findViewById(R.id.isi_alamat);
        tampilKeterangan = findViewById(R.id.isi_ket);
        tampilKelurahan = findViewById(R.id.isi_kelurahan);
        tampilKecamatan = findViewById(R.id.isi_kecamatan);
        tampilStatus = findViewById(R.id.isi_status);




    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseKesehatan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataKesehatan dataProfil =  dataSnapshot.getValue(DataKesehatan.class);


                String namaKeluarga = dataProfil.getNamaKeluarga();
                String kecamatan = dataProfil.getKecamatan();
                String alamat = dataProfil.getAlamat();
                String kelurahan = dataProfil.getKelurahan();
                String keterangan = dataProfil.getKeterangan();
                String status = dataProfil.getStatus();
                tampilNamakeluarga.setText(namaKeluarga);
                tampilKecamatan.setText(kecamatan);
                tampilKelurahan.setText(kelurahan);
                tampilAlamat.setText(alamat);
                tampilStatus.setText(status);
                tampilKeterangan.setText(keterangan);
                getAlamat = alamat;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_keluarga);
        mapFragment.getMapAsync(this);
    }

            @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String StrAddress = "Jalan Veteran No.8, Malang, Indonesia";

                Geocoder coder = new Geocoder(this);
                List<Address> address;


                try {
                    address = coder.getFromLocationName(StrAddress, 1);
                    if (address != null && address.size()!=0) {

                        Address location = address.get(0);

                        LatLng alamat = new  LatLng(location.getLatitude(), location.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(alamat).title("Lokasi Keluarga"));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(alamat,15.0f),5000, null);
                    }
                    else{
                        Toast.makeText(this, "Alamat Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                    }




                } catch (IOException e) {
                    e.printStackTrace();
                }


                //tambah marker di lokasi alamat keluarga tersebut

    }
}
