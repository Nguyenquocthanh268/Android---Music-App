package com.example.musicapp.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicapp.Activity.DangKyActivity;
import com.example.musicapp.R;
import com.example.musicapp.Activity.MainActivity;

public class Fragment_Profile extends Fragment {
    Button btn;
    TextView acc, pass, name, url;
    MainActivity hm;
    String sql = "";
    private SQLiteDatabase db;
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        db = getActivity().openOrCreateDatabase("NguoiDung.db", MODE_PRIVATE, null);
        AnhXa();
        Init();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DangKyActivity.class);
                startActivity(intent);
                hm.finish();
            }
        });
        return  view;
    }

    private void Init() {
        name = view.findViewById(R.id.tennguoidung);
        hm = (MainActivity) getActivity();
        name.setText(hm.getName());
    }

    private void AnhXa() {
        btn = view.findViewById(R.id.btndangxuat);
        name = view.findViewById(R.id.tennguoidung);
    }

}
