package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class login_ngdung extends AppCompatActivity {
    EditText edt_user, edt_pass;
    Button btn_singIn, btn_create_accout;
    ImageView btn_previou;

    Database database;
    public  static int kt_login = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ngdung);
        btn_previou = (ImageView) findViewById(R.id.btn_previou);
        edt_user = (EditText) findViewById(R.id.edt_user);
        edt_pass = (EditText) findViewById(R.id.edt_pass);
        btn_singIn = (Button) findViewById(R.id.btn_singIn);
        btn_create_accout = (Button) findViewById(R.id.btn_create_accout);

        btn_previou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_ngdung.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        btn_singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edt_user0 = edt_user.getText().toString();
                String edt_pass0 = edt_pass.getText().toString();

                if(edt_user0.isEmpty()  || edt_pass0.isEmpty()){
                    Toast.makeText(login_ngdung.this, "Vui lòng nhập Username và Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    database = new Database(login_ngdung.this,"ql_sach.sqlite", null, 1);
                    Cursor data = database.Getdata("SELECT * FROM ng_dung where user_ngdung = '"+edt_user0+"' AND pass_ngdung = '"+edt_pass0+"'");
                    while (data.moveToNext()){
                        int id = data.getInt(0);
                        String user  = data.getString(2);
                        String pass = data.getString(3);
                        kt_login = id;
                    }
                    if(kt_login != 0){
                        Toast.makeText(login_ngdung.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login_ngdung.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(login_ngdung.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                    }
                    //kt_login = 0;
                }
            }
        });
        btn_create_accout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_ngdung.this, dangky_ngdung.class);
                startActivity(intent);
                finish();
            }
        });
    }
}