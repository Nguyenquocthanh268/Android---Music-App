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

import com.example.musicapp.Adapter.NgheSiAdapter;
import com.example.musicapp.Model.NgheSiModel;
import com.example.musicapp.Model.PlaylistModel;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_NgheSi extends Fragment {
    View view;
    NgheSiAdapter ngheSiAdapter;
    RecyclerView recyclerViewNgheSi;
    TextView tenNgheSi;
    ArrayList<NgheSiModel> mangnghesi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nghesi, container, false);
        recyclerViewNgheSi = view.findViewById(R.id.recyclerviewnghesi);
        tenNgheSi = view.findViewById(R.id.txtnghesi);
        mangnghesi = new ArrayList<NgheSiModel>();
        GetData();
        return view;
    }
    private void GetData() {
        ngheSiAdapter = new NgheSiAdapter(getActivity(), mangnghesi);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewNgheSi.setLayoutManager(linearLayoutManager);
        recyclerViewNgheSi.setAdapter(ngheSiAdapter);
        mangnghesi.add(new NgheSiModel("NS01","Anh Tú","https://ss-images.saostar.vn/pc/1641298632909/saostar-yb00y3ss0mdrhtol.jpg"));
        mangnghesi.add(new NgheSiModel("NS02","Trung Quân Idol","https://icdn.dantri.com.vn/thumb_w/770/2022/05/03/ca-si-trung-quan-1651574722985.png"));
        mangnghesi.add(new NgheSiModel("NS03","HIền Hồ","https://thanhnien.mediacdn.vn/zoom/686_429/Uploaded/thanhchau/2022_04_19/ca-si-hien-ho-su-nghiep-thang-hoa-nhung-khong-biet-giu-550.jpeg"));

        ngheSiAdapter.notifyDataSetChanged();
    }
}
