package com.example.app_kesehatan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MenuUtama extends AppCompatActivity {

    Button btnLogout;
     ImageView menuIsiData,menuLihatData;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        btnLogout = findViewById(R.id.btnLogout);
        menuIsiData = findViewById(R.id.masukdataimg);
        menuLihatData = findViewById(R.id.lihatdataimg);

        menuIsiData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuUtama.this, formIsiData.class);
                startActivity(i);
            }
        });

     /*   menuLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuUtama.this, lihatData.class);
                startActivity(i);
            }
        }); */


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(MenuUtama.this, MainActivity.class);
                startActivity(intToMain);
            }
        });
    }
}
