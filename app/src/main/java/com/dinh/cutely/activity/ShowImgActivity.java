package com.dinh.cutely.activity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dinh.cutely.R;
import com.dinh.cutely.model.SanPham;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

public class ShowImgActivity extends AppCompatActivity{

    ImageView img;
    FloatingActionButton fabOption,favorites,fab_set,fab_save,fab_share,un_option;
//    TextView txtSet,txtSave, txtShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);

        //Ánh xạ
       addConTrol();

        addEvents();


    }


    private void addConTrol() {
        img = findViewById(R.id.showImg);
        fabOption = findViewById(R.id.option);
        favorites = findViewById(R.id.favorites);
        fab_set = findViewById(R.id.fab_set);
        fab_save = findViewById(R.id.fab_save);
        fab_share = findViewById(R.id.fab_share);
        un_option = findViewById(R.id.un_option);

//        txtSet = findViewById(R.id.txtSet);
//        txtSave = findViewById(R.id.txtSave);
//        txtShare = findViewById(R.id.txtShare);
    }

    private void addEvents() {
        // truyền ảnh và lấy ảnh
        Intent intent = getIntent();
        SanPham sanPham = (SanPham) intent.getSerializableExtra("sp");
//        Toast.makeText(this, "UK "+sanPham.getHinhSP(), Toast.LENGTH_SHORT).show();
        Glide.with(ShowImgActivity.this).load(sanPham.getHinhSP()).into(img);


        fabOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HienThiIcon();
            }
        });
        //Ẩn các icon save, share, set
        un_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnIcon();
            }
        });
        //Sự Kiện save
        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowImgActivity.this, "Save", Toast.LENGTH_SHORT).show();
            }
        });
        //Sự kiện share
        fab_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ShareIntent = new Intent(Intent.ACTION_SEND);
                ShareIntent.setType("text/plain");
                ShareIntent.putExtra(Intent.EXTRA_SUBJECT, "the title");
                ShareIntent.putExtra(Intent.EXTRA_TEXT, "my body text");
                startActivity(Intent.createChooser(ShareIntent, "Share Using"));
            }
        });
        //sự kiện set
        fab_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowImgActivity.this, "SetImage", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void HienThiIcon(){
        fab_set.show();
        fab_save.show();
        fab_share.show();
        un_option.show();
//        txtSet.setVisibility(View.VISIBLE);
//        txtShare.setVisibility(View.VISIBLE);
//        txtSave.setVisibility(View.VISIBLE);
    }

    private void AnIcon(){
        fab_set.hide();
        fab_save.hide();
        fab_share.hide();
        un_option.hide();
//        txtSet.setVisibility(View.INVISIBLE);
//        txtShare.setVisibility(View.INVISIBLE);
//        txtSave.setVisibility(View.INVISIBLE);
    }
}
