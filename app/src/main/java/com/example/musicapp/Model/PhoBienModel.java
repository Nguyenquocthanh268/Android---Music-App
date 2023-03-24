package com.example.musicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhoBienModel implements Serializable {
    @SerializedName("IdPhoBien")
    @Expose
    private String idPhoBien;
    @SerializedName("TenPhoBien")
    @Expose
    private String tenPhoBien;
    @SerializedName("HinhPhoBien")
    @Expose
    private String hinhPhoBien;

    public PhoBienModel(String idPhoBien, String tenPhoBien, String hinhPhoBien) {
        this.idPhoBien = idPhoBien;
        this.tenPhoBien = tenPhoBien;
        this.hinhPhoBien = hinhPhoBien;
    }

    public String getIdPhoBien() {
        return idPhoBien;
    }

    public void setIdPhoBien(String idPhoBien) {
        this.idPhoBien = idPhoBien;
    }

    public String getTenPhoBien() {
        return tenPhoBien;
    }

    public void setTenPhoBien(String tenPhoBien) {
        this.tenPhoBien = tenPhoBien;
    }

    public String getHinhPhoBien() {
        return hinhPhoBien;
    }

    public void setHinhPhoBien(String hinhPhoBien) {
        this.hinhPhoBien = hinhPhoBien;
    }
}