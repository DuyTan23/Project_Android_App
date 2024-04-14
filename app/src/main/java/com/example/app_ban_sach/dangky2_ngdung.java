package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dangky2_ngdung extends AppCompatActivity {
    Database database;
    EditText user_dk, pass_dk, pass2_dk, diachinhan_dk;
    Button btn_dk;
    String ten_dk, diachi_dk, ngaysinh_dk, chon_gt, user, pass, pass2, diachinhan, sdt_dk;
    byte[] avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky2_ngdung);

        user_dk = (EditText) findViewById(R.id.user_dk);
        pass_dk = (EditText) findViewById(R.id.pass_dk);
        pass2_dk = (EditText) findViewById(R.id.pass2_dk);
        diachinhan_dk = (EditText) findViewById(R.id.diachinhan_dk);
        btn_dk = (Button) findViewById(R.id.btn_dk);





        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                sdt_dk = intent.getStringExtra("SDT").toString();
                ten_dk = intent.getStringExtra("TEN_DK").toString();
                diachi_dk = intent.getStringExtra("DIACHI_DK").toString();
                ngaysinh_dk = intent.getStringExtra("NGAYSINH_DK").toString();
                chon_gt = intent.getStringExtra("CHON_GT");
                avatar = intent.getByteArrayExtra("AVATAR");
                user = user_dk.getText().toString();
                pass = pass_dk.getText().toString().trim();
                pass2 = pass2_dk.getText().toString().trim();
                diachinhan = diachinhan_dk.getText().toString();
                if(user.isEmpty() || pass.isEmpty() || pass2.isEmpty() || diachinhan.isEmpty()){
                    Toast.makeText(dangky2_ngdung.this, "Vui lòng nhập đủ thông tin để tiếp tục", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(pass2)){

                        database = new Database(dangky2_ngdung.this,"ql_sach.sqlite", null, 1);
                        database.InsertNgdung(ten_dk, user, pass, sdt_dk, diachi_dk, avatar, chon_gt,ngaysinh_dk,diachinhan);
                        Intent intent1 = new Intent(dangky2_ngdung.this, login_ngdung.class);
                        login_ngdung.kt_login = 0;
                        startActivity(intent1);
                        finish();
                    }
                    else{

                        Toast.makeText(dangky2_ngdung.this, "Xác nhận mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}