package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import com.example.musicapp.Adapter.MainViewPagerAdapter;
import com.example.musicapp.Fragment.Fragment_Search;
import com.example.musicapp.Fragment.Fragment_TrangChu;
import com.example.musicapp.Fragment.LoadingDialog;
import com.example.musicapp.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    private void init()
    {
        MainViewPagerAdapter mainViewPagerAdapter= new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(),"");
        mainViewPagerAdapter.addFragment(new Fragment_Search(),"");
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(),"");
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(),"");
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
//        Cursor cursor = db.rawQuery(sql, null);
//        cursor.moveToLast();
//        if (!cursor.isAfterLast()){
//            taikhoan = cursor.getString(1);
//            matkhau = cursor.getString(2);
//            name = cursor.getString(3);
//            email = cursor.getString(4);
//            url = cursor.getString(5);
//        }
    }
}

