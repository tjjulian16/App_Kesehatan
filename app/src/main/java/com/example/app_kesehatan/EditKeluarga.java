package com.example.app_kesehatan;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EditKeluarga extends AppCompatActivity {
    EditText editNamaKeluarga, editAlamat, editKeterangan;
    Spinner editKelurahan, editKecamatan, editStatus;
    Button btnEdit, btnLokasi;
    private FusedLocationProviderClient fusedLocationClient;
    private final static int REQUEST_LOCATION_PERMISSION = 1;
    Geocoder geocoder;
    List<Address> listAlamat;

    DatabaseReference databaseKesehatan;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_keluarga);
        Bundle bundle = getIntent().getExtras();

//Extract the data…

       final String id = bundle.getString("id");
        databaseKesehatan = FirebaseDatabase.getInstance().getReference().child("kesehatan/"+id);

        editNamaKeluarga = findViewById(R.id.editNamaKeluarga);
        editAlamat = findViewById(R.id.editAlamat);
        editKeterangan = findViewById(R.id.editKeterangan);
        editKelurahan = findViewById(R.id.spnEditKelurahan);
        editKecamatan = findViewById(R.id.spnEditKecamatan);
        editStatus = findViewById(R.id.spnEditStatus);
        btnEdit = findViewById(R.id.buttonEdit);
        btnLokasi = findViewById(R.id.btnLokasiUpdate);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateKeluarga(id);
            }
        });
        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLokasi();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseKesehatan.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataKesehatan dataEdit =  dataSnapshot.getValue(DataKesehatan.class);


                String namaKeluarga = null,alamat = null,kelurahan = null,keterangan =null ,status=null;
                if (dataEdit != null) {
                    namaKeluarga = dataEdit.getNamaKeluarga();

                    alamat = dataEdit.getAlamat();
                    kelurahan = dataEdit.getKelurahan();
                    keterangan = dataEdit.getKeterangan();
                    status = dataEdit.getStatus();
                }

                editKelurahan.setSelection(getIndex(editKelurahan,kelurahan));
                editStatus.setSelection(getIndex(editStatus,status));
                editNamaKeluarga.setText(namaKeluarga);
                editAlamat.setText(alamat);
                editKeterangan.setText(keterangan);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private int getIndex(Spinner spinner, String string ){
        int index=0;

        for(int i=0; i< spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).equals(string)){
                index = i;
            }
        }
        return index;
    }

    private void updateLokasi() {
        //NGECEK DULU APAKAH DIA UDAH ACCEPT BELOM
        if (ContextCompat.checkSelfPermission(EditKeluarga.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //KALO BELOM MASUK KE SINI
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(EditKeluarga.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
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
                                ActivityCompat.requestPermissions(EditKeluarga.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
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
                ActivityCompat.requestPermissions(EditKeluarga.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
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

                                geocoder = new Geocoder(EditKeluarga.this, Locale.getDefault());

                                try {
                                    listAlamat = geocoder.getFromLocation(latitude, longitude, 1);
                                    String alamat = listAlamat.get(0).getAddressLine(0);
                                    editAlamat.setText(alamat);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    });
        }
    }

    private void UpdateKeluarga(String id){
        DatabaseReference UpdateKeluarga = FirebaseDatabase.getInstance().getReference("kesehatan").child(id);
        String namaKeluarga = editNamaKeluarga.getText().toString().trim();
        String alamat = editAlamat.getText().toString().trim();
        String keterangan = editKeterangan.getText().toString().trim();
        String kecamatan = editKecamatan.getSelectedItem().toString();
        String kelurahan = editKelurahan.getSelectedItem().toString();
        String status = editStatus.getSelectedItem().toString();




        DataKesehatan Keluarga = new DataKesehatan(id,namaKeluarga,kecamatan,kelurahan, status, keterangan, alamat);
        UpdateKeluarga.setValue(Keluarga);
        Intent i = new Intent(EditKeluarga.this, EditBerhasil.class);
        EditKeluarga.this.startActivity(i);


    }
}
