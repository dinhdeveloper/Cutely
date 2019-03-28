package com.dinh.cutely.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoaiSanPham {
    @SerializedName("maLSP")
    @Expose
    private Integer maLSP;
    @SerializedName("tenLSP")
    @Expose
    private String tenLSP;
    @SerializedName("hinhSLP")
    @Expose
    private String hinhSLP;
    @SerializedName("SanPhams")
    @Expose
    private List<SanPham> sanPhams = null;

    public Integer getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(Integer maLSP) {
        this.maLSP = maLSP;
    }

    public String getTenLSP() {
        return tenLSP;
    }

    public void setTenLSP(String tenLSP) {
        this.tenLSP = tenLSP;
    }

    public String getHinhSLP() {
        return hinhSLP;
    }

    public void setHinhSLP(String hinhSLP) {
        this.hinhSLP = hinhSLP;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }

}
