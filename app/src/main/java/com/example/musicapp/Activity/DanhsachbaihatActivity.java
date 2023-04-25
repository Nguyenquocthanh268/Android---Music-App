package com.example.musicapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.musicapp.Adapter.DanhsachbaihatAdapter;
import com.example.musicapp.Model.BaiHatModel;
import com.example.musicapp.Model.BaiHatYeuThichModel;
import com.example.musicapp.Model.BangXepHangModel;
import com.example.musicapp.Model.ChuDeModel;
import com.example.musicapp.Model.NgheSiModel;
import com.example.musicapp.Model.PhoBienModel;
import com.example.musicapp.Model.PlaylistModel;
import com.example.musicapp.Model.ThinhHanhModel;
import com.example.musicapp.R;
import com.example.musicapp.Service_API.APIService;
import com.example.musicapp.Service_API.Dataservice;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    TextView txtcollapsing;
    PlaylistModel playlist = null;
    NgheSiModel ngheSi = null;
    PhoBienModel phoBien = null;
    ThinhHanhModel thinhHanh = null;
    ChuDeModel chuDe = null;
    BangXepHangModel bangXepHang = null;

    BaiHatYeuThichModel baiHatYeuThichModel = null;

    ImageView imgdanhsachcakhuc;
    ArrayList<BaiHatModel> mangbaihat;

    DanhsachbaihatAdapter danhsachbaihatAdapter;

    ImageView btnThemnhac;
    SwipeRefreshLayout swipeRefreshLayout;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        AnhXa();
        floatingActionButton.setEnabled(false);
        DataIntent();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        if (ngheSi != null && !ngheSi.equals("")){
            setValueInView(ngheSi.getHinhNgheSi());
            GetDataNgheSi(ngheSi.getIdNgheSi());
            txtcollapsing.setText(ngheSi.getTenNgheSi());
            getSupportActionBar().setTitle(ngheSi.getTenNgheSi());
        }
        if (playlist != null && !playlist.equals("")){
            setValueInView(playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
            txtcollapsing.setText(playlist.getTen());
            getSupportActionBar().setTitle(playlist.getTen());
        }

        if (thinhHanh != null && !thinhHanh.equals("")){
            setValueInView(thinhHanh.getHinhThinhHanh());
            GetDataThinhHanh(thinhHanh.getIdThinhHanh());
            txtcollapsing.setText(thinhHanh.getTenThinhHanh());
            getSupportActionBar().setTitle(thinhHanh.getTenThinhHanh());
        }
        if (phoBien != null && !phoBien.equals("")){
            setValueInView(phoBien.getHinhPhoBien());
            GetDataPhoBien(phoBien.getIdPhoBien());
            txtcollapsing.setText(phoBien.getTenPhoBien());
            getSupportActionBar().setTitle(phoBien.getTenPhoBien());
        }
        if (chuDe != null && !chuDe.equals("")){
            setValueInView(chuDe.getHinhChuDe());
            GetDataChuDe(chuDe.getIdChuDe());
            txtcollapsing.setText(chuDe.getTenChuDe());
            getSupportActionBar().setTitle(chuDe.getTenChuDe());
        }
        if (bangXepHang != null && !bangXepHang.equals("")){
            setValueInView(bangXepHang.getHinhBangXepHang());
            GetDataBangXepHang(bangXepHang.getIdBangXepHang());
            txtcollapsing.setText(bangXepHang.getTenBangXepHang());
            getSupportActionBar().setTitle(bangXepHang.getTenBangXepHang());
        }


        floatActionButtonClick();




    }

    private void setValueInView(String hinh) {
        Picasso.get().load(hinh).into(imgdanhsachcakhuc);
    }
    private void GetDataPlaylist(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHatModel>> callback = dataservice.GetDanhsachbaihatplaylist(id);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangbaihat = (ArrayList<BaiHatModel>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }
    private void GetDataBangXepHang(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHatModel>> callback = dataservice.GetDanhsachbaihatbangxephang(id);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangbaihat = (ArrayList<BaiHatModel>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }
    private void GetDataChuDe(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHatModel>> callback = dataservice.GetDanhsachbaihatchude(id);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangbaihat = (ArrayList<BaiHatModel>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }
    private void GetDataNgheSi(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHatModel>> callback = dataservice.GetDanhsachbaihatnghesi(id);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangbaihat = (ArrayList<BaiHatModel>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }

    private void GetDataPhoBien(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHatModel>> callback = dataservice.GetDanhsachbaihatphobien(id);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangbaihat = (ArrayList<BaiHatModel>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }
    private void GetDataThinhHanh(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHatModel>> callback = dataservice.GetDanhsachbaihatthinhhanh(id);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangbaihat = (ArrayList<BaiHatModel>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }


    private void AnhXa() {
        toolbar = findViewById(R.id.toolbardanhsachbaihat);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        txtcollapsing = findViewById(R.id.textViewcollapsing);
        btnThemnhac = findViewById(R.id.btnthemnhacthuvien);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void DataIntent() {
        Intent intent = getIntent();
        btnThemnhac.setVisibility(View.GONE);
        if (intent != null){
            if (intent.hasExtra("intentplaylist")){
                playlist = (PlaylistModel) intent.getSerializableExtra("intentplaylist");
            }else
            if (intent.hasExtra("intentnghesi")){
                ngheSi = (NgheSiModel) intent.getSerializableExtra("intentnghesi");
            }else
            if (intent.hasExtra("intentthinhhanh")){
                thinhHanh = (ThinhHanhModel) intent.getSerializableExtra("intentthinhhanh");
            }else
            if (intent.hasExtra("intentphobien")){
                phoBien = (PhoBienModel) intent.getSerializableExtra("intentphobien");
            }else
            if (intent.hasExtra("intentchude")){
                chuDe = (ChuDeModel) intent.getSerializableExtra("intentchude");
            }else
            if (intent.hasExtra("intentbangxephang")){
                bangXepHang = (BangXepHangModel) intent.getSerializableExtra("intentbangxephang");
            }
            else
            if (intent.hasExtra("cakhucyeuthich")){
                bangXepHang = (BangXepHangModel) intent.getSerializableExtra("intentbangxephang");
            }


//            else
//            if(intent.hasExtra("idthuvienplaylist")){
//                thuVienPlayList = (ThuVienPlayListModel) intent.getSerializableExtra("idthuvienplaylist");
//                id = thuVienPlayList.getIDThuVienPlayList();
//                btnThemnhac.setVisibility(View.VISIBLE);
//            }
        }
    }
    private void floatActionButtonClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
                if (mangbaihat!=null){
                    if (mangbaihat.size() > 0){
                        intent.putExtra("cacbaihat", mangbaihat);
                        startActivity(intent);
                    }else {
                        Toast.makeText(DanhsachbaihatActivity.this, "Danh sách không có bài hát nào cả :(", Toast.LENGTH_SHORT).show();
                    }
                }
//                else{
//                    if (mangbaihatthuvienplaylist != null){
//                        if (mangbaihatthuvienplaylist.size() > 0){
//                            intent.putExtra("cacbaihatthuvien", mangbaihatthuvienplaylist);
//                            startActivity(intent);
//                        }else {
//                            Toast.makeText(DanhsachbaihatActivity.this, "Danh sách không có bài hát nào cả :(", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
            }
        });
    }
}