package giohang;

import java.util.Date;

public class Giohang {
    private int id_giohang,id_sach, id_ngdung;

    public Giohang(int id_giohang,int id_sach, int id_ngdung) {
        this.id_giohang = id_giohang;
        this.id_sach = id_sach;
        this.id_ngdung = id_ngdung;
    }

    public int getId_sach() {
        return id_sach;
    }

    public void setId_sach(int id_sach) {
        this.id_sach = id_sach;
    }

    public int getId_ngdung() {
        return id_ngdung;
    }

    public void setId_ngdung(int id_ngdung) {
        this.id_ngdung = id_ngdung;
    }

    public int getId_giohang() {
        return id_giohang;
    }

    public void setId_giohang(int id_giohang) {
        this.id_giohang = id_giohang;
    }
}
