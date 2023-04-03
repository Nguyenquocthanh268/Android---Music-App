package com.example.musicapp.Service_API;

import com.example.musicapp.Model.BaiHatModel;
//import com.example.musicapp.Model.BaiHatThuVienPlayListModel;
//import com.example.musicapp.Model.BaiHatYeuThichModel;
import com.example.musicapp.Model.BangXepHangModel;
import com.example.musicapp.Model.ChuDeModel;
import com.example.musicapp.Model.NgheSiModel;
//import com.example.musicapp.Model.NguoiDungModel;
//import com.example.musicapp.Model.PhanHoiDangKyModel;
import com.example.musicapp.Model.PhoBienModel;
import com.example.musicapp.Model.PlaylistModel;
//import com.example.musicapp.Model.ResponseModel;
import com.example.musicapp.Model.ThinhHanhModel;
import com.example.musicapp.Model.ThuVienPlayListModel;

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



    @GET("playlistforcurrentday.php")
    Call<List<PlaylistModel>> GetPlaylistCurrentDay();

    @GET("nghesiforcurrentday.php")
    Call<List<NgheSiModel>> GetNgheSiCurrent();

    @GET("thinhhanhcurrent.php")
    Call<List<ThinhHanhModel>> GetThinhHanhCurrent();

    @GET("phobiencurrent.php")
    Call<List<PhoBienModel>> GetPhoBienCurrent();

    @GET("chudecurrent.php")
    Call<List<ChuDeModel>> GetChuDeCurrent();

    @GET("bangxephangcurrent.php")
    Call<List<BangXepHangModel>> GetBangXepHangCurrent();

    @FormUrlEncoded
    @POST("thuvienplaylist.php")
    Call<List<ThuVienPlayListModel>> GetBangThuVienPlayList(@Field("username") String username);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatplaylist(@Field("idplaylist") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatnghesi(@Field("idnghesi") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatthinhhanh(@Field("idthinhhanh") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatphobien(@Field("idphobien") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatchude(@Field("idchude") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatbangxephang(@Field("idbangxephang") String id);



}
