package com.dinh.cutely.API;

import com.dinh.cutely.model.LoaiSanPham;
import com.dinh.cutely.model.SanPham;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("loaisanpham/getAllLSP")
    Call<List<LoaiSanPham>> APIServiceLSP();

    @GET("sanpham/getAllSP")
    Call<List<SanPham>> APIServiceSP();

    @GET("sanpham/getDanhMucSP")
    Call<List<SanPham>> APIGetDanhMuc(@Query("id") int id);

    @GET("sanpham/SanPhamTangDan")
    Call<List<SanPham>> APISPTangDan();
}
