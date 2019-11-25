package com.example.app_kesehatan;

public class dataKesehatan {

    String namaKeluarga,kecamatan,kelurahan,status,keterangan,alamat;
    public dataKesehatan(){

    }

    public dataKesehatan(String namaKeluarga, String kecamatan, String kelurahan, String status, String keterangan, String alamat) {
        this.namaKeluarga = namaKeluarga;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.status = status;
        this.keterangan = keterangan;
        this.alamat = alamat;
    }

    public String getNamaKeluarga() {
        return namaKeluarga;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public String getStatus() {
        return status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getAlamat() {
        return alamat;
    }
}



