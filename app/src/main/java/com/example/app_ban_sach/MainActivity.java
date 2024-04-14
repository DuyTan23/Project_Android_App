package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import quangcao_index.QuangCao;
import quangcao_index.QuangCaoAdapter;


public class MainActivity extends AppCompatActivity {
    public static Database database;
    TextView txtv_login;
    LinearLayout taikhoang, dv_the, thongbao, danhmuc_sach, giohang, yeuthich;
    private RecyclerView rcv_qc;
    private QuangCaoAdapter quangCaoAdapter;

    public static  String trang = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv_qc = (RecyclerView) findViewById(R.id.rcv_qc);

        rcv_qc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<QuangCao> quangCaoList = new ArrayList<>();
        quangCaoList.add(new QuangCao(R.drawable.baner1, "Nội dung hấp dẫn","Nội dung hấp gẫn,đa dạng và phong phú"));
        quangCaoList.add(new QuangCao(R.drawable.baner2, "Đảm bảo hoàn tiền 100%","Hoàn lại toàn bộ tiền nếu đơn hàng không đúng"));
        quangCaoList.add(new QuangCao(R.drawable.baner3, "Hỗ trợ trục tuyến 24/7","Tổng đài hỗ trợ 24/7 - Hotline: 039 291 3063"));
        quangCaoAdapter = new QuangCaoAdapter(this,quangCaoList);
        rcv_qc.setAdapter(quangCaoAdapter);

        //create table ng_dung
        database = new Database(this,"ql_sach.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS ng_dung(id_ngdung INTEGER PRIMARY KEY AUTOINCREMENT,ten_ngdung VARCHAR(100),user_ngdung VARCHAR(100),pass_ngdung VARCHAR(100),sdt_ngdung VARCHAR(100),diachi_ngdung TEXT,avatar_ngdung BLOB)");
        //database.QueryData("ALTER TABLE ng_dung ADD COLUMN  gioitinh_ngdung VARCHAR(30)");
        //database.QueryData("ALTER TABLE ng_dung ADD COLUMN  ngaysinh_ngdung  DATE");
        //database.QueryData("ALTER TABLE ng_dung ADD COLUMN  diachi_nhanhang  TEXT");
        //database.QueryData("INSERT INTO ng_dung VALUES(null, 'Admin 1', '001admin', '001admin', null, null, null,null, null, null )");

//        UPDATE employees
//        SET salary = 50000
//        WHERE department = 'IT';
        
        //create table tacgia
        database.QueryData("CREATE TABLE IF NOT EXISTS tacgia(id_tacgia INTEGER PRIMARY KEY AUTOINCREMENT,ten_tacgia TEXT,ngaysinh_tacgia DATE, quoctich_tacgia TEXT)");

        //create table theloai
        database.QueryData("CREATE TABLE IF NOT EXISTS theloai(id_theloai INTEGER PRIMARY KEY AUTOINCREMENT,ten_theloai TEXT, mota_theloai TEXT)");

        //create table nhaxb
        database.QueryData("CREATE TABLE IF NOT EXISTS nhaxb(id_nhaxb INTEGER PRIMARY KEY AUTOINCREMENT,ten_nhaxb TEXT,sdt_nhaxb VARCHAR(20), diachi_nhaxb TEXT)");

        //create table sach
        database.QueryData("CREATE TABLE IF NOT EXISTS sach(id_sach INTEGER PRIMARY KEY AUTOINCREMENT,tensach TEXT,id_nhaxb INTEGER, id_theloai INTEGER, id_tacgia INTEGER, nam_xb INTEGER, giasach FLOAT, hinhsach BLOB, FOREIGN KEY (id_nhaxb) REFERENCES nhaxb(id_nhaxb),FOREIGN KEY (id_tacgia) REFERENCES tacgia(id_tacgia),FOREIGN KEY (id_theloai) REFERENCES theloai(id_thheloai))");

