package yeu_thich;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.MainActivity;
import com.example.app_ban_sach.R;
import com.example.app_ban_sach.chitiet_sach_home;
import com.example.app_ban_sach.giohang_ca_nhan;
import com.example.app_ban_sach.login_ngdung;
import com.example.app_ban_sach.sach_yeuthich;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import giohang.Giohang;
import giohang.GiohangAdapter;


public class YeuthichAdapter extends RecyclerView.Adapter<YeuthichAdapter.YeuthichViewHolder>{
    Context context;
    List<Yeuthich> yeuthichList = new ArrayList<>();

    public YeuthichAdapter(Context context, List<Yeuthich> yeuthichList) {
        this.context = context;
        this.yeuthichList = yeuthichList;
    }

    @NonNull
    @Override
    public YeuthichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yeuthich, parent,false);
        return new YeuthichViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YeuthichViewHolder holder, int position) {
        Yeuthich yeuthich = yeuthichList.get(position);
        if(yeuthich == null){
            return;
        }
        int id_sach = yeuthich.getId_sach();
        String tensach = null;
        float giasach = 0;
        byte[] hinhsach = new byte[0];
        Cursor cursor = MainActivity.database.Getdata("SELECT tensach, giasach, hinhsach FROM sach WHERE id_sach = "+id_sach+"");
        while (cursor.moveToNext()){
            tensach = cursor.getString(0);
            giasach = cursor.getFloat(1);
            hinhsach = cursor.getBlob(2);
        }

//        holder.item_books_yeuthich.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, chitiet_sach_home.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("Yeuthich", (Serializable) yeuthich);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });
        holder.btn_delete_yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.database.QueryData("DELETE FROM yeuthich WHERE id_sach = "+id_sach+" AND id_ngdung = "+login_ngdung.kt_login+"");
                List<Yeuthich> yeuthichArrayList = new ArrayList<>();
                Cursor cursor = MainActivity.database.Getdata("SELECT * FROM yeuthich WHERE id_ngdung  = "+ login_ngdung.kt_login+"");
                while (cursor.moveToNext()){
                    yeuthichArrayList.add(new Yeuthich(cursor.getInt(0),cursor.getInt(1)));
                }
                sach_yeuthich.yeuthichAdapter = new YeuthichAdapter(context,yeuthichArrayList);
                sach_yeuthich.rcv_yeuthich.setAdapter(sach_yeuthich.yeuthichAdapter);
                sach_yeuthich.yeuthichAdapter.notifyDataSetChanged();

            }
        });
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhsach, 0, hinhsach.length);
        holder.txtv_tensach_yeuthich.setText(tensach);
        holder.txtv_giasach_yeuthich.setText(String.valueOf(giasach)+" Đ");
        holder.img_hinhsach_yeuthich.setImageBitmap(bitmap2);
    }

    @Override
    public int getItemCount() {
        if(yeuthichList != null){
            return  yeuthichList.size();
        }
        return 0;
    }

    public  class  YeuthichViewHolder extends RecyclerView.ViewHolder {
        ImageView img_hinhsach_yeuthich;
        LinearLayout item_books_yeuthich;
        TextView txtv_tensach_yeuthich, txtv_giasach_yeuthich;
        Button btn_delete_yeuthich;
        public YeuthichViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hinhsach_yeuthich = itemView.findViewById(R.id.img_hinhsach_yeuthich);
            txtv_tensach_yeuthich = itemView.findViewById(R.id.txtv_tensach_yeuthich);
            txtv_giasach_yeuthich = itemView.findViewById(R.id.txtv_giasach_yeuthich);
            btn_delete_yeuthich = itemView.findViewById(R.id.btn_delete_yeuthich);

        }
    }
}
