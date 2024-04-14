package quangcao_index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_sach.R;

import java.util.List;

public class QuangCaoAdapter extends RecyclerView.Adapter<QuangCaoAdapter.QuangCaoViewHolder>{
    private List<QuangCao> quangCaoList;
    private Context context;

    public QuangCaoAdapter(Context context,List<QuangCao> quangCaoList) {
        this.quangCaoList = quangCaoList;
        this.context = context;
    }

    public void setData(List<QuangCao>quangCaoList){
        this.quangCaoList = quangCaoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuangCaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quangcao, parent,false);
        return new QuangCaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuangCaoViewHolder holder, int position) {
        QuangCao quangCao = quangCaoList.get(position);
        if(quangCao == null){
            return;
        }
        holder.img_qc.setImageResource(quangCao.getImg_qc());
        holder.tieude_qc.setText(quangCao.getTieude_qc());
        holder.noidung_qc.setText(quangCao.getNoidung_qc());
    }

    @Override
    public int getItemCount() {
        if(quangCaoList != null){
            return quangCaoList.size();
        }
        return 0;
    }

    public  class QuangCaoViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_qc;
        private TextView tieude_qc, noidung_qc;

        public QuangCaoViewHolder(@NonNull View itemView) {
            super(itemView);
            img_qc = itemView.findViewById(R.id.img_qc);
            tieude_qc = itemView.findViewById(R.id.tieude_qc);
            noidung_qc = itemView.findViewById(R.id.noidung_qc);
        }
    }
}