        //create table admin_app
        database.QueryData("CREATE TABLE IF NOT EXISTS admin_app(id_admin INTEGER PRIMARY KEY AUTOINCREMENT,ten_admin TEXT,user_admin VARCHAR(100),pass_admin VARCHAR(100),sdt_admin VARCHAR(100),diachi_admin TEXT,avatar_admin BLOB)");

        //create table giohang
        //database.QueryData("CREATE TABLE IF NOT EXISTS giohang(id_sach INTEGER ,id_ngdung INTEGERT,ngay_them DATE , FOREIGN KEY (id_sach) REFERENCES sach(id_sach), FOREIGN KEY (id_ngdung) REFERENCES ng_dung(id_ngdung),UNIQUE(id_sach, id_ngdung ),UNIQUE(id_sach, id_ngdung, ngay_them))");
        //database.QueryData("CREATE TABLE IF NOT EXISTS gio_hang(id_sach INTEGER ,id_ngdung INTEGERT)");
        database.QueryData("CREATE TABLE IF NOT EXISTS gio_hang_n(id_giohang INTEGER PRIMARY KEY AUTOINCREMENT,id_sach INTEGER ,id_ngdung INTEGERT)");


        //create table yeuthich
        database.QueryData("CREATE TABLE IF NOT EXISTS yeuthich(id_sach INTEGER ,id_ngdung INTEGERT, FOREIGN KEY (id_sach) REFERENCES sach(id_sach), FOREIGN KEY (id_ngdung) REFERENCES ng_dung(id_ngdung),UNIQUE(id_sach, id_ngdung ))");

        //create table the
        database.QueryData("CREATE TABLE IF NOT EXISTS theloai(id_the INTEGER PRIMARY KEY AUTOINCREMENT,id_ngdung INTEGERT, ngay_them DATE, ngay_hh DATE, FOREIGN KEY (id_ngdung) REFERENCES ng_dung(id_ngdung))");

        txtv_login = (TextView) findViewById(R.id.txtv_login);
        txtv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login_ngdung.class);
                startActivity(intent);
            }
        });
        taikhoang = (LinearLayout) findViewById(R.id.taikhoang);
        taikhoang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,chitiet_ngdung.class);
                intent.putExtra("TRANG", trang);
                startActivity(intent);
            }
        });
        dv_the = (LinearLayout) findViewById(R.id.dv_the);
        dv_the.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,dv_the.class);
                intent.putExtra("TRANG", trang);
                startActivity(intent);
            }
        });

        thongbao = (LinearLayout) findViewById(R.id.thongbao);
        thongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, thong_bao.class);
                startActivity(intent);
                finish();

            }
        });
        Cursor data = database.Getdata("SELECT * FROM ng_dung WHERE id_ngdung = "+login_ngdung.kt_login+"");

        if(login_ngdung.kt_login != 0){
            while (data.moveToNext()){
                String str = data.getString(2).toString().trim();
                String str2  = str.substring(3,8);
                if(str2.equals("admin")){
                    Intent intent = new Intent(this,home_ql_admin.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
        Cursor data2 = database.Getdata("SELECT * FROM ng_dung WHERE id_ngdung = "+login_ngdung.kt_login+"");
        txtv_login = (TextView) findViewById(R.id.txtv_login);
        if(login_ngdung.kt_login != 0){

            while (data2.moveToNext()){
                String ten_ngdung = data2.getString(1).toString().trim();
                txtv_login.setText(ten_ngdung);
            }
        }
        danhmuc_sach = findViewById(R.id.danhmuc_sach);
        danhmuc_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, danh_muc_sach.class);
                startActivity(intent);
                finish();
            }
        });
        giohang = findViewById(R.id.giohang);
        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(login_ngdung.kt_login != 0) {

                    Intent intent  = new Intent(MainActivity.this, giohang_ca_nhan.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(MainActivity.this,login_ngdung.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        yeuthich = findViewById(R.id.yeuthich);
        yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(login_ngdung.kt_login != 0) {

                    Intent intent  = new Intent(MainActivity.this, sach_yeuthich.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(MainActivity.this,login_ngdung.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}