package com.example.app_kesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class lihatKecamatan extends AppCompatActivity {

    RelativeLayout buttonKecamatan;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kecamatan);

        buttonKecamatan = findViewById(R.id.btnKecamatanLowokwaru);

        buttonKecamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(lihatKecamatan.this, lihatKelurahan.class);
                startActivity(i);
            }
        });
    }
}
