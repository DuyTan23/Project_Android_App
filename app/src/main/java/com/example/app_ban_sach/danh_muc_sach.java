package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sach.Sach;
import sach.SachAdapter_danhmuc;

public class danh_muc_sach extends AppCompatActivity {
    RecyclerView rcv_danhmuc_sach;
    ImageView btn_previou_sach_danhmuc;
    SachAdapter_danhmuc sachAdapter_danhmuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_sach);
        btn_previou_sach_danhmuc = findViewById(R.id.btn_previou_sach_danhmuc);

        rcv_danhmuc_sach = findViewById(R.id.rcv_danhmuc_sach);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_danhmuc_sach.setLayoutManager(gridLayoutManager);
        List<Sach> sachList = new ArrayList<>();
        Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM sach");
        while (cursor1.moveToNext()){
            sachList.add(new Sach(cursor1.getInt(0), cursor1.getString(1).toString().trim(),cursor1.getInt(2), cursor1.getInt(3), cursor1.getInt(4), cursor1.getInt(5),cursor1.getFloat(6),cursor1.getBlob(7)));
        }
        sachAdapter_danhmuc = new SachAdapter_danhmuc(danh_muc_sach.this,sachList);
        rcv_danhmuc_sach.setAdapter(sachAdapter_danhmuc);

        btn_previou_sach_danhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(danh_muc_sach.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}