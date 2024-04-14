package com.example.app_ban_sach;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import tacgia.Tacgia;
import tacgia.TacgiaAdapterSpinner;
import theloai.Theloai;
import theloai.TheloaiAdapterSpinner;

public class chitiet_sach_ql extends AppCompatActivity {
    ImageView img_hinhsach_ql_chitiet;
    ImageView img_hinhsach_update,btn_previou_ql_sach;
    TextView txtv_tensach_ql_chitiet,txtv_tennhaxb_ql_chitiet,txtv_tentheloai_ql_chitiet,txtv_tentacgia_ql_chitiet,txtv_namxb_ql_chitiet, txtv_giasach_ql_chitiet;

    int id_nhaxb, id_theloai, id_tacgia;
    Button btn_delete_ql_sach, btn_update_ql_sach;
    int REQUEST_CODE_FOLDER = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sach_ql);
        img_hinhsach_ql_chitiet = findViewById(R.id.img_hinhsach_ql_chitiet);
        txtv_tensach_ql_chitiet = findViewById(R.id.txtv_tensach_ql_chitiet);
        txtv_tennhaxb_ql_chitiet = findViewById(R.id.txtv_tennhaxb_ql_chitiet);
        txtv_tentheloai_ql_chitiet = findViewById(R.id.txtv_tentheloai_ql_chitiet);
        txtv_tentacgia_ql_chitiet = findViewById(R.id.txtv_tentacgia_ql_chitiet);
        txtv_namxb_ql_chitiet = findViewById(R.id.txtv_namxb_ql_chitiet);
        txtv_giasach_ql_chitiet = findViewById(R.id.txtv_giasach_ql_chitiet);
        btn_delete_ql_sach = findViewById(R.id.btn_delete_ql_sach);
        btn_update_ql_sach = findViewById(R.id.btn_update_ql_sach);
        btn_previou_ql_sach = findViewById(R.id.btn_previou_ql_sach);
        btn_previou_ql_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(chitiet_sach_ql.this, home_ql_sach.class);
                startActivity(i);
                finish();
            }
        });


        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Sach sach = (Sach) bundle.get("Sach_sach");
        int id_sach = sach.getId_sach();
        byte[] hinhsach = new byte[0];
        Cursor cursor =  MainActivity.database.Getdata("SELECT * FROM sach WHERE id_sach = "+id_sach+"");
        while (cursor.moveToNext()){
            txtv_tensach_ql_chitiet.setText(cursor.getString(1));
            id_nhaxb = cursor.getInt(2);
            id_theloai = cursor.getInt(3);
            id_tacgia = cursor.getInt(4);
            txtv_namxb_ql_chitiet.setText(String.valueOf(cursor.getInt(5)));
            txtv_giasach_ql_chitiet.setText(String.valueOf(cursor.getFloat(6)));
            hinhsach = cursor.getBlob(7);
        }
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhsach, 0, hinhsach.length);
        img_hinhsach_ql_chitiet.setImageBitmap(bitmap2);
        Cursor cursor1 = MainActivity.database.Getdata("SELECT ten_nhaxb FROM nhaxb WHERE id_nhaxb = "+id_nhaxb+"");
        while (cursor1.moveToNext()){
            txtv_tennhaxb_ql_chitiet.setText(cursor1.getString(0));
        }
        Cursor cursor2 = MainActivity.database.Getdata("SELECT ten_theloai FROM theloai WHERE id_theloai = "+id_theloai+"");
        while (cursor2.moveToNext()){
            txtv_tentheloai_ql_chitiet.setText(cursor2.getString(0));
        }
        Cursor cursor3= MainActivity.database.Getdata("SELECT ten_tacgia FROM tacgia WHERE id_tacgia = "+id_tacgia+"");
        while (cursor3.moveToNext()){
            txtv_tentacgia_ql_chitiet.setText(cursor3.getString(0));
        }

        btn_delete_ql_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(chitiet_sach_ql.this);
                dialog.setTitle("Hộp thoại sử lí");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_xacnhan_delete);
                Button btn_yes_delete = dialog.findViewById(R.id.btn_yes_delete);
                Button btn_no_delete = dialog.findViewById(R.id.btn_no_delete);
                dialog.show();
                btn_yes_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.database.QueryData("DELETE FROM sach WHERE id_sach = "+id_sach+"");
                        Intent intent   = new Intent(chitiet_sach_ql.this, home_ql_sach.class);
                        startActivity(intent);
                        finish();
                        dialog.cancel();
                    }
                });
                btn_no_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });

        btn_update_ql_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(chitiet_sach_ql.this);
                dialog.setTitle("Hộp thoại sử lí");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_add_sach);
                dialog.show();
                TextView txtv_title_ql_sach_form = dialog.findViewById(R.id.txtv_title_ql_sach_form);
                txtv_title_ql_sach_form.setText("Form cập nhật sách");

                EditText edt_add_tensach = dialog.findViewById(R.id.edt_add_tensach);
                EditText edt_add_namxb = dialog.findViewById(R.id.edt_add_namxb);
                EditText edt_add_giasach = dialog.findViewById(R.id.edt_add_giasach);

                Spinner Spinner_nhaxb = (Spinner) dialog.findViewById(R.id.Spinner_nhaxb);
                Spinner Spinner_tacgia = (Spinner) dialog.findViewById(R.id.Spinner_tacgia);
                Spinner Spinner_theloai = (Spinner) dialog.findViewById(R.id.Spinner_theloai);

                Button btn_yes_sach_add, btn_no_sach_add;
                btn_yes_sach_add = dialog.findViewById(R.id.btn_yes_sach_add);
                btn_no_sach_add = dialog.findViewById(R.id.btn_no_sach_add);

                img_hinhsach_update = (ImageView) dialog.findViewById(R.id.img_hinhsach_add);
                ImageButton btn_open_folder_img_hinh_add = (ImageButton) dialog.findViewById(R.id.btn_open_folder_img_hinh_add);

                btn_open_folder_img_hinh_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent,REQUEST_CODE_FOLDER);
                    }
                });



                byte[] hinhsach = new byte[0];
                Cursor cursor =  MainActivity.database.Getdata("SELECT * FROM sach WHERE id_sach = "+id_sach+"");
                while (cursor.moveToNext()){
                    edt_add_tensach.setText(cursor.getString(1));
                    id_nhaxb = cursor.getInt(2);
                    id_theloai = cursor.getInt(3);
                    id_tacgia = cursor.getInt(4);
                    edt_add_namxb.setText(String.valueOf(cursor.getInt(5)));
                    edt_add_giasach.setText(String.valueOf(cursor.getFloat(6)));
                    hinhsach = cursor.getBlob(7);
                }
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhsach, 0, hinhsach.length);
                img_hinhsach_update.setImageBitmap(bitmap2);

                List<Nhaxb> nhaxbList_spinner = new ArrayList<>();

                Cursor cursor5 = MainActivity.database.Getdata("SELECT * FROM nhaxb ");
                while (cursor5.moveToNext()){
                    nhaxbList_spinner.add(new Nhaxb(cursor5.getInt(0), cursor5.getString(1), cursor5.getString(2), cursor5.getString(3)));
                }
                int dem = -1;
                for(int i = 0; i<nhaxbList_spinner.size(); i++){
                    if(id_nhaxb == nhaxbList_spinner.get(i).getId_nhaxb()){
                        dem = i;
                    }
                }
                NhaxbAdapterSpinner nhaxbAdapterSpinner = new NhaxbAdapterSpinner(chitiet_sach_ql.this, R.layout.item_spinner_select,nhaxbList_spinner);
                Spinner_nhaxb.setAdapter(nhaxbAdapterSpinner);
                Spinner_nhaxb.setSelection(dem,true);
                Spinner_nhaxb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        id_nhaxb = nhaxbAdapterSpinner.getItem(i).getId_nhaxb();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                List<Tacgia> tacgiaList = new ArrayList<>();
                Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM tacgia ");
                while (cursor1.moveToNext()){
                    tacgiaList.add(new Tacgia(cursor1.getInt(0), cursor1.getString(1), cursor1.getString(2)));
                }

                int dem1 = -1;
                for(int i = 0; i<tacgiaList.size(); i++){
                    if(id_tacgia == tacgiaList.get(i).getId_tacgia()){
                        dem1 = i;
                    }
                }
                TacgiaAdapterSpinner tacgiaAdapterSpinner = new TacgiaAdapterSpinner(chitiet_sach_ql.this, R.layout.item_spinner_select,tacgiaList);
                Spinner_tacgia.setAdapter(tacgiaAdapterSpinner);
                Spinner_tacgia.setSelection(dem1, true);
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

                int dem2 = -1;
                for(int i = 0; i<theloaiList.size(); i++){
                    if(id_theloai == theloaiList.get(i).getId_theloai()){
                        dem2 = i;
                    }
                }
                TheloaiAdapterSpinner theloaiAdapterSpinner = new TheloaiAdapterSpinner(chitiet_sach_ql.this, R.layout.item_spinner_select,theloaiList);
                Spinner_theloai.setAdapter(theloaiAdapterSpinner);
                Spinner_theloai.setSelection(dem2, true);
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
                        String tensach, namxb, giasach;
                        tensach = edt_add_tensach.getText().toString().trim();
                        namxb =edt_add_namxb.getText().toString();
                        giasach = edt_add_giasach.getText().toString();

                        BitmapDrawable bitmapDrawable = (BitmapDrawable) img_hinhsach_update.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                        byte[] hinhsach_up  = byteArrayOutputStream.toByteArray();
                        MainActivity.database.QueryData("UPDATE sach SET tensach = '"+tensach+"', id_nhaxb = '"+id_nhaxb+"', id_theloai = '"+id_theloai+"', id_tacgia = '"+id_tacgia+"', nam_xb = '"+namxb+"', giasach = '"+giasach+"' WHERE id_sach = "+id_sach+"");
                        MainActivity.database.UpdateSach(hinhsach_up,id_sach);
                        dialog.cancel();


                        byte[] hinhsach = new byte[0];
                        Cursor cursor =  MainActivity.database.Getdata("SELECT * FROM sach WHERE id_sach = "+id_sach+"");
                        while (cursor.moveToNext()){
                            txtv_tensach_ql_chitiet.setText(cursor.getString(1));
                            id_nhaxb = cursor.getInt(2);
                            id_theloai = cursor.getInt(3);
                            id_tacgia = cursor.getInt(4);
                            txtv_namxb_ql_chitiet.setText(String.valueOf(cursor.getInt(5)));
                            txtv_giasach_ql_chitiet.setText(String.valueOf(cursor.getFloat(6)));
                            hinhsach = cursor.getBlob(7);
                        }
                        Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhsach, 0, hinhsach.length);
                        img_hinhsach_ql_chitiet.setImageBitmap(bitmap2);
                        Cursor cursor1 = MainActivity.database.Getdata("SELECT ten_nhaxb FROM nhaxb WHERE id_nhaxb = "+id_nhaxb+"");
                        while (cursor1.moveToNext()){
                            txtv_tennhaxb_ql_chitiet.setText(cursor1.getString(0));
                        }
                        Cursor cursor2 = MainActivity.database.Getdata("SELECT ten_theloai FROM theloai WHERE id_theloai = "+id_theloai+"");
                        while (cursor2.moveToNext()){
                            txtv_tentheloai_ql_chitiet.setText(cursor2.getString(0));
                        }
                        Cursor cursor3= MainActivity.database.Getdata("SELECT ten_tacgia FROM tacgia WHERE id_tacgia = "+id_tacgia+"");
                        while (cursor3.moveToNext()){
                            txtv_tentacgia_ql_chitiet.setText(cursor3.getString(0));
                        }
                    }
                });
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK &data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap resizedBitmap = resizeImage(bitmap,300);
                img_hinhsach_update.setImageBitmap(resizedBitmap);

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