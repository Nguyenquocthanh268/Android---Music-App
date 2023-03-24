package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ThinhHanhModel implements Serializable {
    @SerializedName("IdThinhHanh")
    @Expose
    private String idThinhHanh;
    @SerializedName("TenThinhHanh")
    @Expose
    private String tenThinhHanh;
    @SerializedName("HinhThinhHanh")
    @Expose
    private String hinhThinhHanh;

    public ThinhHanhModel(String idThinhHanh, String tenThinhHanh, String hinhThinhHanh) {
        this.idThinhHanh = idThinhHanh;
        this.tenThinhHanh = tenThinhHanh;
        this.hinhThinhHanh = hinhThinhHanh;
    }

    public String getIdThinhHanh() {
        return idThinhHanh;
    }

    public void setIdThinhHanh(String idThinhHanh) {
        this.idThinhHanh = idThinhHanh;
    }

    public String getTenThinhHanh() {
        return tenThinhHanh;
    }

    public void setTenThinhHanh(String tenThinhHanh) {
        this.tenThinhHanh = tenThinhHanh;
    }

    public String getHinhThinhHanh() {
        return hinhThinhHanh;
    }

    public void setHinhThinhHanh(String hinhThinhHanh) {
        this.hinhThinhHanh = hinhThinhHanh;
    }
}
