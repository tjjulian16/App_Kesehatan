package com.example.app_kesehatan;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class inputBerhasil extends AppCompatActivity {
    ImageView kembaliMenuUtama;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil_input);
        kembaliMenuUtama = findViewById(R.id.btnKembali);

        kembaliMenuUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(inputBerhasil.this, formIsiData.class);
                startActivity(i);
            }
        });
    }


    }
