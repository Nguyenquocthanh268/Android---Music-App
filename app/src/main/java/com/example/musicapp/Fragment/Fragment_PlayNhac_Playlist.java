package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.PlayNhacActivity;
import com.example.musicapp.Adapter.PlaynhacAdapter;
import com.example.musicapp.Model.BaiHatModel;
import com.example.musicapp.Model.BaiHatThuVienPlayListModel;
import com.example.musicapp.Model.BaiHatYeuThichModel;
import com.example.musicapp.R;

import java.util.ArrayList;

public class Fragment_PlayNhac_Playlist extends Fragment {
    View view;
    RecyclerView recyclerViewplaynhac;
    PlaynhacAdapter playnhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playnhac_playlist,container,false);
        recyclerViewplaynhac = view.findViewById(R.id.recyclerviewPlaybaihat);
        if(PlayNhacActivity.mangbaihat.size() > 0 ){
            playnhacAdapter = new PlaynhacAdapter(getActivity(), PlayNhacActivity.mangbaihat);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerViewplaynhac.setLayoutManager(linearLayoutManager);
            recyclerViewplaynhac.setAdapter(playnhacAdapter);
        }
        if(PlayNhacActivity.mangbaihatyeuthich.size() > 0 ){
            ArrayList<BaiHatModel> mangbaihat = new ArrayList<>();
            for (BaiHatYeuThichModel i: PlayNhacActivity.mangbaihatyeuthich) {
                BaiHatModel a = new BaiHatModel(i.getIdBaiHat(), i.getTenBaiHat(), i.getHinhBaiHat(), i.getTenCaSi(), i.getLinkBaiHat());
                mangbaihat.add(a);
            }
            playnhacAdapter = new PlaynhacAdapter(getActivity(), mangbaihat);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerViewplaynhac.setLayoutManager(linearLayoutManager);
            recyclerViewplaynhac.setAdapter(playnhacAdapter);
        }
        if(PlayNhacActivity.mangbaihetthuvienplaylist.size() > 0 ){
            ArrayList<BaiHatModel> mangbaihat = new ArrayList<>();
            for (BaiHatThuVienPlayListModel i: PlayNhacActivity.mangbaihetthuvienplaylist) {
                BaiHatModel a = new BaiHatModel(i.getIdBaiHat(), i.getTenBaiHat(), i.getHinhBaiHat(), i.getTenCaSi(), i.getLinkBaiHat());
                mangbaihat.add(a);
            }
            playnhacAdapter = new PlaynhacAdapter(getActivity(), mangbaihat);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerViewplaynhac.setLayoutManager(linearLayoutManager);
            recyclerViewplaynhac.setAdapter(playnhacAdapter);
        }

        return view;
    }
}
