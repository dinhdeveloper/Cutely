package com.dinh.cutely.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dinh.cutely.R;
import com.dinh.cutely.model.SanPham;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;

public class ShowImgActivity extends AppCompatActivity {

    Bitmap bitmap;
    private static final int WRITE_EXTERNAL = 1;
    ImageView img;
    FloatingActionButton fabOption, favorites, fab_set, fab_save, fab_share, un_option;

    int Requescode = 656;

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
    }

    private void addEvents() {
        // truyền ảnh và lấy ảnh
        Intent intent = getIntent();
        final SanPham sanPham = (SanPham) intent.getSerializableExtra("sp");
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

//        bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
//            PackageManager.PERMISSION_DENIED){
//                String[] persion = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                requestPermissions(persion,WRITE_EXTERNAL);
//            }else {
//
//            }
//        }else {
//
//        }

        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




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
            }
        });
    }


    private void HienThiIcon() {
        fab_set.show();
        fab_save.show();
        fab_share.show();
        un_option.show();
    }

    private void AnIcon() {
        fab_set.hide();
        fab_save.hide();
        fab_share.hide();
        un_option.hide();
    }

}


