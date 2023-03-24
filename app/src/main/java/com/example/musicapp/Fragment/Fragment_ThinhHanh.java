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

import com.example.musicapp.Adapter.ThinhHanhAdapter;
import com.example.musicapp.Model.NgheSiModel;
import com.example.musicapp.Model.ThinhHanhModel;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ThinhHanh extends Fragment {
    View view;
    ThinhHanhAdapter thinhHanhAdapter;
    RecyclerView recyclerViewthinhhanh;
    TextView tenThinhHanh;
    ArrayList<ThinhHanhModel> mangthinhhanh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thinhhanh, container, false);
        recyclerViewthinhhanh = view.findViewById(R.id.recyclerviewthinhhanh);
        tenThinhHanh = view.findViewById(R.id.txtthinhhanh);
        mangthinhhanh = new ArrayList<ThinhHanhModel>();
        GetData();
        return view;
    }

    private void GetData() {
        thinhHanhAdapter = new ThinhHanhAdapter(getActivity(), mangthinhhanh);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewthinhhanh.setLayoutManager(linearLayoutManager);
        recyclerViewthinhhanh.setAdapter(thinhHanhAdapter);
        mangthinhhanh.add(new ThinhHanhModel("TH01","Nhạc Hoa Thịnh Hành",
                "https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/cover/1/9/f/7/19f7601ee9899da6f07de343c4165e73.jpg"));
        mangthinhhanh.add(new ThinhHanhModel("TH02","Album của Vũ","https://2sao.vietnamnetjsc.vn/images/2022/09/15/18/19/v1.png"));
        mangthinhhanh.add(new ThinhHanhModel("TH03","Album truyền thống","https://images2.thanhnien.vn/uploaded/congthang/2018_01_22/biamylinh2_UHWN.jpg?width=500"));
        thinhHanhAdapter.notifyDataSetChanged();
    }
}
