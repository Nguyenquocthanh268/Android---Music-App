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
import com.example.musicapp.Service_API.APIService;
import com.example.musicapp.Service_API.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;

public class Fragment_Playlist extends Fragment{
//    de.hdodenhof.circleimageview.CircleImageView imguser;
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
//        imguser = view.findViewById(R.id.imageviewuser);
        mangplaylist = new ArrayList<PlaylistModel>();
//        Picasso.get().load("https://seeded-session-images.scdn.co/v1/img/artist/2xvW7dgL1640K8exTcRMS4/en").into(imguser);
        GetData();

        return view;
    }
    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<PlaylistModel>> callback = dataservice.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<PlaylistModel>>() {
            @Override
            public void onResponse(Call<List<PlaylistModel>> call, Response<List<PlaylistModel>> response) {
                ArrayList<PlaylistModel> mangplaylist = (ArrayList<PlaylistModel>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(), mangplaylist);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewplaylist.setLayoutManager(linearLayoutManager);
                recyclerViewplaylist.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<PlaylistModel>> call, Throwable t) {

            }
        });
    }
}
