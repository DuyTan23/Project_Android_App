package quangcao_index;

public class QuangCao {
    private int img_qc;
    private String tieude_qc;
    private String noidung_qc;

    public QuangCao(int img_qc, String tieude_qc, String noidung_qc) {
        this.img_qc = img_qc;
        this.tieude_qc = tieude_qc;
        this.noidung_qc = noidung_qc;
    }

    public int getImg_qc() {
        return img_qc;
    }

    public void setImg_qc(int img_qc) {
        this.img_qc = img_qc;
    }

    public String getTieude_qc() {
        return tieude_qc;
    }

    public void setTieude_qc(String tieude_qc) {
        this.tieude_qc = tieude_qc;
    }

    public String getNoidung_qc() {
        return noidung_qc;
    }

    public void setNoidung_qc(String noidung_qc) {
        this.noidung_qc = noidung_qc;
    }
}
