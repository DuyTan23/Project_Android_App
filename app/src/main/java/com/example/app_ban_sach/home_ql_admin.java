package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class home_ql_admin extends AppCompatActivity {
    LinearLayout ql_sach, ql_tacgia, ql_nhaxb, ql_theloai, ql_hoadon;
    CardView car_log_out_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ql_admin);
        ql_sach = (LinearLayout) findViewById(R.id.ql_sach);
        ql_tacgia = (LinearLayout) findViewById(R.id.ql_tacgia);
        ql_theloai = (LinearLayout) findViewById(R.id.ql_theloai);
        ql_nhaxb = (LinearLayout) findViewById(R.id.ql_nhacxb);
        car_log_out_admin = (CardView) findViewById(R.id.car_log_out_admin);

        car_log_out_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_ngdung.kt_login = 0;
                Intent intent = new Intent(home_ql_admin.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ql_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_ql_admin.this, home_ql_sach.class);
                startActivity(intent);
                finish();
            }
        });

        ql_tacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_ql_admin.this, home_ql_tacgia.class);
                startActivity(intent);
                finish();
            }
        });
        ql_nhaxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_ql_admin.this, home_ql_nhaxb.class);
                startActivity(intent);
                finish();
            }
        });

        ql_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_ql_admin.this, home_ql_theloai.class);
                startActivity(intent);;

            }
        });

    }
}