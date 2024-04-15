package com.example.tugas6_listview;

public class Mhs {
    String NRP;
    String Nama;
    String NoHP;
    String Image;

    public Mhs(String NRP, String nama, String noHP, String Image) {
        this.NRP = NRP;
        this.Nama = nama;
        this.NoHP = noHP;
        this.Image = Image;
    }

    public String getNRP() {
        return this.NRP;
    }

    public void setNRP(String NRP) {
        this.NRP = NRP;
    }

    public String getNama() {
        return this.Nama;
    }

    public void setNama(String nama) {
        this.Nama = nama;
    }

    public String getNoHP() {
        return this.NoHP;
    }

    public void setNoHP(String noHP) {
        this.NoHP = noHP;
    }

    public String getImage() {
        return this.Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }
}
