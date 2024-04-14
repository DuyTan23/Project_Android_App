package theloai;

public class Theloai {
    int id_theloai;
    String ten_theloai;
    String mota_theloai;

    public Theloai(int id_theloai, String ten_theloai, String mota_theloai) {

        this.id_theloai = id_theloai;
        this.ten_theloai = ten_theloai;
        this.mota_theloai = mota_theloai;
    }

    public int getId_theloai() {
        return id_theloai;
    }

    public void setId_theloai(int id_theloai) {
        this.id_theloai = id_theloai;
    }

    public String getTen_theloai() {
        return ten_theloai;
    }

    public void setTen_theloai(String ten_theloai) {
        this.ten_theloai = ten_theloai;
    }

    public String getMota_theloai() {
        return mota_theloai;
    }

    public void setMota_theloai(String mota_theloai) {
        this.mota_theloai = mota_theloai;
    }
}
