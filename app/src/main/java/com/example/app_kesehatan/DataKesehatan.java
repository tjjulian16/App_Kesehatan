package com.example.app_kesehatan;

public class DataKesehatan {

    String namaKeluarga,kecamatan,kelurahan,status,keterangan,alamat,id;
    public DataKesehatan(){

    }

    public DataKesehatan(String id, String namaKeluarga, String kecamatan, String kelurahan, String status, String keterangan, String alamat) {
        this.id = id;
        this.namaKeluarga = namaKeluarga;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.status = status;
        this.keterangan = keterangan;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNamaKeluarga(String namaKeluarga) {
        this.namaKeluarga = namaKeluarga;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setAlamat(String alamat) {
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



