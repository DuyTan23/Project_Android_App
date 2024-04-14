package nhaxb;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.MainActivity;
import com.example.app_ban_sach.R;

import java.util.ArrayList;
import java.util.List;
import com.example.app_ban_sach.home_ql_nhaxb;



public class NhaxbAdapter extends RecyclerView.Adapter<NhaxbAdapter.NhaxbViewHolder>{

    private List<Nhaxb> nhaxbList;

    public NhaxbAdapter(List<Nhaxb> nhaxbList) {
        this.nhaxbList = nhaxbList;
    }

    @NonNull
    @Override
    public NhaxbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhaxb, parent,false);
        return new NhaxbViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhaxbViewHolder holder, int position) {
        Nhaxb nhaxb = nhaxbList.get(position);
        if(nhaxb == null){
            return;
        }

        holder.btn_delete_nhaxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_nhaxb = nhaxb.getId_nhaxb();
                List<Nhaxb> nhaxbs = new ArrayList<>();

                MainActivity.database.QueryData("DELETE FROM nhaxb WHERE id_nhaxb = "+id_nhaxb+"");

                Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM nhaxb");
                while (cursor1.moveToNext()){
                    nhaxbs.add(new Nhaxb(cursor1.getInt(0), cursor1.getString(1).toString().trim(), cursor1.getString(2).toString().trim(), cursor1.getString(3).toString().trim()));
                }
                home_ql_nhaxb.nhaxbAdapter = new NhaxbAdapter(nhaxbs);
                home_ql_nhaxb.rcv_nhaxb.setAdapter(home_ql_nhaxb.nhaxbAdapter);
                home_ql_nhaxb.nhaxbAdapter.notifyDataSetChanged();
                home_ql_nhaxb.dialog.cancel();
            }
        });

        holder.btn_update_nhaxb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_nhaxb = nhaxb.getId_nhaxb();
                home_ql_nhaxb.dialog.setTitle("Hộp thoại sử lí");
                home_ql_nhaxb.dialog.setCancelable(false);
                home_ql_nhaxb.dialog.setContentView(R.layout.dialog_update_nhaxb);
                home_ql_nhaxb.dialog.show();
                EditText edt_update_tennhaxb  = (EditText) home_ql_nhaxb.dialog.findViewById(R.id.edt_update_tennhaxb);
                EditText edt_update_sdtnhaxb = (EditText) home_ql_nhaxb.dialog.findViewById(R.id.edt_update_sdtnhaxb);
                EditText edt_update_diachinhaxb  = (EditText) home_ql_nhaxb.dialog.findViewById(R.id.edt_update_diachinhaxb);
                Button btn_yes_nhaxb_update = (Button) home_ql_nhaxb.dialog.findViewById(R.id.btn_yes_nhaxb_update);
                Button btn_no_nhaxb_update = (Button) home_ql_nhaxb.dialog.findViewById(R.id.btn_no_nhaxb_update);

                edt_update_tennhaxb.setText(nhaxb.getTen_nhaxb().toString());
                edt_update_sdtnhaxb.setText(nhaxb.getSdt_nhaxb().toString());
                edt_update_diachinhaxb.setText(nhaxb.getDiachi_nhaxb().toString());

                btn_yes_nhaxb_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tennhaxb = edt_update_tennhaxb.getText().toString();
                        String sdtnhaxb = edt_update_sdtnhaxb.getText().toString();
                        String diachinhaxb = edt_update_diachinhaxb.getText().toString();
                        List<Nhaxb> nhaxbs = new ArrayList<>();
                        MainActivity.database.QueryData("UPDATE nhaxb SET ten_nhaxb = '"+tennhaxb+"', sdt_nhaxb = '"+sdtnhaxb+"', diachi_nhaxb = '"+diachinhaxb+"' WHERE id_nhaxb = "+id_nhaxb+"");
                        Cursor cursor1 = MainActivity.database.Getdata("SELECT * FROM nhaxb");
                        while (cursor1.moveToNext()){
                            nhaxbs.add(new Nhaxb(cursor1.getInt(0), cursor1.getString(1).toString().trim(), cursor1.getString(2).toString().trim(), cursor1.getString(3).toString().trim()));
                        }
                        home_ql_nhaxb.nhaxbAdapter = new NhaxbAdapter(nhaxbs);
                        home_ql_nhaxb.rcv_nhaxb.setAdapter(home_ql_nhaxb.nhaxbAdapter);
                        home_ql_nhaxb.nhaxbAdapter.notifyDataSetChanged();
                        home_ql_nhaxb.dialog.cancel();
                    }
                });
                btn_no_nhaxb_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        home_ql_nhaxb.dialog.cancel();
                    }
                });

            }
        });
        holder.ten_nhaxb.setText(nhaxb.getTen_nhaxb().toString());
        holder.sdt_nhaxb.setText(nhaxb.getSdt_nhaxb().toString());
        holder.diachi_nhaxb.setText(nhaxb.getDiachi_nhaxb().toString());
    }

    @Override
    public int getItemCount() {
        if(nhaxbList != null){
            return nhaxbList.size();
        }
        return 0;
    }

    public class NhaxbViewHolder extends RecyclerView.ViewHolder {
        TextView ten_nhaxb, sdt_nhaxb, diachi_nhaxb;
        CardView btn_delete_nhaxb  , btn_update_nhaxb ;
        public NhaxbViewHolder(@NonNull View itemView) {
            super(itemView);
            ten_nhaxb = itemView.findViewById(R.id.ten_nhaxb);
            sdt_nhaxb = itemView.findViewById(R.id.sdt_nhaxb);
            diachi_nhaxb = itemView.findViewById(R.id.diachi_nhaxb);
            btn_delete_nhaxb = itemView.findViewById(R.id.btn_delete_nhaxb);
            btn_update_nhaxb = itemView.findViewById(R.id.btn_update_nhaxb);
        }
    }
}
