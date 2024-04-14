package giohang;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.MainActivity;
import com.example.app_ban_sach.R;
import com.example.app_ban_sach.giohang_ca_nhan;
import com.example.app_ban_sach.home_ql_nhaxb;
import com.example.app_ban_sach.login_ngdung;

import java.util.ArrayList;
import java.util.List;


public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.GiohangViewHolder>{
    private List<Giohang> giohangList;

    public GiohangAdapter( List<Giohang> giohangList) {
        this.giohangList = giohangList;
    }

    @NonNull
    @Override
    public GiohangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent,false);
        return new GiohangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiohangViewHolder holder, int position) {
        Giohang giohang = giohangList.get(position);
        if(giohang == null){
            return;
        }
        int id_giohang = giohang.getId_giohang();
        int id_sach = giohang.getId_sach();
        String tensach = null;
        float giasach = 0;
        byte[] hinhsach = new byte[0];
        Cursor cursor = MainActivity.database.Getdata("SELECT tensach, giasach, hinhsach FROM sach WHERE id_sach = "+id_sach+"");
        while (cursor.moveToNext()){
            tensach = cursor.getString(0);
            giasach = cursor.getFloat(1);
            hinhsach = cursor.getBlob(2);
        }
        holder.btn_delete_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.database.QueryData("DELETE FROM gio_hang_n WHERE id_giohang = "+id_giohang+"");
                List<Giohang> giohangList1 = new ArrayList<>();
                Cursor cursor = MainActivity.database.Getdata("SELECT * FROM gio_hang_n WHERE id_ngdung  = "+login_ngdung.kt_login+"");
                while (cursor.moveToNext()){
                    giohangList1.add(new Giohang(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2)));
                }
                giohang_ca_nhan.giohangAdapter = new GiohangAdapter(giohangList1);
                giohang_ca_nhan.rcv_giohang.setAdapter(giohang_ca_nhan.giohangAdapter);
                giohang_ca_nhan.giohangAdapter.notifyDataSetChanged();

            }
        });
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhsach, 0, hinhsach.length);
        holder.txtv_tensach_hiohang.setText(tensach);
        holder.txtv_giasach_giohang.setText(String.valueOf(giasach)+" Đ");
        holder.img_hinhsach_giohang.setImageBitmap(bitmap2);
    }

    @Override
    public int getItemCount() {
        if(giohangList != null){
            return giohangList.size();
        }
        return 0;
    }

    public class GiohangViewHolder extends RecyclerView.ViewHolder {
        ImageView img_hinhsach_giohang;
        TextView txtv_tensach_hiohang, txtv_giasach_giohang;
        CardView btn_delete_giohang;
        public GiohangViewHolder(@NonNull View itemView) {
            super(itemView);
            txtv_tensach_hiohang = itemView.findViewById(R.id.txtv_tensach_giohang);
            txtv_giasach_giohang = itemView.findViewById(R.id.txtv_giasach_giohang);
            img_hinhsach_giohang = itemView.findViewById(R.id.img_hinhsach_giohang);
            btn_delete_giohang = itemView.findViewById(R.id.btn_delete_giohang);
        }
    }
}
