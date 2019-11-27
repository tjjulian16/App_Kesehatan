package com.example.app_kesehatan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditKeluarga extends AppCompatActivity {
    EditText editNamaKeluarga, editAlamat, editKeterangan;
    Spinner editKelurahan, editKecamatan, editStatus;
    Button btnEdit;

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
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateKeluarga(id);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseKesehatan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataKesehatan dataEdit =  dataSnapshot.getValue(DataKesehatan.class);


                String namaKeluarga = dataEdit.getNamaKeluarga();

                String alamat = dataEdit.getAlamat();
                String kelurahan = dataEdit.getKelurahan();
                String keterangan = dataEdit.getKeterangan();
                String status = dataEdit.getStatus();
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

    private boolean UpdateKeluarga(String id){
        DatabaseReference UpdateKeluarga = FirebaseDatabase.getInstance().getReference("kesehatan").child(id);
        String namaKeluarga = editNamaKeluarga.getText().toString().trim();
        String alamat = editAlamat.getText().toString().trim();
        String keterangan = editKeterangan.getText().toString().trim();
        String kecamatan = editKecamatan.getSelectedItem().toString();
        String kelurahan = editKelurahan.getSelectedItem().toString();
        String status = editStatus.getSelectedItem().toString();
        DataKesehatan Keluarga = new DataKesehatan(id,namaKeluarga,kecamatan,kelurahan, status, keterangan, alamat);
        UpdateKeluarga.setValue(Keluarga);
        return true;
    }
}
