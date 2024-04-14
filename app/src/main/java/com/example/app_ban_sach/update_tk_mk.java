package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class update_tk_mk extends AppCompatActivity {
    EditText edt_up_pass_old, edt_up_pass_new, edt_up_pass_new_xn;
    CheckBox check_agree;

    Button btn_agree_change_pass;

    String pass_old ,pass_new, pass_new_xn;

    Boolean checkbox = false;

    Database database;

    ImageButton btn_previou_up_mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tk_mk);
        edt_up_pass_old = (EditText) findViewById(R.id.edt_up_pass_old);
        edt_up_pass_new = (EditText) findViewById(R.id.edt_up_pass_new);
        edt_up_pass_new_xn = (EditText) findViewById(R.id.edt_up_pass_new_xn);
        check_agree = (CheckBox) findViewById(R.id.check_agree);
        btn_agree_change_pass = (Button) findViewById(R.id.btn_agree_change_pass);

        btn_previou_up_mk = (ImageButton) findViewById(R.id.btn_previou_up_mk);

        btn_previou_up_mk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(update_tk_mk.this, update_ngdung.class);
                startActivity(intent);
                finish();
            }
        });

        database = new Database(this,"ql_sach.sqlite", null, 1);

        check_agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkbox  = b;
                }

            }
        });


        btn_agree_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pass_old = edt_up_pass_old.getText().toString();
                pass_new = edt_up_pass_new.getText().toString().trim();
                pass_new_xn = edt_up_pass_new_xn.getText().toString().trim();


                if (pass_new.equals(pass_new_xn) == false || pass_old.isEmpty() || pass_new.isEmpty() || pass_new_xn.isEmpty() || checkbox == false){
                    Toast.makeText(update_tk_mk.this, "Vui long kiem tra lai thong tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(update_tk_mk.this, pass_new_xn, Toast.LENGTH_SHORT).show();
                    database.QueryData("UPDATE ng_dung SET pass_ngdung = '"+pass_new_xn+"' WHERE id_ngdung = "+login_ngdung.kt_login+" ");
                    Toast.makeText(update_tk_mk.this, "Đổi thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(update_tk_mk.this, MainActivity.class);
                }
            }
        });


    }
}