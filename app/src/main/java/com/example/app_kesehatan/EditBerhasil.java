package com.example.app_kesehatan;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditBerhasil extends AppCompatActivity {

    Button btnback;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil_edit);
        btnback = findViewById(R.id.btnbacktodata);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i = new Intent(EditBerhasil.this, LihatKelurahan.class);
                view.getContext().startActivity(i);
            }
        });
    }


}

