package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tacgia.Tacgia;
import tacgia.TacgiaAdapter;
import theloai.Theloai;
import theloai.TheloaiAdapter;

public class home_ql_theloai extends AppCompatActivity {
    ImageView btn_previou_ql_theloai, btn_add_theloai;
    public static Dialog dialog;

    public static RecyclerView rcv_theloai;

    public  static TheloaiAdapter theloaiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ql_theloai);
        btn_add_theloai = (ImageView) findViewById(R.id.btn_add_theloai);
        dialog = new Dialog(home_ql_theloai.this);
        rcv_theloai = (RecyclerView) findViewById(R.id.rcv_theloai);
        rcv_theloai.setLayoutManager(new LinearLayoutManager(home_ql_theloai.this, LinearLayoutManager.VERTICAL, false));
        btn_previou_ql_theloai = (ImageView) findViewById(R.id.btn_previou_ql_theloai) ;

        btn_previou_ql_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_ql_theloai.this,home_ql_admin.class);
                startActivity(intent);
                finish();
            }
        });

        btn_add_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setTitle("Hộp thoại sử lí");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_add_theloai);
                EditText edt_add_tentheloai = (EditText) dialog.findViewById(R.id.edt_add_tentheloai);
                EditText edt_add_motatheloai = (EditText) dialog.findViewById(R.id.edt_add_motatheloai);
                Button btn_yes_theloai_add = (Button) dialog.findViewById(R.id.btn_yes_theloai_add);
                Button btn_no_theloai_add= (Button) dialog.findViewById(R.id.btn_no_theloai_add);
                dialog.show();

                btn_no_theloai_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                btn_yes_theloai_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tentheloai = edt_add_tentheloai.getText().toString().trim();
                        String motatheloai = edt_add_motatheloai.getText().toString().trim();
                        if(tentheloai.isEmpty() || motatheloai.equals("Chọn ngày sinh của bạn")){
                            Toast.makeText(home_ql_theloai.this, "Vui lòng không bỏ trống", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            MainActivity.database.QueryData("INSERT INTO theloai VALUES(null, '"+tentheloai+"', '"+motatheloai+"')");
                            dialog.cancel();



                            rcv_theloai = (RecyclerView) findViewById(R.id.rcv_theloai);
                            rcv_theloai.setLayoutManager(new LinearLayoutManager(home_ql_theloai.this, LinearLayoutManager.VERTICAL, false));
                            List<Theloai> theloaiList = new ArrayList<>();
                            Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM theloai ");
                            while (cursor1.moveToNext()){
                                theloaiList.add(new Theloai(cursor1.getInt(0), cursor1.getString(1).toString().trim(), cursor1.getString(2).toString().trim()));
                            }
                            theloaiAdapter = new TheloaiAdapter(theloaiList);
                            rcv_theloai.setAdapter(theloaiAdapter);
                            theloaiAdapter.notifyDataSetChanged();
                        }
                    }
                });
                btn_no_theloai_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });
        rcv_theloai = (RecyclerView) findViewById(R.id.rcv_theloai);
        rcv_theloai.setLayoutManager(new LinearLayoutManager(home_ql_theloai.this, LinearLayoutManager.VERTICAL, false));

        List<Theloai> theloaiList = new ArrayList<>();
        Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM theloai");
        while (cursor1.moveToNext()){
            theloaiList.add(new Theloai(cursor1.getInt(0), cursor1.getString(1).toString().trim(), cursor1.getString(2).toString().trim()));
        }

        theloaiAdapter = new TheloaiAdapter(theloaiList);
        rcv_theloai.setAdapter(theloaiAdapter);
        theloaiAdapter.notifyDataSetChanged();
    }
}