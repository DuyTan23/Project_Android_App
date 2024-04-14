package tacgia;

public class Tacgia {
    private int id_tacgia;
    private String ten_tacgia;
    private String ngaysinh_tacgia;

    public Tacgia(int id_tacgia, String ten_tacgia, String ngaysinh_tacgia) {
        this.id_tacgia = id_tacgia;
        this.ten_tacgia = ten_tacgia;
        this.ngaysinh_tacgia = ngaysinh_tacgia;
    }

    public int getId_tacgia() {
        return id_tacgia;
    }

    public void setId_tacgia(int id_tacgia) {
        this.id_tacgia = id_tacgia;
    }

    public String getTen_tacgia() {
        return ten_tacgia;
    }

    public void setTen_tacgia(String ten_tacgia) {
        this.ten_tacgia = ten_tacgia;
    }

    public String getNgaysinh_tacgia() {
        return ngaysinh_tacgia;
    }

    public void setNgaysinh_tacgia(String ngaysinh_tacgia) {
        this.ngaysinh_tacgia = ngaysinh_tacgia;
    }
}
