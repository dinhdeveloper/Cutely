package com.dinh.cutely.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dinh.cutely.R;
import com.dinh.cutely.model.SanPham;
import java.io.IOException;

public class ShowImgActivity extends AppCompatActivity {
    ImageView img;
    FloatingActionButton fabOption, favorites, fab_set, fab_save, fab_share, un_option;


    boolean  doubleClick = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        final Intent intent = getIntent();
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
                ShareIntent.putExtra(Intent.EXTRA_TEXT, sanPham.getHinhSP());
                startActivity(Intent.createChooser(ShareIntent, "Share Using"));
            }
        });
        //sự kiện set
        fab_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // AlertDialog.Builder builder = new AlertDialog.Builder(ShowImgActivity.this,R.style.AlertDialogCustom);
               AlertDialog.Builder builder = new AlertDialog.Builder(ShowImgActivity.this);
                builder.setTitle("Xác Nhận");
                builder.setMessage("Bạn có muốn cài hình nền không?");
                builder.setCancelable(true);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Glide.with(getApplicationContext()).asBitmap()
                                .load(sanPham.getHinhSP())
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        // img.setImageBitmap(resource);
                                        try {
                                            WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());
                                            manager.setBitmap(resource);
                                            Toast.makeText(ShowImgActivity.this, "Cài đặt thành công", Toast.LENGTH_SHORT).show();

                                        } catch (IOException e) {
                                            Toast.makeText(ShowImgActivity.this, "Cài đặt không thành công", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    }
                                });

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                final AlertDialog alertDialog = builder.create();
                alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onShow(DialogInterface arg0) {
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#ecf0f1"));
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ecf0f1"));
                    }
                });
                alertDialog.show();
                //set màu cho chữ xác nhận
                int textViewId = alertDialog.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
                TextView tv = (TextView) alertDialog.findViewById(textViewId);
                tv.setTextColor(Color.parseColor("#ecf0f1"));
            }
        });
        //set img

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


