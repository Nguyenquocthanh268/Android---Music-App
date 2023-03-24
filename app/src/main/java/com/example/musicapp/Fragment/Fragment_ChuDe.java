package com.example.musicapp.Fragment;

import android.content.Context;
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

import com.example.musicapp.Adapter.ChuDeAdapter;
import com.example.musicapp.Adapter.PhoBienAdapter;
import com.example.musicapp.Model.ChuDeModel;
import com.example.musicapp.Model.PhoBienModel;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe extends Fragment {

    View view;
    ChuDeAdapter chuDeAdapter;
    RecyclerView recyclerViewChuDe;
    TextView tenChuDe;
    ArrayList<ChuDeModel> mangchude;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude, container, false);
        recyclerViewChuDe = view.findViewById(R.id.recyclerviewchude);
        tenChuDe = view.findViewById(R.id.txtchude);
        mangchude = new ArrayList<ChuDeModel>();
        GetData();
        return view;
    }

    private void GetData() {
        chuDeAdapter = new ChuDeAdapter(getActivity(), mangchude);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewChuDe.setLayoutManager(linearLayoutManager);
        recyclerViewChuDe.setAdapter(chuDeAdapter);
        mangchude.add(new ChuDeModel("PB01","Best of Chillies","https://i.scdn.co/image/ab67706c0000da84063dac83fe6ab9f4e676c089"));
        mangchude.add(new ChuDeModel("PB02","Nhac Hoa","https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/cover/1/9/f/7/19f7601ee9899da6f07de343c4165e73.jpg"));
        mangchude.add(new ChuDeModel("PB03","Trung Quan Idol","https://icdn.dantri.com.vn/thumb_w/770/2022/05/03/ca-si-trung-quan-1651574722985.png"));
        chuDeAdapter.notifyDataSetChanged();

    }
}
