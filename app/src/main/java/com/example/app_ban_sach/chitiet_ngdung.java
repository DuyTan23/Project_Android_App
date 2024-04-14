package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class chitiet_ngdung extends AppCompatActivity {
    Database database;

    TextView txtv_ten, txtv_sdt, txtv_diachi, txtv_gioitinh, txtv_ngaysinh, txtv_diachinhan;
    ImageView btn_previou, btn_update_ngdun, img_avatar;
    CardView car_log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_ngdung);


        txtv_ten = (TextView) findViewById(R.id.txtv_ten);
        txtv_gioitinh = (TextView) findViewById(R.id.txtv_gioitinh);
        txtv_sdt = (TextView)  findViewById(R.id.txtv_sdt);
        txtv_diachi = (TextView) findViewById(R.id.txtv_diachi);
        txtv_ngaysinh = (TextView) findViewById(R.id.txtv_ngaysinh);
        txtv_diachinhan = (TextView) findViewById(R.id.txtv_diachinhan);
        btn_previou = (ImageView) findViewById(R.id.btn_previou) ;
        car_log_out = (CardView) findViewById(R.id.car_log_out) ;
        btn_update_ngdun = (ImageView)  findViewById(R.id.btn_update_ngdung);
        img_avatar = (ImageView) findViewById(R.id.img_avatar);



        database = new Database(this,"ql_sach.sqlite", null, 1);
        Cursor data = database.Getdata("SELECT * FROM ng_dung  WHERE id_ngdung = "+login_ngdung.kt_login+"");
        //chuyen byte thanh imageVuew bitmap


        if(login_ngdung.kt_login != 0) {
            while (data.moveToNext()) {
                byte[] hinhanh = data.getBlob(6);
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
                String ten_ngdung = data.getString(1);
                String user_ngdung = data.getString(2);
                String pass_ngdung = data.getString(3);
                String sdt_ngdung = data.getString(4);
                String diachi_ngdung = data.getString(5);
                String gioitinh_ngdung = data.getString(7);
                String ngaysinh_ngdung = data.getString(8);
                String diachi_nhanhang = data.getString(9);
                txtv_ten.setText(ten_ngdung);
                txtv_gioitinh.setText(gioitinh_ngdung);
                txtv_sdt.setText(sdt_ngdung);
                txtv_diachi.setText(diachi_ngdung);
                txtv_diachinhan.setText(diachi_nhanhang);
                txtv_gioitinh.setText(gioitinh_ngdung);
                txtv_ngaysinh.setText(ngaysinh_ngdung);
                img_avatar.setImageBitmap(bitmap2);

                btn_update_ngdun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(chitiet_ngdung.this, update_ngdung.class);
                        intent.putExtra("TEN",ten_ngdung);
                        intent.putExtra("USER",user_ngdung);
                        intent.putExtra("PASS",pass_ngdung);
                        intent.putExtra("SDT",sdt_ngdung);
                        intent.putExtra("DIACHI",diachi_ngdung);
                        intent.putExtra("GIOITINH", gioitinh_ngdung);
                        intent.putExtra("DIACHINHAN",diachi_nhanhang);
                        intent.putExtra("NGAYSINH", ngaysinh_ngdung);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }
        else{
            Intent intent = new Intent(chitiet_ngdung.this,login_ngdung.class);
            startActivity(intent);
            finish();
        }



            btn_previou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(chitiet_ngdung.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        car_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_ngdung.kt_login = 0;
                Intent intent = new Intent(chitiet_ngdung.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        }
    }