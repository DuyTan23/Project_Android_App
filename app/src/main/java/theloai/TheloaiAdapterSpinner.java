package theloai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_ban_sach.R;

import java.util.List;

    public class TheloaiAdapterSpinner extends ArrayAdapter<Theloai> {


        public TheloaiAdapterSpinner(@NonNull Context context, int resource, @NonNull List<Theloai> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner_select,parent, false);
            TextView txtv_select = (TextView) convertView.findViewById(R.id.txtv_select);
            Theloai theloai = this.getItem(position);
            if(theloai != null){
                txtv_select.setText(theloai.getTen_theloai());
            }
            return convertView;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner_item,parent, false);
            TextView txtv_item = (TextView) convertView.findViewById(R.id.txtv_item);
            Theloai theloai = this.getItem(position);
            if(theloai != null){
                txtv_item.setText(theloai.getTen_theloai());
            }
            return convertView;
        }
    }


