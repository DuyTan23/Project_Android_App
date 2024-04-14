package theloai;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.MainActivity;
import com.example.app_ban_sach.R;

import java.util.ArrayList;
import java.util.List;


import com.example.app_ban_sach.home_ql_theloai;


public class TheloaiAdapter extends RecyclerView.Adapter<TheloaiAdapter.TheloaiViewHolder> {

    private List<Theloai> theloaiList;

    public TheloaiAdapter(List<Theloai> theloaiList) {
        this.theloaiList = theloaiList;
    }

    @NonNull
    @Override
    public TheloaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theloai, parent,false);
        return new TheloaiViewHolder(view);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull TheloaiViewHolder holder, int position) {
        Theloai theloai = theloaiList.get(position);
        if(theloai == null){
            return;
        }

        holder.btn_update_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_theloai = theloai.getId_theloai();
                home_ql_theloai.dialog.setTitle("Hộp thoại sử lí");
                home_ql_theloai.dialog.setCancelable(false);
                home_ql_theloai.dialog.setContentView(R.layout.dialog_update_theloai);
                EditText edt_update_tentheloai = (EditText) home_ql_theloai.dialog.findViewById(R.id.edt_update_tentheloai);
                EditText edt_update_motatheloai = (EditText) home_ql_theloai.dialog.findViewById(R.id.edt_update_motatheloai);
                Button btn_yes_tacgia_update = (Button) home_ql_theloai.dialog.findViewById(R.id.btn_yes_theloai_update);
                Button btn_no_tacgia_update = (Button) home_ql_theloai.dialog.findViewById(R.id.btn_no_theloai_update);

                edt_update_tentheloai.setText(theloai.getTen_theloai().toString());
                edt_update_motatheloai.setText(theloai.getMota_theloai());
                home_ql_theloai.dialog.show();

                btn_no_tacgia_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        home_ql_theloai.dialog.cancel();
                    }
                });

                btn_yes_tacgia_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tentheloai = edt_update_tentheloai.getText().toString().trim();
                        String motatheloai = edt_update_motatheloai.getText().toString().trim();
                        MainActivity.database.QueryData("UPDATE theloai SET ten_theloai = '"+tentheloai+"', mota_theloai = '"+motatheloai+"' WHERE id_theloai = "+id_theloai+"");
                        Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM theloai");
                        List<Theloai> theloais = new ArrayList<>();
                        while (cursor1.moveToNext()){
                            theloais.add(new Theloai(cursor1.getInt(0), cursor1.getString(1).toString().trim(), cursor1.getString(2).toString().trim()));
                        }

                        home_ql_theloai.theloaiAdapter = new TheloaiAdapter(theloais);
                        home_ql_theloai.rcv_theloai.setAdapter(home_ql_theloai.theloaiAdapter);
                        home_ql_theloai.theloaiAdapter.notifyDataSetChanged();
                        home_ql_theloai.dialog.cancel();
                    }
                });

            }
        });

        holder.btn_delete_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_theloai = theloai.getId_theloai();
                List<Theloai> theloais = new ArrayList<>();
                MainActivity.database.QueryData("DELETE FROM theloai WHERE id_theloai = "+id_theloai+"");
                Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM theloai");
                while (cursor1.moveToNext()){
                    theloais.add(new Theloai(cursor1.getInt(0), cursor1.getString(1).toString().trim(), cursor1.getString(2).toString().trim()));
                }

                home_ql_theloai.theloaiAdapter = new TheloaiAdapter(theloais);
                home_ql_theloai.rcv_theloai.setAdapter(home_ql_theloai.theloaiAdapter);
                home_ql_theloai.theloaiAdapter.notifyDataSetChanged();
            }
        });
        holder.ten_theloai.setText(theloai.getTen_theloai().toString());
        holder.mota_theloai.setText(theloai.getMota_theloai().toString());
    }


    @Override
    public int getItemCount() {
        if(theloaiList != null){
            return theloaiList.size();
        }
        return 0;
    }

    public  class TheloaiViewHolder extends RecyclerView.ViewHolder{
        private TextView ten_theloai, mota_theloai;

        CardView btn_delete_theloai , btn_update_theloai;

        public TheloaiViewHolder(@NonNull View itemView) {
            super(itemView);
            ten_theloai = itemView.findViewById(R.id.ten_theloai);
            mota_theloai = itemView.findViewById(R.id.mota_theloai);
            btn_delete_theloai = itemView.findViewById(R.id.btn_delete_theloai);
            btn_update_theloai = itemView.findViewById(R.id.btn_update_theloai);
        }
    }
}
