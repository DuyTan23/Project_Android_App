package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class dv_the extends AppCompatActivity {
    TextView txtv_ten_the, txtv_gioitinh_the, txtv_ngaysinh_the, txtv_sdt_the;
    ImageView img_avatar_the;
    ImageView btn_previou_the;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dv_the);
        txtv_ten_the = (TextView) findViewById(R.id.txtv_ten_the);
        txtv_gioitinh_the = (TextView) findViewById(R.id.txtv_gioitinh_the);
        txtv_ngaysinh_the = (TextView) findViewById(R.id.txtv_ngaysinh_the);
        txtv_sdt_the = (TextView) findViewById(R.id.txtv_sdt_the);
        img_avatar_the = (ImageView) findViewById(R.id.img_avatar_the);
        btn_previou_the = (ImageView) findViewById(R.id.btn_previou_the);
        Cursor data = MainActivity.database.Getdata("SELECT * FROM ng_dung  WHERE id_ngdung = "+login_ngdung.kt_login+"");

        btn_previou_the.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dv_the.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if(login_ngdung.kt_login != 0) {
            while (data.moveToNext()) {
                byte[] hinhanh = data.getBlob(6);
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
                String ten_ngdung = data.getString(1);
                String sdt_ngdung = data.getString(4);
                String gioitinh_ngdung = data.getString(7);
                String ngaysinh_ngdung = data.getString(8);
                txtv_ten_the.setText(ten_ngdung);
                txtv_gioitinh_the.setText(gioitinh_ngdung);
                txtv_sdt_the.setText(sdt_ngdung);
                txtv_ngaysinh_the.setText(ngaysinh_ngdung);
                img_avatar_the.setImageBitmap(bitmap2);
            }
        }
        else{
            Intent intent = new Intent(dv_the.this,login_ngdung.class);
            startActivity(intent);
            finish();
        }

    }
}