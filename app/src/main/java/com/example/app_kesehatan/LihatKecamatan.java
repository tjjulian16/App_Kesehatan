package com.example.app_kesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class LihatKecamatan extends AppCompatActivity {

    RelativeLayout buttonKecamatan;
    ImageView homeBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kecamatan);
        homeBtn = findViewById(R.id.home);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(LihatKecamatan.this, MenuUtama.class);
                startActivity(home);
            }
        });
        buttonKecamatan = findViewById(R.id.btnKecamatanLowokwaru);

        buttonKecamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LihatKecamatan.this, LihatKelurahan.class);
                startActivity(i);
            }
        });
    }
}
