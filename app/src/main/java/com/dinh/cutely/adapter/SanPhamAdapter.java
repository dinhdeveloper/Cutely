package com.dinh.cutely.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dinh.cutely.R;
import com.dinh.cutely.activity.ShowImgActivity;
import com.dinh.cutely.model.SanPham;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    Context context;
    List<SanPham> sanPhams;

    public SanPhamAdapter(Context context, List<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SanPham contact = sanPhams.get(i);
        Glide.with(context).load(contact.getHinhSP()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            //cardView = itemView.findViewById(R.id.cvDiamond);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShowImgActivity.class);
                    SanPham sanPham = sanPhams.get(getAdapterPosition());
//                    Toast.makeText(context,"DAY ROI "+ sanPham.getHinhSP(), Toast.LENGTH_SHORT).show();
                    intent.putExtra("sp", sanPham);
                    v.getContext().startActivity(intent);
                    // gửi lệnh đến android system để mở màn hình 2.
                }
            });
        }
    }
}
