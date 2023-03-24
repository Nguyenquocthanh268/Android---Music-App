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

import com.example.musicapp.Adapter.PlaylistAdapter;
import com.example.musicapp.Model.PlaylistModel;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment{
    de.hdodenhof.circleimageview.CircleImageView imguser;
    PlaylistAdapter playlistAdapter;
    RecyclerView recyclerViewplaylist;
    TextView tenPlaylist;
    View view;
    ArrayList<PlaylistModel> mangplaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);

        recyclerViewplaylist = view.findViewById(R.id.recyclerviewplaylist);
        tenPlaylist = view.findViewById(R.id.txtplaylist);
        imguser = view.findViewById(R.id.imageviewuser);
        mangplaylist = new ArrayList<PlaylistModel>();
        Picasso.get().load("https://seeded-session-images.scdn.co/v1/img/artist/2xvW7dgL1640K8exTcRMS4/en").into(imguser);
        GetData();

        return view;
    }
    private void GetData() {
        playlistAdapter = new PlaylistAdapter(getActivity(), mangplaylist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewplaylist.setLayoutManager(linearLayoutManager);
        recyclerViewplaylist.setAdapter(playlistAdapter);
        mangplaylist.add(new PlaylistModel("ID04","Nhạc Hoa Thịnh Hành",
                "https://photo-resize-zmp3.zmdcdn.me/w240_r1x1_jpeg/cover/1/9/f/7/19f7601ee9899da6f07de343c4165e73.jpg"));
        mangplaylist.add(new PlaylistModel("ID02","Best of Chillies",
                "https://i.scdn.co/image/ab67706c0000da84063dac83fe6ab9f4e676c089"));
        mangplaylist.add(new PlaylistModel("ID03","Tour Trên những đám mây",
                "https://i.scdn.co/image/ab67706c0000da84063dac83fe6ab9f4e676c089"));
        playlistAdapter.notifyDataSetChanged();
    }
}
