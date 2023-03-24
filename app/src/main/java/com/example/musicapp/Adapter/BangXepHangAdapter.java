package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Model.BangXepHangModel;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BangXepHangAdapter extends RecyclerView.Adapter<BangXepHangAdapter.ViewHolder>{
    Context context;
    ArrayList<BangXepHangModel> mangbangxephang;
    View view;

    public BangXepHangAdapter(Context context, ArrayList<BangXepHangModel> mangbangxephang) {
        this.context = context;
        this.mangbangxephang = mangbangxephang;
    }

    @NonNull
    @Override
    public BangXepHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_bangxephang,parent, false);
        return new BangXepHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BangXepHangAdapter.ViewHolder holder, int position) {
        BangXepHangModel bangXepHang = mangbangxephang.get(position);
        holder.txtbangxephang.setText(bangXepHang.getTenBangXepHang());
        Picasso.get().load(bangXepHang.getHinhBangXepHang()).into(holder.imgbangxephang);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangbangxephang != null ? mangbangxephang.size() : 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgbangxephang;
        TextView txtbangxephang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbangxephang = itemView.findViewById(R.id.imageviewbangxephang);
            txtbangxephang = itemView.findViewById(R.id.textviewbangxephang);
        }
    }
}
