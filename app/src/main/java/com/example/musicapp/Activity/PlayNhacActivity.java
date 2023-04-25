package com.example.musicapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.Adapter.ViewPagerDiaNhac;
import com.example.musicapp.Fragment.Fragment_PlayNhac_Playlist;
import com.example.musicapp.Fragment.Fragment_dia_nhac;
import com.example.musicapp.Model.BaiHatModel;
import com.example.musicapp.Model.BaiHatYeuThichModel;
import com.example.musicapp.Model.ResponseModel;
import com.example.musicapp.R;
import com.example.musicapp.Service_API.APIService;
import com.example.musicapp.Service_API.Dataservice;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayNhacActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbarplaynhac;
    private SeekBar seekBarnhac;
    private SQLiteDatabase db;
    private String taikhoan;
    private ImageView imageViewtim;
    private TextView textViewtennhac, textViewcasi, textViewrunrime, textViewtatoltime;
    private ImageButton imageButtontronnhac, imageButtonpreviewnhac, imageButtonplaypausenhac, imageButtonnexnhac,
            imageButtonlapnhac;
    private int dem = 0, position = 0, duration = 0, timeValue = 0, durationToService = 0;
    private boolean repeat = false, checkrandom = false, isplaying,next = false;
    public static ArrayList<BaiHatModel> mangbaihat = new ArrayList<>();
    public static ArrayList<BaiHatYeuThichModel> mangbaihatyeuthich = new ArrayList<>();

    private Fragment_dia_nhac fragment_dia_nhac;
    ViewPager viewPagerplaynhac;
    private Fragment_PlayNhac_Playlist fragment_playNhac_playlist;
    public static ViewPagerDiaNhac adapternhac;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
//        AnhXa();
//        GetDataFromIntent();

        db = openOrCreateDatabase("NguoiDung.db", MODE_PRIVATE, null);
//        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
//                new IntentFilter("send_data_to_activity"));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        getDataSQLite();
        AnhXa();

        enventClick();
//        setViewStart();
//        StartService();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
//        mangbaihetthuvienplaylist.clear();
        mangbaihatyeuthich.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                BaiHatModel baiHat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baiHat);
            } else if (intent.hasExtra("cacbaihat")) {
                mangbaihat = intent.getParcelableArrayListExtra("cacbaihat");
            } else if (intent.hasExtra("cakhucthuvien")) {
//                BaiHatThuVienPlayListModel baiHatThuVienPlayList = intent.getParcelableExtra("cakhucthuvien");
//                mangbaihetthuvienplaylist.add(baiHatThuVienPlayList);
            } else if (intent.hasExtra("cacbaihatthuvien")) {
//                mangbaihetthuvienplaylist = intent.getParcelableArrayListExtra("cacbaihatthuvien");
            } else if (intent.hasExtra(("cakhucyeuthich"))) {
                BaiHatYeuThichModel baiHatYeuThichModel = intent.getParcelableExtra("cakhucyeuthich");
                mangbaihatyeuthich.add(baiHatYeuThichModel);
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
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.BLACK);

        adapternhac = new ViewPagerDiaNhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_dia_nhac);
        adapternhac.AddFragment(fragment_playNhac_playlist);
        viewPagerplaynhac.setAdapter(adapternhac);
//        fragment_dia_nhac = (Fragment_dia_nhac) adapternhac.getItem(1);
        if(mangbaihat.size() > 0 ){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            textViewcasi.setText(mangbaihat.get(0).getTenCaSi());
            textViewtennhac.setText(mangbaihat.get(0).getTenBaiHat());
            new playMP3().execute(mangbaihat.get(0).getLinkBaiHat());
            imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
            checkYeuThich(taikhoan,mangbaihat.get(0).getIdBaiHat());
            setGradient(mangbaihat.get(0).getHinhBaiHat());
        }
        if(mangbaihatyeuthich.size() > 0){
            getSupportActionBar().setTitle(mangbaihatyeuthich.get(0).getTenBaiHat());
            textViewcasi.setText(mangbaihatyeuthich.get(0).getTenCaSi());
            textViewtennhac.setText(mangbaihatyeuthich.get(0).getTenBaiHat());
            new playMP3().execute(mangbaihatyeuthich.get(0).getLinkBaiHat());
            imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
            checkYeuThich(taikhoan,mangbaihatyeuthich.get(0).getIdBaiHat());
            setGradient(mangbaihatyeuthich.get(0).getHinhBaiHat());
        }

