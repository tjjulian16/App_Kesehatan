package com.example.app_kesehatan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BuatData extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "kesehatan";
    public BuatData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String buatuser = "create table user(id integer primary key, username String null, password String null);";
        Log.d("Data", "onCreate"+buatuser);
        db.execSQL(buatuser);
        String insertuser = "INSERT INTO user (id, username, password) VALUES ('1', 'admin', 'admin');";
        db.execSQL(insertuser);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
