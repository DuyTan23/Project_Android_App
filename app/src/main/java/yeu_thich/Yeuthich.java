package yeu_thich;

public class Yeuthich {
    private int id_sach;
    private  int id_ngdung;

    public Yeuthich(int id_sach, int id_ngdung) {
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
}
