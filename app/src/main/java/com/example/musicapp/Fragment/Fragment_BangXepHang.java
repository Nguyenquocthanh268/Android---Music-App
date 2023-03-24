package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Adapter.BangXepHangAdapter;
import com.example.musicapp.Adapter.ChuDeAdapter;
import com.example.musicapp.Model.BangXepHangModel;
import com.example.musicapp.Model.ChuDeModel;
import com.example.musicapp.R;

import java.util.ArrayList;

public class Fragment_BangXepHang  extends Fragment {
    View view;
    BangXepHangAdapter bangXepHangAdapter;
    RecyclerView recyclerViewbangxephang;
    TextView tenbangxephang;
    ArrayList<BangXepHangModel> mangbangxephang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bangxephang, container, false);
        recyclerViewbangxephang = view.findViewById(R.id.recyclerviewbangxephang);
        tenbangxephang = view.findViewById(R.id.txtbangxephang);
        mangbangxephang = new ArrayList<BangXepHangModel>();
        GetData();
        return view;
    }

    private void GetData() {
        bangXepHangAdapter = new BangXepHangAdapter(getActivity(), mangbangxephang);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewbangxephang.setLayoutManager(linearLayoutManager);
        recyclerViewbangxephang.setAdapter(bangXepHangAdapter);
        mangbangxephang.add(new BangXepHangModel("BXH01","Best of Chillies","https://i.scdn.co/image/ab67706c0000da84063dac83fe6ab9f4e676c089"));
        mangbangxephang.add(new BangXepHangModel("BXH02","Nhac Hoa","https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/cover/1/9/f/7/19f7601ee9899da6f07de343c4165e73.jpg"));
        mangbangxephang.add(new BangXepHangModel("BXH03","Trung Quan Idol","https://icdn.dantri.com.vn/thumb_w/770/2022/05/03/ca-si-trung-quan-1651574722985.png"));
        bangXepHangAdapter.notifyDataSetChanged();

    }
}
