package com.example.musicapp.Service_API;



import com.example.musicapp.Model.BaiHatModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {




    @FormUrlEncoded
    @POST("timkiembaihat.php")
    Call<List<BaiHatModel>> GetTimKiembaihat(@Field("tukhoa") String tukhoa);



}
