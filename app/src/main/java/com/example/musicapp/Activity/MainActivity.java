package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.musicapp.Adapter.MainViewPagerAdapter;
import com.example.musicapp.Fragment.Fragment_Profile;
import com.example.musicapp.Fragment.Fragment_Search;
import com.example.musicapp.Fragment.Fragment_Thu_Vien;
import com.example.musicapp.Fragment.Fragment_TrangChu;
import com.example.musicapp.Fragment.LoadingDialog;
import com.example.musicapp.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    TabLayout tabLayout;
    ViewPager viewPager;
    private String taikhoan, matkhau, name, email, url;
    private long backPressTime;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = openOrCreateDatabase("NguoiDung.db", MODE_PRIVATE, null);
        getData();

        final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);

        anhxa();
        loadingDialog.StartLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
            }
        }, 7500);
        init();
        overridePendingTransition(R.anim.anim_intent_in_home, R.anim.anim_intent_out);
    }

    @Override
    public void onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()){
            mToast.cancel();
//            Intent intent = new Intent(getApplicationContext(), KhoiDongActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.putExtra("EXIT", true);
//            startActivity(intent);
            finish();
            System.exit(0);
        }else {
//            mToast = Toast.makeText(HomeActivity.this, "Ấn lần nữa để thoát", Toast.LENGTH_SHORT);
            mToast.show();
        }
        backPressTime = System.currentTimeMillis();
    }
    private void init()
    {
        MainViewPagerAdapter mainViewPagerAdapter= new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Search(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Thu_Vien(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Profile(),"");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconthuvien);
        tabLayout.getTabAt(3).setIcon(R.drawable.iconlogo);
    }
    public void anhxa()
    {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
    public void getData() {
        String sql = "SELECT * FROM tbNguoiDung";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToLast();
        if (!cursor.isAfterLast()){
            taikhoan = cursor.getString(1);
            matkhau = cursor.getString(2);
            name = cursor.getString(3);
            email = cursor.getString(4);
            url = cursor.getString(5);
        }
    }

    public String getName() {
        return name;
    }
}

