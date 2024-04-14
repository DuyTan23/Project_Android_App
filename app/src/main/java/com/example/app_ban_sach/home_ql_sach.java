package com.example.app_ban_sach;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import nhaxb.Nhaxb;
import nhaxb.NhaxbAdapterSpinner;
import sach.Sach;
import sach.SachAdapter;
import tacgia.Tacgia;
import tacgia.TacgiaAdapter;
import tacgia.TacgiaAdapterSpinner;
import theloai.Theloai;
import theloai.TheloaiAdapterSpinner;

public class home_ql_sach extends AppCompatActivity {

    ImageView btn_add_books, btn_previou_ql_books;
    public static Dialog dialog;
    public static RecyclerView rcv_books;
    int REQUEST_CODE_FOLDER = 123;
    ImageView img_hinhsach_add;
    int id_nhaxb , id_tacgia, id_theloai, namxb;
    float giasach;
    String tensach;
    byte[] hinhsach;
    RecyclerView rcv_sach;
    SachAdapter sachAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ql_sach);
        btn_add_books = (ImageView) findViewById(R.id.btn_add_books);
        btn_previou_ql_books = (ImageView) findViewById(R.id.btn_previou_ql_books);







        dialog = new Dialog(home_ql_sach.this);


        btn_add_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Spinner Spinner_nhaxb, Spinner_theloai,Spinner_tacgia;


                dialog.setTitle("Hộp thoại sử lí");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_add_sach);

                Button btn_yes_sach_add, btn_no_sach_add;
                btn_yes_sach_add = dialog.findViewById(R.id.btn_yes_sach_add);
                btn_no_sach_add = dialog.findViewById(R.id.btn_no_sach_add);

                Spinner_nhaxb = (Spinner) dialog.findViewById(R.id.Spinner_nhaxb);
                Spinner_tacgia = (Spinner) dialog.findViewById(R.id.Spinner_tacgia);
                Spinner_theloai = (Spinner) dialog.findViewById(R.id.Spinner_theloai);

                img_hinhsach_add = (ImageView) dialog.findViewById(R.id.img_hinhsach_add);
                ImageButton btn_open_folder_img_hínhsach_add = (ImageButton) dialog.findViewById(R.id.btn_open_folder_img_hinh_add);
                EditText edt_add_tensach = dialog.findViewById(R.id.edt_add_tensach);
                EditText edt_add_namxb = dialog.findViewById(R.id.edt_add_namxb);
                EditText edt_add_giasach = dialog.findViewById(R.id.edt_add_giasach);







                btn_open_folder_img_hínhsach_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent,REQUEST_CODE_FOLDER);
                    }
                });

                //tạo danh sách dữ liệu cho spinner nhà xb
                List<Nhaxb> nhaxbList_spinner = new ArrayList<>();
                Cursor cursor = MainActivity.database.Getdata("SELECT * FROM nhaxb ");
                while (cursor.moveToNext()){
                    nhaxbList_spinner.add(new Nhaxb(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
                }
                NhaxbAdapterSpinner nhaxbAdapterSpinner = new NhaxbAdapterSpinner(home_ql_sach.this, R.layout.item_spinner_select,nhaxbList_spinner);
                Spinner_nhaxb.setAdapter(nhaxbAdapterSpinner);
                Spinner_nhaxb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       id_nhaxb = nhaxbAdapterSpinner.getItem(i).getId_nhaxb();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });


                //tạo danh sách dữ liệu cho spinner nhà xb
                List<Tacgia>tacgiaList = new ArrayList<>();
                Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM tacgia ");
                while (cursor1.moveToNext()){
                    tacgiaList.add(new Tacgia(cursor1.getInt(0), cursor1.getString(1), cursor1.getString(2)));
                }
                TacgiaAdapterSpinner tacgiaAdapterSpinner = new TacgiaAdapterSpinner(home_ql_sach.this, R.layout.item_spinner_select,tacgiaList);
                Spinner_tacgia.setAdapter(tacgiaAdapterSpinner);
                Spinner_tacgia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        id_tacgia = tacgiaAdapterSpinner.getItem(i).getId_tacgia();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                //tạo danh sách dữ liệu cho spinner nhà xb
                List<Theloai>theloaiList = new ArrayList<>();
                Cursor cursor2 = MainActivity.database.Getdata("SELECT * FROM theloai ");
                while (cursor2.moveToNext()){
                    theloaiList.add(new Theloai(cursor2.getInt(0), cursor2.getString(1), cursor2.getString(2)));
                }
                TheloaiAdapterSpinner theloaiAdapterSpinner = new TheloaiAdapterSpinner(home_ql_sach.this, R.layout.item_spinner_select,theloaiList);
                Spinner_theloai.setAdapter(theloaiAdapterSpinner);
                Spinner_theloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       id_theloai = theloaiAdapterSpinner.getItem(i).getId_theloai();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });




                btn_no_sach_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                btn_yes_sach_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        tensach = edt_add_tensach.getText().toString();
                        String namxb_s = edt_add_namxb.getText().toString();
                        String giasach_s = edt_add_giasach.getText().toString();
                        if(tensach.isEmpty()||namxb_s.isEmpty()||giasach_s.isEmpty()){
                            Toast.makeText(home_ql_sach.this, "Vui long nhap", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            namxb = Integer.parseInt(namxb_s);
                            giasach = Float.parseFloat(giasach_s);
                        }
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) img_hinhsach_add.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                    byte[] hinhsach  = byteArrayOutputStream.toByteArray();

                        dialog.cancel();
                        MainActivity.database.Insert_sach(tensach, id_nhaxb, id_theloai, id_tacgia, namxb, giasach, hinhsach);
                        Toast.makeText(home_ql_sach.this, "Thêm Thành công", Toast.LENGTH_SHORT).show();

                        rcv_sach = (RecyclerView) findViewById(R.id.rcv_sach);
                        rcv_sach.setLayoutManager(new LinearLayoutManager(home_ql_sach.this, LinearLayoutManager.VERTICAL, false));
                        List<Sach> sachList = new ArrayList<>();
                        Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM sach");
                        while (cursor1.moveToNext()){
                            sachList.add(new Sach(cursor1.getInt(0), cursor1.getString(1).toString().trim(),cursor1.getInt(2), cursor1.getInt(3), cursor1.getInt(4), cursor1.getInt(5),cursor1.getFloat(6),cursor1.getBlob(7)));
                        }
                        sachAdapter = new SachAdapter(home_ql_sach.this,sachList);
                        rcv_sach.setAdapter(sachAdapter);
                        sachAdapter.notifyDataSetChanged();
                    }
                });
                dialog.show();
            }
        });


        btn_previou_ql_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_ql_sach.this, home_ql_admin.class);
                startActivity(intent);
                finish();
            }
        });


        rcv_sach = (RecyclerView) findViewById(R.id.rcv_sach);
        rcv_sach.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Sach> sachList = new ArrayList<>();
        Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM sach");
        while (cursor1.moveToNext()){
            sachList.add(new Sach(cursor1.getInt(0), cursor1.getString(1).toString().trim(),cursor1.getInt(2), cursor1.getInt(3), cursor1.getInt(4), cursor1.getInt(5),cursor1.getFloat(6),cursor1.getBlob(7)));
        }
        sachAdapter = new SachAdapter(home_ql_sach.this,sachList);
        rcv_sach.setAdapter(sachAdapter);
        sachAdapter.notifyDataSetChanged();

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK &data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap resizedBitmap = resizeImage(bitmap,300);
                img_hinhsach_add.setImageBitmap(resizedBitmap);

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