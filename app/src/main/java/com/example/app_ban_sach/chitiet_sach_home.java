package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import sach.Sach;
import yeu_thich.Yeuthich;

public class chitiet_sach_home extends AppCompatActivity {
    ImageView img_hinhsach_chitiet_home;
    TextView txtv_tensach_chitiet,txtv_tennhaxb_chitiet,txtv_tentheloai_chitiet,txtv_tentacgia_chitiet,txtv_namxb_chitiet, txtv_giasach_chitiet;
    ImageView btn_previou_sach;
    int id_nhaxb, id_theloai, id_tacgia;
    Button btn_add_giohang,btn_add_yeuthich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sach_home);
        img_hinhsach_chitiet_home = findViewById(R.id.img_hinhsach_chitiet_home);
        txtv_tensach_chitiet = findViewById(R.id.txtv_tensach_chitiet);
        txtv_tennhaxb_chitiet = findViewById(R.id.txtv_tennhaxb_chitiet);
        txtv_tentheloai_chitiet = findViewById(R.id.txtv_tentheloai_chitiet);
        txtv_tentacgia_chitiet = findViewById(R.id.txtv_tentacgia_chitiet);
        txtv_namxb_chitiet = findViewById(R.id.txtv_namxb_chitiet);
        txtv_giasach_chitiet = findViewById(R.id.txtv_giasach_chitiet);
        btn_previou_sach = findViewById(R.id.btn_previou_sach);
        btn_add_giohang = findViewById(R.id.btn_add_giohang);
        btn_add_yeuthich = findViewById(R.id.btn_add_yeuthich);
        btn_previou_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(chitiet_sach_home.this, danh_muc_sach.class);
                startActivity(i);
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Sach sach = (Sach) bundle.get("Sach_sach");
        Bundle bundle2 = getIntent().getExtras();
        if(bundle2 == null){
            return;
        }
        Yeuthich yeuthich = (Yeuthich) bundle2.get("Yeuthich");

        int id_sach = sach.getId_sach();
        byte[] hinhsach = new byte[0];
        Cursor cursor =  MainActivity.database.Getdata("SELECT * FROM sach WHERE id_sach = "+id_sach+"");
        while (cursor.moveToNext()){
            txtv_tensach_chitiet.setText(cursor.getString(1));
            id_nhaxb = cursor.getInt(2);
            id_theloai = cursor.getInt(3);
            id_tacgia = cursor.getInt(4);
            txtv_namxb_chitiet.setText(String.valueOf(cursor.getInt(5)));
            txtv_giasach_chitiet.setText(String.valueOf(cursor.getFloat(6)));
            hinhsach = cursor.getBlob(7);
        }
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhsach, 0, hinhsach.length);
        img_hinhsach_chitiet_home.setImageBitmap(bitmap2);
        Cursor cursor1 = MainActivity.database.Getdata("SELECT ten_nhaxb FROM nhaxb WHERE id_nhaxb = "+id_nhaxb+"");
        while (cursor1.moveToNext()){
            txtv_tennhaxb_chitiet.setText(cursor1.getString(0));
        }
        Cursor cursor2 = MainActivity.database.Getdata("SELECT ten_theloai FROM theloai WHERE id_theloai = "+id_theloai+"");
        while (cursor2.moveToNext()){
            txtv_tentheloai_chitiet.setText(cursor2.getString(0));
        }
        Cursor cursor3= MainActivity.database.Getdata("SELECT ten_tacgia FROM tacgia WHERE id_tacgia = "+id_tacgia+"");
        while (cursor3.moveToNext()){
            txtv_tentacgia_chitiet.setText(cursor3.getString(0));
        }
        btn_add_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login_ngdung.kt_login != 0) {

                    //MainActivity.database.QueryData("INSERT INTO Gio_hang VALUES(null,'"+id_sach+"', '"+login_ngdung.kt_login+"')");
                    MainActivity.database.QueryData("INSERT INTO Gio_hang_n VALUES(null, '"+id_sach+"', '"+login_ngdung.kt_login+"')");
                    Toast.makeText(chitiet_sach_home.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(chitiet_sach_home.this,login_ngdung.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btn_add_yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login_ngdung.kt_login != 0) {
                    MainActivity.database.QueryData("INSERT INTO yeuthich VALUES('"+id_sach+"', '"+login_ngdung.kt_login+"')");
                    Toast.makeText(chitiet_sach_home.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(chitiet_sach_home.this,login_ngdung.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}