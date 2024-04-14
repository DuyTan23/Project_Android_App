package sach;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.MainActivity;
import com.example.app_ban_sach.R;
import com.example.app_ban_sach.chitiet_sach_home;
import com.example.app_ban_sach.chitiet_sach_ql;

import java.io.InputStream;
import java.util.List;

public class SachAdapter_danhmuc extends RecyclerView.Adapter<SachAdapter_danhmuc.SachViewHolderDanhMuc>{
    Context context;
    List<Sach> sachList;


    public SachAdapter_danhmuc(Context context, List<Sach> sachList) {
        this.context = context;
        this.sachList = sachList;
    }

    @NonNull
    @Override
    public SachViewHolderDanhMuc onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_books_home, parent,false);
        return new SachViewHolderDanhMuc(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolderDanhMuc holder, int position) {
        Sach sach = sachList.get(position);
        if(sach == null){
            return;
        }
        byte[] hinhsach = new byte[0];
        Cursor cursor =  MainActivity.database.Getdata("SELECT hinhsach FROM sach WHERE id_sach = "+sach.getId_sach()+"");
        while (cursor.moveToNext()){
            hinhsach = cursor.getBlob(0);
        }

        holder.item_books_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, chitiet_sach_home.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Sach_sach",sach);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhsach, 0, hinhsach.length);
        holder.txtv_tensach_dahmuc.setText(sach.getTensach().toString());
        holder.txtv_giasach_danhmuc.setText(String.valueOf(sach.getGiasach())+" Đ");
        Bitmap resizedBitmap = resizeImage(bitmap2,355,300);
        holder.img_hinhsach_danhmuc.setImageBitmap(resizedBitmap);
    }

    @Override
    public int getItemCount() {
        if(sachList != null){
            return  sachList.size();
        }
        return 0;
    }

    public  class  SachViewHolderDanhMuc extends RecyclerView.ViewHolder{
        ImageView img_hinhsach_danhmuc;
        LinearLayout item_books_home;
        TextView txtv_tensach_dahmuc, txtv_giasach_danhmuc;
        public SachViewHolderDanhMuc(@NonNull View itemView) {
            super(itemView);
            img_hinhsach_danhmuc = itemView.findViewById(R.id.img_hinhsach_danhmuc);
            txtv_tensach_dahmuc = itemView.findViewById(R.id.txtv_tensach_danhmuc);
            txtv_giasach_danhmuc = itemView.findViewById(R.id.txtv_giasach_danhmuc);
            item_books_home = itemView.findViewById(R.id.item_books_home);

        }
    }
    private Bitmap resizeImage(Bitmap originalBitmap, int dai, int cao) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();

        // Chọn kích thước mới sao cho chiều dài và chiều rộng đều bằng 'size'
        int newWidth = dai;
        int newHeight = cao;

        float scaleWidth = ((float) dai) / width;
        float scaleHeight = ((float) cao) / height;

        // Sử dụng ma trận để resize ảnh
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        // Tạo ảnh mới sau khi đã resize
        Bitmap resizedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, width, height, matrix, false);

        // Cắt thành hình vuông
        int cropStartX = (resizedBitmap.getWidth() - dai) / 2;
        int cropStartY = (resizedBitmap.getHeight() - cao) / 2;
        Bitmap croppedBitmap = Bitmap.createBitmap(resizedBitmap, cropStartX, cropStartY, dai, cao);
        return croppedBitmap;
    }
}
