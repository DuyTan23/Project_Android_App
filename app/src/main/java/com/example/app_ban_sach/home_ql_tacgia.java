package com.example.app_ban_sach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import quangcao_index.QuangCao;
import quangcao_index.QuangCaoAdapter;
import tacgia.Tacgia;
import tacgia.TacgiaAdapter;

public class home_ql_tacgia extends AppCompatActivity {
    ImageView btn_previou_ql_tacgia, btn_add_tacgia;
    EditText edt_add_tentacgia;
    Button btnOpenDatePicker_add_time_tacgia, btn_yes_tacgia_add, btn_no_tacgia_add;
    public static RecyclerView rcv_tacgia;
    public static TacgiaAdapter tacgiaAdapter;
    private ConstraintLayout item_tacgia;
    public static Dialog dialog;
    public  static DatePickerDialog datePickerDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ql_tacgia);
        btn_previou_ql_tacgia = (ImageView) findViewById(R.id.btn_previou_ql_tacgia);
        btn_add_tacgia = (ImageView) findViewById(R.id.btn_add_tacgia);


        dialog = new Dialog(home_ql_tacgia.this);

        btn_previou_ql_tacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_ql_tacgia.this, home_ql_admin.class);
                startActivity(intent);
                finish();
            }
        });


        btn_add_tacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setTitle("Hộp thoại sử lí");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_add_tacgia);
                edt_add_tentacgia = (EditText) dialog.findViewById(R.id.edt_add_tentacgia);
                btnOpenDatePicker_add_time_tacgia = (Button) dialog.findViewById(R.id.btnOpenDatePicker_add_time_tacgia);
                btn_yes_tacgia_add = (Button) dialog.findViewById(R.id.btn_yes_tacgia_add);
                btn_no_tacgia_add= (Button) dialog.findViewById(R.id.btn_no_tacgia_add);
                dialog.show();;

                btnOpenDatePicker_add_time_tacgia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openDatePickerDialog();
                    }
                });
                btn_no_tacgia_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                btn_yes_tacgia_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tentacgia = edt_add_tentacgia.getText().toString().trim();
                        String ngaysinh = btnOpenDatePicker_add_time_tacgia.getText().toString();
                        if(tentacgia.isEmpty() || ngaysinh.equals("Chọn ngày sinh của bạn")){
                            Toast.makeText(home_ql_tacgia.this, "Vui lòng không bỏ trống", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            MainActivity.database.QueryData("INSERT INTO tacgia VALUES(null, '"+tentacgia+"', '"+ngaysinh+"', null)");
                            dialog.cancel();


                            rcv_tacgia = (RecyclerView) findViewById(R.id.rcv_tacgia);
                            rcv_tacgia.setLayoutManager(new LinearLayoutManager(home_ql_tacgia.this, LinearLayoutManager.VERTICAL, false));
                            List<Tacgia> tacgiaList1 = new ArrayList<>();
                            Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM tacgia");
                            while (cursor1.moveToNext()){
                                tacgiaList1.add(new Tacgia(cursor1.getInt(0), cursor1.getString(1).toString().trim(), cursor1.getString(2).toString().trim()));
                            }
                            tacgiaAdapter = new TacgiaAdapter(tacgiaList1);
                            rcv_tacgia.setAdapter(tacgiaAdapter);
                            tacgiaAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });



        rcv_tacgia = (RecyclerView) findViewById(R.id.rcv_tacgia);
        rcv_tacgia.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Tacgia> tacgiaList = new ArrayList<>();
        Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM tacgia");
        while (cursor1.moveToNext()){
            tacgiaList.add(new Tacgia(cursor1.getInt(0), cursor1.getString(1).toString().trim(), cursor1.getString(2).toString().trim()));
        }
        tacgiaAdapter = new TacgiaAdapter(tacgiaList);
        rcv_tacgia.setAdapter(tacgiaAdapter);
        tacgiaAdapter.notifyDataSetChanged();

    }
    private void openDatePickerDialog() {
        // Lấy ngày tháng năm hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        datePickerDialog = new DatePickerDialog(
                home_ql_tacgia.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String selectedDate = i + "-" + (i1 + 1) + "-" + i2;
                        // Hiển thị ngày đã chọn
                        btnOpenDatePicker_add_time_tacgia.setText(selectedDate);
                    }
                },
                year, month, day
        );

        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }


}