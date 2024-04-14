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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.MainActivity;
import com.example.app_ban_sach.R;
import com.example.app_ban_sach.chitiet_sach_ql;
import com.example.app_ban_sach.home_ql_tacgia;

import java.io.InputStream;
import java.util.List;


public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    List<Sach> sachList;
    Context context;

    public SachAdapter(Context context,List<Sach> sachList) {
        this.context = context;

        this.sachList = sachList;
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent,false);
        return new SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        Sach sach = sachList.get(position);
        if(sach == null){
            return;
        }
        byte[] hinhsach = new byte[0];
        Cursor cursor =  MainActivity.database.Getdata("SELECT hinhsach FROM sach WHERE id_sach = "+sach.getId_sach()+"");
        while (cursor.moveToNext()){
            hinhsach = cursor.getBlob(0);
        }

        holder.btn_chitietsach_ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, chitiet_sach_ql.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Sach_sach",sach);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhsach, 0, hinhsach.length);
        holder.ten_sach.setText(sach.getTensach().toString());
        holder.img_hinhsach_item.setImageBitmap(bitmap2);

    }

    @Override
    public int getItemCount() {
        if(sachList != null){
            return  sachList.size();
        }
        return 0;
    }

    public class SachViewHolder extends RecyclerView.ViewHolder{
        ImageView img_hinhsach_item;
        TextView ten_sach;
        Button btn_chitietsach_ql;
        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hinhsach_item = itemView.findViewById(R.id.img_hinhsach_item);
            ten_sach = itemView.findViewById(R.id.ten_sach);
            btn_chitietsach_ql = itemView.findViewById(R.id.btn_chitietsach_ql);
        }
    }
}
