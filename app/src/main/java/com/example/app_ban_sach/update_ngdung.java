package com.example.app_ban_sach;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class update_ngdung extends AppCompatActivity {
    EditText edt_up_ten, edt_up_sdt, edt_up_diachi, edt_up_noinhan;
    RadioGroup radi_up_gt;
    RadioButton rabu_up_nam, rabu_up_nu;
    Button btnOpenDatePicker_up, btn_update_save, btn_update_mk;

    ImageButton btn_open_camera, btn_open_folder_img, btn_previou_up;

    Database database;
    ImageView img_avatar_up;

    private String chon_gt = "";

    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ngdung);

        database = new Database(this,"ql_sach.sqlite", null, 1);
        Cursor data = database.Getdata("SELECT * FROM ng_dung WHERE id_ngdung = "+login_ngdung.kt_login+ "");

        //ánh xạ

        edt_up_ten = (EditText) findViewById(R.id.edt_up_ten);
        edt_up_sdt = (EditText) findViewById(R.id.edt_up_sdt);
        edt_up_diachi = (EditText) findViewById(R.id.edt_up_diachi);
        edt_up_noinhan = (EditText) findViewById(R.id.edt_up_noinhan);
        radi_up_gt  = (RadioGroup) findViewById(R.id.radi_up_gt);
        rabu_up_nam = (RadioButton) findViewById(R.id.rabu_up_nam);
        rabu_up_nu = (RadioButton) findViewById(R.id.rabu_up_nu);
        btnOpenDatePicker_up = (Button) findViewById(R.id.btnOpenDatePicker_up);
        btn_open_camera = (ImageButton) findViewById(R.id.btn_open_camera);
        img_avatar_up = (ImageView) findViewById(R.id.img_avatar_up) ;
        btn_open_folder_img =(ImageButton) findViewById(R.id.btn_open_folder_img);
        btn_update_save = (Button) findViewById(R.id.btn_update_save) ;
        btn_update_mk = (Button) findViewById(R.id.btn_update_mk);
        btn_previou_up = (ImageButton) findViewById(R.id.btn_previou_up);

        btn_previou_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(update_ngdung.this, chitiet_ngdung.class);
                startActivity(intent);
                finish();
            }
        });

        btn_open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });

        btn_open_folder_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });

        if(login_ngdung.kt_login != 0) {
            while (data.moveToNext()) {
                byte[] hinhanh = data.getBlob(6);
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
                img_avatar_up.setImageBitmap(bitmap2);

                edt_up_ten.setText(data.getString(1));
                edt_up_sdt.setText(data.getString(4));
                edt_up_diachi.setText(data.getString(5));
                edt_up_noinhan.setText(data.getString(9));
                btnOpenDatePicker_up.setText(data.getString(8));
                if(data.getString(7).equals("Nam")){
                    radi_up_gt.check(R.id.rabu_up_nam);
                    chon_gt = rabu_up_nam.getText().toString();
                }
                else{
                    radi_up_gt.check(R.id.rabu_up_nu);
                    chon_gt = rabu_up_nu.getText().toString();
                }
            }
        }
        radi_up_gt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rabu_up_nam){
                   chon_gt = rabu_up_nam.getText().toString();
                }
                else{
                    chon_gt = rabu_up_nu.getText().toString();
                }
            }
        });

        btnOpenDatePicker_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePickerDialog();
            }
        });

        btn_update_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten_ngdung = edt_up_ten.getText().toString();
                String sdt_ngdung = edt_up_sdt.getText().toString();
                String diachi_ngdung = edt_up_diachi.getText().toString();
                String ngaysinh_ngdung = btnOpenDatePicker_up.getText().toString();
                String diachi_nhanhang = edt_up_noinhan.getText().toString();

                BitmapDrawable bitmapDrawable = (BitmapDrawable) img_avatar_up.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] avatar = byteArrayOutputStream.toByteArray();
                database.QueryData("UPDATE ng_dung SET ten_ngdung = '"+ten_ngdung+"', sdt_ngdung = '"+sdt_ngdung+"', diachi_ngdung = '"+diachi_ngdung+"', gioitinh_ngdung = '"+chon_gt+"', ngaysinh_ngdung = '"+ngaysinh_ngdung+"', diachi_nhanhang = '"+diachi_nhanhang+"' WHERE id_ngdung = "+login_ngdung.kt_login+"");
                database.UpdateNgdung(avatar);
                Intent intent = new Intent(update_ngdung.this, chitiet_ngdung.class);
                startActivity(intent);
                finish();

            }
        });

        btn_update_mk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(update_ngdung.this,update_tk_mk.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                // Giới hạn kích thước ảnh
                Bitmap resizedBitmap = resizeImage(bitmap,300);
                img_avatar_up.setImageBitmap(resizedBitmap);
            }
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK &data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap resizedBitmap = resizeImage(bitmap,300);
                img_avatar_up.setImageBitmap(resizedBitmap);

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

    private void openDatePickerDialog() {
        // Lấy ngày tháng năm hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                update_ngdung.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String selectedDate = i + "-" + (i1 + 1) + "-" + i2;
                        // Hiển thị ngày đã chọn
                        btnOpenDatePicker_up.setText(selectedDate);
                    }
                },
                year, month, day
        );

        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }
}