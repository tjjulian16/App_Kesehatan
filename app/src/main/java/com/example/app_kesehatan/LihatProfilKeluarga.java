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
    private LatLng koor;
    private Double latitude, longitude;
    private  DatabaseReference databaseKesehatan;
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
        latitude = 28.6139391;
        longitude = 77.2068325;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_keluarga);
        mapFragment.getMapAsync(this);


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

             //  LatLng koor = getKoordinat(alamat);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public LatLng getKoordinat(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng koor = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            koor = new LatLng( (location.getLatitude() * 1E6),
                    (location.getLongitude() * 1E6));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return koor;
    }
            @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       //tambah marker di lokasi alamat keluarga tersebut
        LatLng alamat = new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(alamat).title("Lokasi Keluarga"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(alamat));
    }
}
