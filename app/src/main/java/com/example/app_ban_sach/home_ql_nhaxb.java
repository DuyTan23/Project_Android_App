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

import nhaxb.Nhaxb;
import nhaxb.NhaxbAdapter;
public class home_ql_nhaxb extends AppCompatActivity {
    ImageView btn_previou_ql_nhaxb, btn_add_nhaxb;
    public static Dialog dialog;

    public static RecyclerView rcv_nhaxb;

    public  static NhaxbAdapter nhaxbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ql_nhaxb);

        btn_add_nhaxb = (ImageView) findViewById(R.id.btn_add_nhaxb);
        btn_previou_ql_nhaxb = (ImageView) findViewById(R.id.btn_previou_ql_nhaxb);
        rcv_nhaxb = (RecyclerView) findViewById(R.id.rcv_nhaxb);
        dialog = new Dialog(home_ql_nhaxb.this);
        rcv_nhaxb.setLayoutManager(new LinearLayoutManager(home_ql_nhaxb.this, LinearLayoutManager.VERTICAL, false));

        btn_add_nhaxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setTitle("Hộp thoại sử lí");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_add_nhaxb);
                dialog.show();
                EditText edt_add_tennhaxb = (EditText) dialog.findViewById(R.id.edt_add_tennhaxb);
                EditText edt_add_sdtnhaxb = (EditText) dialog.findViewById(R.id.edt_add_sdtnhaxb);
                EditText  edt_add_diachinhaxb = (EditText) dialog.findViewById(R.id.edt_add_diachinhaxb);
                Button btn_yes_nhaxb_add = (Button) dialog.findViewById(R.id.btn_yes_nhaxb_add);
                Button btn_no_nhaxb_add = (Button) dialog.findViewById(R.id.btn_no_nhaxb_add);

                btn_no_nhaxb_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                btn_yes_nhaxb_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tennhaxb, sdtnhaxb, diachinhaxb;
                        tennhaxb = edt_add_tennhaxb.getText().toString().trim();
                        sdtnhaxb = edt_add_sdtnhaxb.getText().toString().trim();
                        diachinhaxb  = edt_add_diachinhaxb.getText().toString().trim();
                        if(tennhaxb.isEmpty() || sdtnhaxb.isEmpty() || diachinhaxb.isEmpty()){
                            Toast.makeText(home_ql_nhaxb.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(home_ql_nhaxb.this, tennhaxb+sdtnhaxb+diachinhaxb, Toast.LENGTH_SHORT).show();
                            //MainActivity.database.QueryData("INSERT INTO nhaxb VALUES(null, '"+tennhaxb+"','"+sdtnhaxb+"','"+diachinhaxb+"')");
                            MainActivity.database.QueryData("INSERT INTO nhaxb VALUES(null, '"+tennhaxb+"', '"+sdtnhaxb+"', '"+diachinhaxb+"')");
                            Toast.makeText(home_ql_nhaxb.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            rcv_nhaxb = (RecyclerView) findViewById(R.id.rcv_nhaxb);
                            rcv_nhaxb.setLayoutManager(new LinearLayoutManager(home_ql_nhaxb.this, LinearLayoutManager.VERTICAL, false));
                            List<Nhaxb> nhaxbList = new ArrayList<>();
                            Cursor cursor = MainActivity.database.Getdata("SELECT * FROM nhaxb ");
                            while (cursor.moveToNext()){
                                nhaxbList.add(new Nhaxb(cursor.getInt(0), cursor.getString(1).toString(), cursor.getString(2).toString(), cursor.getString(3).toString()));
                            }
                            nhaxbAdapter = new NhaxbAdapter(nhaxbList);
                            rcv_nhaxb.setAdapter(nhaxbAdapter);
                            nhaxbAdapter.notifyDataSetChanged();
                            dialog.cancel();

                        }
                    }
                });
            }
        });
        List<Nhaxb> nhaxbList = new ArrayList<>();
        Cursor cursor = MainActivity.database.Getdata("SELECT * FROM nhaxb ");
        while (cursor.moveToNext()){
            nhaxbList.add(new Nhaxb(cursor.getInt(0), cursor.getString(1).toString().trim(), cursor.getString(2).toString(), cursor.getString(3).toString()));
        }
        nhaxbAdapter = new NhaxbAdapter(nhaxbList);
        rcv_nhaxb.setAdapter(nhaxbAdapter);
        nhaxbAdapter.notifyDataSetChanged();

        btn_previou_ql_nhaxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_ql_nhaxb.this, home_ql_admin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}