//       fragment_playNhac_playlist = (Fragment_PlayNhac_Playlist) adapternhac.getItem(position);

    }
    private void enventClick(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1)!=null){
                    if(mangbaihat.size()>0 ){
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }
                    if(mangbaihatyeuthich.size()>0 ){
                        fragment_dia_nhac.PlayNhac(mangbaihatyeuthich.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }
                    else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imageViewtim.setOnClickListener(view -> {
            if (dem == 0){
                Animation animation = AnimationUtils.loadAnimation(PlayNhacActivity.this, R.anim.anim_timclick);
                imageViewtim.setImageResource(R.drawable.iconloved);
                view.startAnimation(animation);
                if (mangbaihat.size() > 0){
                    insertYeuThich(taikhoan, mangbaihat.get(position).getIdBaiHat());
                }
                dem++;
            }else {
                imageViewtim.setImageResource(R.drawable.iconlove);
                if (mangbaihat.size() > 0){
                    deleteYeuThich(taikhoan, mangbaihat.get(position).getIdBaiHat());
                }

                dem--;
            }
        });
        imageButtonplaypausenhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);

                }
                else{
                    mediaPlayer.start();
                    imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                }
            }
        });
        imageButtonlapnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat == false){
                    if ( checkrandom == true){
                        checkrandom = false;
                        imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                        imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
                    }
                    imageButtonlapnhac.setImageResource(R.drawable.iconsyned);
                    repeat= true;
                }
                else {
                    imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imageButtontronnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkrandom == false){
                    if ( repeat == true){
                        repeat = false;

                        imageButtontronnhac.setImageResource(R.drawable.iconshuffled);
                        imageButtonlapnhac.setImageResource(R.drawable.iconrepeat);
                    }
                    imageButtontronnhac.setImageResource(R.drawable.iconshuffled);
                    checkrandom= true;
                }
                else {
                    imageButtontronnhac.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        seekBarnhac.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                        mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imageButtonnexnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mangbaihat.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if( position < (mangbaihat.size())){
                        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                        position++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position -=1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index ;
                        }
                        if (position > (mangbaihat.size()-1)){
                            position =0 ;
                        }
                        new playMP3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        textViewcasi.setText(mangbaihat.get(position).getTenCaSi());
                        textViewtennhac.setText(mangbaihat.get(position).getTenBaiHat());
                        checkYeuThich(taikhoan,mangbaihat.get(position).getIdBaiHat());
                        setGradient(mangbaihat.get(position).getHinhBaiHat());
//                        UpdateTime();
                    }
                }
                if ( mangbaihatyeuthich.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if( position < (mangbaihatyeuthich.size())){
                        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                        position++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihatyeuthich.size();
                            }
                            position -=1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihatyeuthich.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index ;
                        }
                        if (position > (mangbaihatyeuthich.size()-1)){
                            position =0 ;
                        }
                        new playMP3().execute(mangbaihatyeuthich.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(mangbaihatyeuthich.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihatyeuthich.get(position).getTenBaiHat());
                        textViewcasi.setText(mangbaihatyeuthich.get(position).getTenCaSi());
                        textViewtennhac.setText(mangbaihatyeuthich.get(position).getTenBaiHat());
                        checkYeuThich(taikhoan,mangbaihatyeuthich.get(position).getIdBaiHat());
                        setGradient(mangbaihatyeuthich.get(position).getHinhBaiHat());
//                        UpdateTime();
                    }
                }
                imageButtonlapnhac.setClickable(false);
                imageButtonnexnhac.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                         imageButtonlapnhac.setClickable(true);
                         imageButtonnexnhac.setClickable(true);
                    }
                },5000);

            }
        });
        imageButtonpreviewnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mangbaihat.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if( position < (mangbaihat.size())){
                        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                        position--;
                        if(position < 0){
                            position = mangbaihat.size()-1;
                        }
                        if(repeat == true){

                            position +=1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index ;
                        }

                        new playMP3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        textViewcasi.setText(mangbaihat.get(position).getTenCaSi());
                        textViewtennhac.setText(mangbaihat.get(position).getTenBaiHat());
                        checkYeuThich(taikhoan,mangbaihat.get(position).getIdBaiHat());
                        setGradient(mangbaihat.get(position).getHinhBaiHat());
//                        UpdateTime();
                    }
                }
                if ( mangbaihatyeuthich.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if( position < (mangbaihatyeuthich.size())){
                        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                        position--;
                        if(position < 0){
                            position = mangbaihatyeuthich.size()-1;
                        }
                        if(repeat == true){

                            position +=1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihatyeuthich.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index ;
                        }

                        new playMP3().execute(mangbaihatyeuthich.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(mangbaihatyeuthich.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihatyeuthich.get(position).getTenBaiHat());
                        textViewcasi.setText(mangbaihatyeuthich.get(position).getTenCaSi());
                        textViewtennhac.setText(mangbaihatyeuthich.get(position).getTenBaiHat());
                        checkYeuThich(taikhoan,mangbaihatyeuthich.get(position).getIdBaiHat());
                        setGradient(mangbaihatyeuthich.get(position).getHinhBaiHat());
//                        UpdateTime();
                    }
                }
                imageButtonlapnhac.setClickable(false);
                imageButtonpreviewnhac.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonlapnhac.setClickable(true);
                        imageButtonpreviewnhac.setClickable(true);
                    }
                },5000);
            }
        });

    }
    private void insertYeuThich(String un, int idbh) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.insertyeuthich(un, idbh);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }
        });

    }
    private void deleteYeuThich(String un, int idbh) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.deleteyeuthich(un, idbh);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }
        });
    }
    private void checkYeuThich(String un, int idbh) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.checkyeuthich(un, idbh);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        dem = 1;
                        imageViewtim.setImageResource(R.drawable.iconloved);
                    } else {
                        dem = 0;
                        imageViewtim.setImageResource(R.drawable.iconlove);
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }
        });
    }


    private void getDataSQLite() {
        String sql = "SELECT * FROM tbNguoiDung";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToLast();
        taikhoan = cursor.getString(1);
    }
    private void setGradient(String urlImage) {
        Picasso.get().load(urlImage)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Palette.from(bitmap).generate(palette -> {
                            assert palette != null;
                            Palette.Swatch swatch = palette.getDominantSwatch();
                            RelativeLayout mContaier = findViewById(R.id.mContainer);
                            mContaier.setBackgroundResource(R.drawable.bgr_playnhac);
                            assert swatch != null;
                            GradientDrawable gradientDrawableBg = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                                    new int[]{swatch.getRgb(), swatch.getRgb()});
                            mContaier.setBackground(gradientDrawableBg);
                        });
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }
                });
    }

