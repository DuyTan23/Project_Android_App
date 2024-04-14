package sach;

import java.io.Serializable;
import java.sql.Blob;

public class Sach implements Serializable {
    private int id_sach, id_theloai, id_nhaxb, id_tacgia, nam_xb;
    private String tensach ;
    private float giasach  ;
    private byte[] hinhsach;

    public Sach(int id_sach, String tensach, int id_nhaxb, int id_theloai ,int id_tacgia, int nam_xb, float giasach, byte[] hinhsach) {
        this.id_sach = id_sach;
        this.id_theloai = id_theloai;
        this.id_nhaxb = id_nhaxb;
        this.id_tacgia = id_tacgia;
        this.tensach = tensach;
        this.giasach = giasach;
        this.nam_xb = nam_xb;
        this.hinhsach = hinhsach;
    }

    public int getId_sach() {
        return id_sach;
    }

    public void setId_sach(int id_sach) {
        this.id_sach = id_sach;
    }

    public int getId_theloai() {
        return id_theloai;
    }

    public void setId_theloai(int id_theloai) {
        this.id_theloai = id_theloai;
    }

    public int getId_nhaxb() {
        return id_nhaxb;
    }

    public void setId_nhaxb(int id_nhaxb) {
        this.id_nhaxb = id_nhaxb;
    }

    public int getId_tacgia() {
        return id_tacgia;
    }

    public void setId_tacgia(int id_tacgia) {
        this.id_tacgia = id_tacgia;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public float getGiasach() {
        return giasach;
    }

    public void setGiasach(float giasach) {
        this.giasach = giasach;
    }

    public float getNam_xb() {
        return nam_xb;
    }

    public void setNam_xb(int nam_xb) {
        this.nam_xb = nam_xb;
    }

    public byte[] getHinhsach() {
        return hinhsach;
    }

    public void setHinhsach(byte[] hinhsach) {
        this.hinhsach = hinhsach;
    }
}
