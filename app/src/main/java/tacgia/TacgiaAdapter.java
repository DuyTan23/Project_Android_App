package tacgia;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.MainActivity;
import com.example.app_ban_sach.R;
import com.example.app_ban_sach.home_ql_tacgia;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TacgiaAdapter extends RecyclerView.Adapter<TacgiaAdapter.TacgiaViewHolder>{

    private List<Tacgia> tacgiaList;

    public TacgiaAdapter(List<Tacgia> tacgiaList) {
        this.tacgiaList = tacgiaList;
    }
    public   Button btnOpenDatePicker_update_time_tacgia;

    @NonNull
    @Override
    public TacgiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tacgia, parent,false);
        return new TacgiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TacgiaViewHolder holder, int position) {
         Tacgia tacgia = tacgiaList.get(position);
        if(tacgia == null){
            return;
        }
        EditText edt_update_tentacgia;

        holder.btn_update_tacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_ql_tacgia.dialog.setTitle("Hộp thoại sử lí");
                home_ql_tacgia.dialog.setCancelable(false);
                home_ql_tacgia.dialog.setContentView(R.layout.dialog_update_tacgia);
                EditText edt_update_tentacgia  = (EditText) home_ql_tacgia.dialog.findViewById(R.id.edt_update_tentacgia);
                Button btnOpenDatePicker_update_time_tacgia =  home_ql_tacgia.dialog.findViewById(R.id.btnOpenDatePicker_update_time_tacgia);
                Button btn_yes_tacgia_update = home_ql_tacgia.dialog.findViewById(R.id.btn_yes_tacgia_update);
                Button btn_no_tacgia_update = home_ql_tacgia.dialog.findViewById(R.id.btn_no_tacgia_update);
                home_ql_tacgia.dialog.show();

                btnOpenDatePicker_update_time_tacgia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });


                int id_tacgia = tacgia.getId_tacgia();
                Cursor cursor = MainActivity.database.Getdata("SELECT * FROM tacgia WHERE id_tacgia = "+id_tacgia+"");
                while (cursor.moveToNext()){
                    edt_update_tentacgia.setText(tacgia.getTen_tacgia().toString());
                    btnOpenDatePicker_update_time_tacgia.setText(tacgia.getNgaysinh_tacgia());
                }

                btn_yes_tacgia_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.database.QueryData("UPDATE tacgia SET ten_tacgia = '"+edt_update_tentacgia.getText().toString().trim()+"', ngaysinh_tacgia = '"+btnOpenDatePicker_update_time_tacgia.getText().toString().trim()+"' WHERE id_tacgia = "+id_tacgia+"");
                        Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM tacgia");
                        List<Tacgia> tacgiaList1 = new ArrayList<>();
                        while (cursor1.moveToNext()){
                            tacgiaList1.add(new Tacgia(cursor1.getInt(0), cursor1.getString(1).toString().trim(), cursor1.getString(2).toString().trim()));
                        }
                        home_ql_tacgia.tacgiaAdapter = new TacgiaAdapter(tacgiaList1);
                        home_ql_tacgia.rcv_tacgia.setAdapter(home_ql_tacgia.tacgiaAdapter);
                        home_ql_tacgia.tacgiaAdapter.notifyDataSetChanged();
                        home_ql_tacgia.dialog.cancel();
                    }
                });
                btn_no_tacgia_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        home_ql_tacgia.dialog.cancel();
                    }
                });
            }
        });

        holder.btn_delete_tacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_tacgia = tacgia.getId_tacgia();
                List<Tacgia> tacgiaList1 = new ArrayList<>();
                MainActivity.database.QueryData("DELETE FROM tacgia WHERE id_tacgia = "+id_tacgia+"");
                Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM tacgia");
                while (cursor1.moveToNext()){
                    tacgiaList1.add(new Tacgia(cursor1.getInt(0), cursor1.getString(1).toString().trim(), cursor1.getString(2).toString().trim()));
                }
                home_ql_tacgia.tacgiaAdapter = new TacgiaAdapter(tacgiaList1);
                home_ql_tacgia.rcv_tacgia.setAdapter(home_ql_tacgia.tacgiaAdapter);
                home_ql_tacgia.tacgiaAdapter.notifyDataSetChanged();
            }
        });

        holder.ten_tacgia.setText(tacgia.getTen_tacgia());
        holder.ngaysinh_tacgia.setText(tacgia.getNgaysinh_tacgia());
    }

    @Override
    public int getItemCount() {
        if(tacgiaList != null){
            return tacgiaList.size();
        }
        return 0;
    }
    public  class TacgiaViewHolder extends RecyclerView.ViewHolder{
        private TextView ten_tacgia, ngaysinh_tacgia;
        private ConstraintLayout item_tacgia;
        CardView btn_delete_tacgia , btn_update_tacgia;
        private RecyclerView rcv_tacgia;
        Button btnOpenDatePicker_update_time_tacgia,btn_yes_tacgia_update, btn_no_tacgia_update;

        public TacgiaViewHolder(@NonNull View itemView) {
            super(itemView);
            rcv_tacgia = (RecyclerView) itemView.findViewById(R.id.rcv_tacgia);
            ten_tacgia = itemView.findViewById(R.id.ten_tacgia);
            ngaysinh_tacgia = itemView.findViewById(R.id.ngaysinh_tacgia);
            item_tacgia = itemView.findViewById(R.id.item_tacgia);
            btn_delete_tacgia = itemView.findViewById(R.id.btn_delete_tacgia);
            btn_update_tacgia = itemView.findViewById(R.id.btn_update_tacgia);
            btnOpenDatePicker_update_time_tacgia = item_tacgia.findViewById(R.id.btnOpenDatePicker_update_time_tacgia);
            btn_yes_tacgia_update = itemView.findViewById(R.id.btn_yes_tacgia_update);
            btn_no_tacgia_update = itemView.findViewById(R.id.btn_no_tacgia_update);
        }
    }
}
