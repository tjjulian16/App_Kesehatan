package com.example.app_kesehatan;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class FormIsiData extends AppCompatActivity {

    EditText inputNamaKeluarga, inputAlamat, inputKeterangan;
    Spinner spnKelurahan, spnKecamatan, spnStatus;
    Button buttonSimpan, buttonLokasi;
    private FusedLocationProviderClient fusedLocationClient;
    private final static int REQUEST_LOCATION_PERMISSION = 1;
    Geocoder geocoder;
    List<Address> listAlamat;

    DatabaseReference databaseKesehatan;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_isi_data);

        databaseKesehatan = FirebaseDatabase.getInstance().getReference("kesehatan");
        inputNamaKeluarga = findViewById(R.id.inputNamaKeluarga);
        inputAlamat = findViewById(R.id.inputAlamat);
        inputKeterangan = findViewById(R.id.inputKeterangan);
        spnKecamatan = findViewById(R.id.spnKecamatan);
        spnKelurahan = findViewById(R.id.spnKelurahan);
        spnStatus = findViewById(R.id.spnStatus);
        buttonSimpan = findViewById(R.id.buttonSimpan);
        buttonLokasi = findViewById(R.id.btnLokasi);
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahDataKesehatan();
            }
        });
        buttonLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLokasi();
            }
        });

    }

    private void getLokasi() {
        //NGECEK DULU APAKAH DIA UDAH ACCEPT BELOM
        if (ContextCompat.checkSelfPermission(FormIsiData.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //KALO BELOM MASUK KE SINI
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(FormIsiData.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                //KALO DISINI ITU INTINYA BUAT PENJELASAN, APP NYA ITU MAU MINTA PERMISSION APA SIH GAN
                // BAKAL MUNCUL KALO USER NYA NOLAK BEBERAPA KALI PERMISSION NYA

                new AlertDialog.Builder(this).setTitle("HAI, TOLONG DONG")
                        .setMessage("KAMI CUMA MAU MINTA LOKASI DOANG")
                        .setPositiveButton("OK BRO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(FormIsiData.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        REQUEST_LOCATION_PERMISSION);
                            }
                        }).setNegativeButton("NGGA DEH", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
            } else {
                // No explanation needed; request the permission
                // (INI TUH INTINYA DIA MINTA PERMISSION KE USER TERKAIT LOCATION ACCESS)
                ActivityCompat.requestPermissions(FormIsiData.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_PERMISSION);


            }
        } else {
            // Permission has already been granted
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object

                                Double latitude = location.getLatitude();
                                Double longitude = location.getLongitude();

                                geocoder = new Geocoder(FormIsiData.this, Locale.getDefault());

                                try {
                                    listAlamat = geocoder.getFromLocation(latitude, longitude, 1);
                                    String alamat = listAlamat.get(0).getAddressLine(0);
                                    inputAlamat.setText(alamat);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    });
        }
    }



    private void tambahDataKesehatan(){
        String namaKeluarga = inputNamaKeluarga.getText().toString().trim();
        String alamat = inputAlamat.getText().toString().trim();
            String keterangan = inputKeterangan.getText().toString().trim();
            String kecamatan = spnKecamatan.getSelectedItem().toString();
            String kelurahan = spnKelurahan.getSelectedItem().toString();
            String status = spnStatus.getSelectedItem().toString();

            if(!(namaKeluarga.isEmpty() && alamat.isEmpty()) ){
               String id =   databaseKesehatan.push().getKey();

               DataKesehatan data = new DataKesehatan(id,namaKeluarga,kecamatan,kelurahan,status,keterangan,alamat);

               databaseKesehatan.child(id).setValue(data);

                startActivity(new Intent(FormIsiData.this, InputBerhasil.class));
            }
            else if (namaKeluarga.isEmpty()){
                inputNamaKeluarga.setError("Harap Masukkan Nama Keluarga");
            }
            else if(alamat.isEmpty()){
                inputAlamat.setError("Harap Masukkan Alamat Keluarga");
            }
            else{
                inputNamaKeluarga.setError("Harap Masukkan Nama Keluarga");
                inputAlamat.setError("Harap Masukkan Alamat Keluarga");
            }

        }
}