class playMP3 extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        return strings[0];
    }

    @Override
    protected void onPostExecute(String baihat) {
        super.onPostExecute(baihat);
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
            mediaPlayer.setDataSource(baihat);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        TimeSong();
        UpdateTime();
    }
    private void TimeSong(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtatoltime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarnhac.setMax(mediaPlayer.getDuration());

    }
    private void UpdateTime(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seekBarnhac.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewrunrime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if( next == true ){
                    if(mangbaihat.size() > 0){
                        if( position < (mangbaihat.size())){
                            imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                            position++;
                            if(repeat == true){
                                if(position == 0){
                                    position = mangbaihat.size();
                                }
                                position -=1;
                            }
                            if (checkrandom == true){
                                Random random = new Random();
                                int index = random.nextInt(mangbaihat.size());
                                if(index == position){
                                    position = index - 1;
                                }
                                position = index ;
                            }
                            if (position > (mangbaihat.size()-1)){
                                position =0 ;
                            }
                            new playMP3().execute(mangbaihat.get(position).getLinkBaiHat());
                            fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                            getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                            textViewcasi.setText(mangbaihat.get(position).getTenCaSi());
                            textViewtennhac.setText(mangbaihat.get(position).getTenBaiHat());
                            checkYeuThich(taikhoan,mangbaihat.get(position).getIdBaiHat());
                            setGradient(mangbaihat.get(position).getHinhBaiHat());

                        }
                    }
                    if(mangbaihatyeuthich.size()>0){
                        if( position < (mangbaihatyeuthich.size())){
                            imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                            position++;
                            if(repeat == true){
                                if(position == 0){
                                    position = mangbaihatyeuthich.size();
                                }
                                position -=1;
                            }
                            if (checkrandom == true){
                                Random random = new Random();
                                int index = random.nextInt(mangbaihatyeuthich.size());
                                if(index == position){
                                    position = index - 1;
                                }
                                position = index ;
                            }
                            if (position > (mangbaihatyeuthich.size()-1)){
                                position =0 ;
                            }
                            new playMP3().execute(mangbaihatyeuthich.get(position).getLinkBaiHat());
                            fragment_dia_nhac.PlayNhac(mangbaihatyeuthich.get(position).getHinhBaiHat());
                            getSupportActionBar().setTitle(mangbaihatyeuthich.get(position).getTenBaiHat());
                            textViewcasi.setText(mangbaihatyeuthich.get(position).getTenCaSi());
                            textViewtennhac.setText(mangbaihatyeuthich.get(position).getTenBaiHat());
                            checkYeuThich(taikhoan,mangbaihatyeuthich.get(position).getIdBaiHat());
                            setGradient(mangbaihatyeuthich.get(position).getHinhBaiHat());

                        }
                    }


                imageButtonlapnhac.setClickable(false);
                imageButtonnexnhac.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonlapnhac.setClickable(true);
                        imageButtonnexnhac.setClickable(true);
                    }
                },5000);
                next = false;
                handler1.removeCallbacks(this);
                }

                else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
}