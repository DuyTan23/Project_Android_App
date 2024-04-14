package com.example.app_ban_sach;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class dangky_ngdung extends AppCompatActivity {
    private Button btnOpenDatePicker, btn_tieptuc_dk ;
    ImageButton btn_previoudk;

    EditText edt_dk_ten, edt_dk_diachi, edt_dk_sdt;
    RadioGroup radi_gt;
    RadioButton rabu_nam, rabu_nu;
    String chon_gt = "";

    ImageButton btn_open_camera_dk, btn_open_folder_img_dk;
    ImageView img_avatar_dk;

    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;

    Bitmap bitmap0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky_ngdung);
        btnOpenDatePicker = (Button) findViewById(R.id.btnOpenDatePicker);
        btn_tieptuc_dk = (Button) findViewById(R.id.btn_tieptuc_dk);
        btn_previoudk = (ImageButton) findViewById(R.id.btn_previoudk);
        edt_dk_sdt = (EditText)  findViewById(R.id.edt_dk_sdt);
        edt_dk_ten = (EditText) findViewById(R.id.edt_dk_ten);
        edt_dk_diachi = (EditText) findViewById(R.id.edt_dk_diachi);
        radi_gt = (RadioGroup) findViewById(R.id.radi_gt);
        rabu_nam = (RadioButton) findViewById(R.id.rabu_nam);
        rabu_nu = (RadioButton) findViewById(R.id.rabu_nu);



        btn_open_camera_dk = (ImageButton) findViewById(R.id.btn_open_camera_dk);
        btn_open_folder_img_dk = (ImageButton)  findViewById(R.id.btn_open_folder_img_dk);
        img_avatar_dk = (ImageView)  findViewById(R.id.img_avatar_dk);
        btnOpenDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePickerDialog();
            }
        });


        btn_open_camera_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });

        btn_open_folder_img_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });

        if(rabu_nam.isChecked()){
            chon_gt = rabu_nam.getText().toString();
        }
        else{
            chon_gt = rabu_nu.getText().toString();
        }

        radi_gt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rabu_nam){
                    chon_gt = rabu_nam.getText().toString();
                    Toast.makeText(dangky_ngdung.this, chon_gt, Toast.LENGTH_SHORT).show();
                }
                else{
                     chon_gt = rabu_nu.getText().toString();
                    Toast.makeText(dangky_ngdung.this, chon_gt, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_previoudk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(dangky_ngdung.this,login_ngdung.class);
                startActivity(intent);
                finish();
            }
        });


        btn_tieptuc_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ten = edt_dk_ten.getText().toString();
                String sdt = edt_dk_sdt.getText().toString();
                String diachi = edt_dk_diachi.getText().toString();
                String ngaysinh = btnOpenDatePicker.getText().toString();

                if(ten.isEmpty() || diachi.isEmpty() ||sdt.isEmpty()){
                    Toast.makeText(dangky_ngdung.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(dangky_ngdung.this, chon_gt, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(dangky_ngdung.this, dangky2_ngdung.class);
                    intent.putExtra("TEN_DK", ten);
                    intent.putExtra("DIACHI_DK",diachi);
                    intent.putExtra("CHON_GT",chon_gt);
                    intent.putExtra("NGAYSINH_DK",ngaysinh);
                    intent.putExtra("SDT",sdt);
                    byte[] avatar;
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) img_avatar_dk.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    avatar = byteArrayOutputStream.toByteArray();
                    intent.putExtra("AVATAR",avatar);
                    startActivity(intent);
                    finish();
                }

            }
        });


        int kt = 0;
    }
    private void openDatePickerDialog() {
        // Lấy ngày tháng năm hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                dangky_ngdung.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String selectedDate = i + "-" + (i1 + 1) + "-" + i2;
                        // Hiển thị ngày đã chọn
                        btnOpenDatePicker.setText(selectedDate);
                    }
                },
                year, month, day
        );

        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                // Giới hạn kích thước ảnh
                Bitmap resizedBitmap = resizeImage(bitmap,300);
                img_avatar_dk.setImageBitmap(resizedBitmap);
                bitmap0 = resizedBitmap;
            }
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK &data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap resizedBitmap = resizeImage(bitmap,300);
                img_avatar_dk.setImageBitmap(resizedBitmap);
                bitmap0 = resizedBitmap;

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Bitmap resizeImage(Bitmap originalBitmap, int size) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();

        // Chọn kích thước mới sao cho chiều dài và chiều rộng đều bằng 'size'
        int newWidth = size;
        int newHeight = size;

        float scaleWidth = ((float) size) / width;
        float scaleHeight = ((float) size) / height;

        // Sử dụng ma trận để resize ảnh
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        // Tạo ảnh mới sau khi đã resize
        Bitmap resizedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, width, height, matrix, false);

        // Cắt thành hình vuông
        int cropStartX = (resizedBitmap.getWidth() - size) / 2;
        int cropStartY = (resizedBitmap.getHeight() - size) / 2;
        Bitmap croppedBitmap = Bitmap.createBitmap(resizedBitmap, cropStartX, cropStartY, size, size);
        return croppedBitmap;
    }
}