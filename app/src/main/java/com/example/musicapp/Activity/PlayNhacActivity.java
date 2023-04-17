package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musicapp.Adapter.ViewPagerDiaNhac;
import com.example.musicapp.Fragment.Fragment_PlayNhac_Playlist;
import com.example.musicapp.Fragment.Fragment_dia_nhac;
import com.example.musicapp.Model.BaiHatModel;
import com.example.musicapp.R;

import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbarplaynhac;
    private SeekBar seekBarnhac;
    private ImageView imageViewtim;
    private TextView textViewtennhac, textViewcasi, textViewrunrime, textViewtatoltime;
    private ImageButton imageButtontronnhac, imageButtonpreviewnhac, imageButtonplaypausenhac, imageButtonnexnhac,
            imageButtonlapnhac;
    private int dem = 0, position = 0, duration = 0, timeValue = 0, durationToService = 0;
    private boolean repeat = false, checkrandom = false, isplaying;
    public static ArrayList<BaiHatModel> mangbaihat = new ArrayList<>();

    private Fragment_dia_nhac fragment_dia_nhac;
    ViewPager viewPagerplaynhac;
    private Fragment_PlayNhac_Playlist fragment_playNhac_playlist;
    public static ViewPagerDiaNhac adapternhac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        AnhXa();
        GetDataFromIntent();
    }
    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
//        mangbaihetthuvienplaylist.clear();
//        mangbaihatyeuthich.clear();
        if (intent != null){
            if (intent.hasExtra("cakhuc")){
                BaiHatModel baiHat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baiHat);
            }else if (intent.hasExtra("cacbaihat")){
                mangbaihat = intent.getParcelableArrayListExtra("cacbaihat");
            }else if (intent.hasExtra("cakhucthuvien")){
//                BaiHatThuVienPlayListModel baiHatThuVienPlayList = intent.getParcelableExtra("cakhucthuvien");
//                mangbaihetthuvienplaylist.add(baiHatThuVienPlayList);
            }else if (intent.hasExtra("cacbaihatthuvien")){
//                mangbaihetthuvienplaylist = intent.getParcelableArrayListExtra("cacbaihatthuvien");
            }else if (intent.hasExtra(("cakhucyeuthich"))){
//                BaiHatYeuThichModel baiHatYeuThichModel = intent.getParcelableExtra("cakhucyeuthich");
//                mangbaihatyeuthich.add(baiHatYeuThichModel);
            }
        }
    }
    private void AnhXa() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        seekBarnhac = findViewById(R.id.seekBartime);
        viewPagerplaynhac = findViewById(R.id.viewPagerdianhac);
        imageViewtim = findViewById(R.id.imageViewtimplaynhac);
        imageButtontronnhac = findViewById(R.id.imageButtontron);
        imageButtonpreviewnhac = findViewById(R.id.imageButtonpreview);
        imageButtonplaypausenhac = findViewById(R.id.imageButtonplaypause);
        imageButtonnexnhac = findViewById(R.id.imageButtonnext);
        imageButtonlapnhac = findViewById(R.id.imageButtonlap);
        textViewtatoltime = findViewById(R.id.textViewtimetotal);
        textViewcasi = findViewById(R.id.textViewtencasiplaynhac);
        textViewtennhac = findViewById(R.id.textViewtenbaihatplaynhac);
        textViewrunrime = findViewById(R.id.textViewruntime);
        fragment_dia_nhac = new Fragment_dia_nhac();
        fragment_playNhac_playlist = new Fragment_PlayNhac_Playlist();

        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.BLACK);

        adapternhac = new ViewPagerDiaNhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_dia_nhac);
        adapternhac.AddFragment(fragment_playNhac_playlist);
        viewPagerplaynhac.setAdapter(adapternhac);
//       fragment_dia_nhac = (Fragment_dia_nhac) adapternhac.getItem(position);
//       fragment_playNhac_playlist = (Fragment_PlayNhac_Playlist) adapternhac.getItem(position);

    }
}