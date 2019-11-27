package com.example.app_kesehatan;
import java.util.ArrayList;
public class DataKelurahan {

    private static String[] namaKelurahan = {
            "Dinoyo",
            "Jatimulyo",
            "Ketawanggede",
            "Lowokwaru",
            "Merjosari",
            "Mojolangu",
            "Sumbersari",
            "Tasikmadu",
            "Tlogomas",
            "Tulusrejo",
            "Tunggulwulung",
            "Tunjungsekar"
    };

    public static String[] getNamaKelurahan() {
        return namaKelurahan;
    }

    static ArrayList<DataKesehatan> getListData() {
        ArrayList<DataKesehatan> list = new ArrayList<>();
        for (int position = 0; position < namaKelurahan.length; position++) {
            DataKesehatan data = new DataKesehatan();
            data.setKelurahan(namaKelurahan[position]);

            list.add(data);
        }
        return list;
    }
}
