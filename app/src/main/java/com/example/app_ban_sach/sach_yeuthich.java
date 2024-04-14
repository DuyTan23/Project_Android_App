package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import giohang.Giohang;
import sach.Sach;
import yeu_thich.Yeuthich;
import yeu_thich.YeuthichAdapter;


public class sach_yeuthich extends AppCompatActivity {
    public static RecyclerView rcv_yeuthich;
    public static YeuthichAdapter yeuthichAdapter;
    ImageView btn_previou_yeuthich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_yeuthich);
        rcv_yeuthich = findViewById(R.id.rcv_sach_yeuthich);
        btn_previou_yeuthich = findViewById(R.id.btn_previou_sach_yeuthich);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv_yeuthich.setLayoutManager(gridLayoutManager);
        Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM yeuthich WHERE id_ngdung = "+login_ngdung.kt_login+"");
        List<Yeuthich> yeuthichList = new ArrayList<>();
        while (cursor1.moveToNext()){
            yeuthichList.add(new Yeuthich(cursor1.getInt(0), cursor1.getInt(1)));
        }
        yeuthichAdapter = new YeuthichAdapter(sach_yeuthich.this,yeuthichList);
        rcv_yeuthich.setAdapter(yeuthichAdapter);
        yeuthichAdapter.notifyDataSetChanged();

        btn_previou_yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sach_yeuthich.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}