package com.dinh.cutely.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dinh.cutely.API.APIService;
import com.dinh.cutely.API.APIUntil;
import com.dinh.cutely.R;
import com.dinh.cutely.adapter.SanPhamAdapter;
import com.dinh.cutely.model.SanPham;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WallFragment extends Fragment {

    GridLayoutManager gridLayoutManager;
    public RecyclerView recyclerView;
    SanPhamAdapter sanPhamAdapter;
    List<SanPham> sanPhams;
    APIService apiServiceSP;

    public WallFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wall, container, false);

        apiServiceSP = APIUntil.getServer();
        recyclerView = view.findViewById(R.id.rcWall);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        //kéo lên đầu trang
        FloatingActionButton moveTop = view.findViewById(R.id.fab);
        moveTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                recyclerView.smoothScrollToPosition(0);
            }
        });
        apiServiceSP.APIServiceSP().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhams =  response.body();
                sanPhamAdapter = new SanPhamAdapter(getContext(), sanPhams);
                recyclerView.setAdapter(sanPhamAdapter);
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });

        return view;
    }
    
}
