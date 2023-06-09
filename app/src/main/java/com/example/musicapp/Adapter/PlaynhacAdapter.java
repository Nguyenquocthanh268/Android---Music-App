package com.example.musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Model.BaiHatModel;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaynhacAdapter extends  RecyclerView.Adapter<PlaynhacAdapter.ViewHolder> {

    Context context;
    View view;
    ArrayList<BaiHatModel> mangbaihat;

    public PlaynhacAdapter(Context context, ArrayList<BaiHatModel> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_playbaihat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHatModel baiHat = mangbaihat.get(position);
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
        holder.txtcasi.setText(baiHat.getTenCaSi());
        holder.txtindex.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtcasi,txttenbaihat,txtindex;
        public ViewHolder(View itemView){
            super(itemView);
            txtindex = itemView.findViewById(R.id.txtindex_playnhac);
            txtcasi = itemView.findViewById(R.id.txtcasi_playnhac);
            txttenbaihat = itemView.findViewById(R.id.txtten_playnhac);
        }
    }
}
