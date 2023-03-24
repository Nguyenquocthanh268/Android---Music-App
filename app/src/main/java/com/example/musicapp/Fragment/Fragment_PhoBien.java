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

import com.example.musicapp.Adapter.PhoBienAdapter;
import com.example.musicapp.Adapter.PlaylistAdapter;
import com.example.musicapp.Model.PhoBienModel;
import com.example.musicapp.Model.PlaylistModel;
import com.example.musicapp.R;

import java.util.ArrayList;

public class Fragment_PhoBien extends Fragment {

    PhoBienAdapter phoBienAdapter;
    RecyclerView recyclerViewphobien;
    TextView tenPhoBien;
    View view;
    ArrayList<PhoBienModel> mangphobien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phobien, container, false);
        recyclerViewphobien = view.findViewById(R.id.recyclerviewphobien);
        tenPhoBien = view.findViewById(R.id.txtphobien);
        mangphobien = new ArrayList<PhoBienModel>();
        GetData();
        return view;
    }

    private void GetData() {
        phoBienAdapter = new PhoBienAdapter(getActivity(), mangphobien);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewphobien.setLayoutManager(linearLayoutManager);
        recyclerViewphobien.setAdapter(phoBienAdapter);
        mangphobien.add(new PhoBienModel("PB01","Best of Chillies","https://i.scdn.co/image/ab67706c0000da84063dac83fe6ab9f4e676c089"));
        mangphobien.add(new PhoBienModel("PB02","Nhac Hoa","https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/cover/1/9/f/7/19f7601ee9899da6f07de343c4165e73.jpg"));
        mangphobien.add(new PhoBienModel("PB03","Trung Quan Idol","https://icdn.dantri.com.vn/thumb_w/770/2022/05/03/ca-si-trung-quan-1651574722985.png"));
        phoBienAdapter.notifyDataSetChanged();
    }
}
