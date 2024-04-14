package nhaxb;

import android.content.Intent;

public class Nhaxb {

    //id_nhaxb INTEGER PRIMARY KEY AUTOINCREMENT,ten_nhaxb TEXT,sdt_nhaxb VARCHAR(20), diachi_nhaxb TEXT
    private int id_nhaxb;
    private  String ten_nhaxb, sdt_nhaxb, diachi_nhaxb;

    public Nhaxb(int id_nhaxb, String ten_nhaxb, String sdt_nhaxb, String diachi_nhaxb) {
        this.id_nhaxb = id_nhaxb;
        this.ten_nhaxb = ten_nhaxb;
        this.sdt_nhaxb = sdt_nhaxb;
        this.diachi_nhaxb = diachi_nhaxb;
    }

    public int getId_nhaxb() {
        return id_nhaxb;
    }

    public void setId_nhaxb(int id_nhaxb) {
        this.id_nhaxb = id_nhaxb;
    }

    public String getTen_nhaxb() {
        return ten_nhaxb;
    }

    public void setTen_nhaxb(String ten_nhaxb) {
        this.ten_nhaxb = ten_nhaxb;
    }

    public String getSdt_nhaxb() {
        return sdt_nhaxb;
    }

    public void setSdt_nhaxb(String sdt_nhaxb) {
        this.sdt_nhaxb = sdt_nhaxb;
    }

    public String getDiachi_nhaxb() {
        return diachi_nhaxb;
    }

    public void setDiachi_nhaxb(String diachi_nhaxb) {
        this.diachi_nhaxb = diachi_nhaxb;
    }
}
