package com.example.app_ban_sach;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }



    public Cursor Getdata(String sql){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return  sqLiteDatabase.rawQuery(sql,null);
    }
    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    public void InsertNgdung( String ten_ngdung,String user_ngdung, String pass_ngdung,String sdt_ngdung,String diachi_ngdung, byte[]hinh, String gioitinh_ngdung, String ngaysinh_ngdung, String diachi_nhanhang ){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO ng_dung VALUES(null, ?, ?, ?, ?, ?, ?,?, ?, ? )";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,ten_ngdung);
        sqLiteStatement.bindString(2,user_ngdung);
        sqLiteStatement.bindString(3,pass_ngdung);
        sqLiteStatement.bindString(4, sdt_ngdung);
        sqLiteStatement.bindString(5,diachi_ngdung);
        sqLiteStatement.bindBlob(6,hinh);
        sqLiteStatement.bindString(7, gioitinh_ngdung);
        sqLiteStatement.bindString(8, ngaysinh_ngdung);
        sqLiteStatement.bindString(9, diachi_nhanhang);
        sqLiteStatement.executeInsert();

    }

    public  void Insert_sach(String tensach, int id_nhaxb, int id_theloai, int id_tacgia, int nam_xb, float giasach, byte[] hinhsach){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO sach VALUES(null,?,?,?,?,?,?,?)";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,tensach);
        sqLiteStatement.bindLong(2, id_nhaxb);
        sqLiteStatement.bindLong(3, id_theloai);
        sqLiteStatement.bindLong(4, id_tacgia);
        sqLiteStatement.bindLong(5, nam_xb);
        sqLiteStatement.bindDouble(6, giasach);
        sqLiteStatement.bindBlob(7, hinhsach);
        sqLiteStatement.executeInsert();

    }

    public void UpdateNgdung(byte[]hinh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE ng_dung SET avatar_ngdung = ? WHERE id_ngdung = "+login_ngdung.kt_login+"";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindBlob(1,hinh);

        sqLiteStatement.executeUpdateDelete();
    }
    public void UpdateSach(byte[]hinh, int id_sach){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE sach SET hinhsach = ? WHERE id_sach = "+id_sach+"";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindBlob(1,hinh);

        sqLiteStatement.executeUpdateDelete();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
