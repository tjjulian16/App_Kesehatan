package com.example.app_kesehatan;
import java.util.ArrayList;
public class dataKelurahan {

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

    static ArrayList<dataKesehatan> getListData() {
        ArrayList<dataKesehatan> list = new ArrayList<>();
        for (int position = 0; position < namaKelurahan.length; position++) {
            dataKesehatan data = new dataKesehatan();
            data.setKelurahan(namaKelurahan[position]);

            list.add(data);
        }
        return list;
    }
}