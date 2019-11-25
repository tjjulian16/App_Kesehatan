package com.example.app_kesehatan;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class formIsiData extends AppCompatActivity {

    EditText inputNamaKeluarga, inputAlamat, inputKeterangan;
    Spinner spnKelurahan, spnKecamatan, spnStatus;
    Button buttonSimpan;

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

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahDataKesehatan();
            }
        });

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

               dataKesehatan data = new dataKesehatan(namaKeluarga,kecamatan,kelurahan,status,keterangan,alamat);

               databaseKesehatan.child(id).setValue(data);

                Toast.makeText(this,"Tambah Data Kesehatan Berhasil", Toast.LENGTH_LONG).show();
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
