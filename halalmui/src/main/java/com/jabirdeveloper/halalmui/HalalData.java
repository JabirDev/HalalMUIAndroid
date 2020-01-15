package com.jabirdeveloper.halalmui;

public class HalalData {
    private String namaProduk, namaProdusen, tanggalValid, nomorSertifikat;

    public HalalData() {
    }

    public HalalData(String namaProduk, String namaProdusen, String tanggalValid, String nomorSertifikat) {
        this.namaProduk = namaProduk;
        this.namaProdusen = namaProdusen;
        this.tanggalValid = tanggalValid;
        this.nomorSertifikat = nomorSertifikat;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public String getNamaProdusen() {
        return namaProdusen;
    }

    public String getTanggalValid() {
        return tanggalValid;
    }

    public String getNomorSertifikat() {
        return nomorSertifikat;
    }
}
