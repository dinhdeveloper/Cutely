package com.dinh.cutely.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dinh.cutely.R;
import com.dinh.cutely.model.SanPham;

public class ShowImgActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);

        FloatingActionButton fab1 = findViewById(R.id.set);

        img = findViewById(R.id.showImg);
        Intent intent = getIntent();
        SanPham sanPham = (SanPham) intent.getSerializableExtra("sp");
        Glide.with(ShowImgActivity.this).load(sanPham.getHinhSP()).into(img);

    }
}
