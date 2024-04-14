package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class thong_bao extends AppCompatActivity {
    ImageView btn_previou_thong_bao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);
        btn_previou_thong_bao = (ImageView) findViewById(R.id.btn_previou_thong_bao);
        btn_previou_thong_bao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(thong_bao.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}