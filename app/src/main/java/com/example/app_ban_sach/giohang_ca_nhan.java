package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import giohang.Giohang;
import giohang.GiohangAdapter;

public class giohang_ca_nhan extends AppCompatActivity {
    public static RecyclerView rcv_giohang;
    public static GiohangAdapter giohangAdapter;
    ImageView btn_previou_giohang_canhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang_ca_nhan);
        rcv_giohang = findViewById(R.id.rcv_giohang);
        btn_previou_giohang_canhan = findViewById(R.id.btn_previou_giohang_canhan);
        rcv_giohang.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        List<Giohang> giohangList = new ArrayList<>();
        Cursor cursor = MainActivity.database.Getdata("SELECT * FROM gio_hang_n WHERE id_ngdung  = "+login_ngdung.kt_login+"");
        while (cursor.moveToNext()){
            giohangList.add(new Giohang(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2)));
        }
        giohangAdapter = new GiohangAdapter(giohangList);
        rcv_giohang.setAdapter(giohangAdapter);
        giohangAdapter.notifyDataSetChanged();

        btn_previou_giohang_canhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(giohang_ca_nhan.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}