package com.example.root.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by root on 1/22/18.
 */

public class GridViewMobile extends BaseAdapter {

    private int icons[];
    private String Letter[];
    private Context ctx;
    private LayoutInflater layoutInflater;
    private String Decription[];
    private String price[];
    private boolean like=false;

    public GridViewMobile(int[] icons, String[] letter, Context ctx, String[] decription, String[] price, boolean like) {
        this.icons = icons;
        Letter = letter;
        this.ctx = ctx;
        Decription = decription;
        this.price = price;
        this.like = like;
    }

    public GridViewMobile(int[] icons, String[] letter, Context ctx, String[] decription, String[]Price) {
        this.icons = icons;
        Letter = letter;
        this.ctx = ctx;
        Decription = decription;
        this.price=Price;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int i) {
        return Letter[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View gridView=view;
        if(view==null){
            layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView=layoutInflater.inflate(R.layout.item,null);
        }
        ImageView imageView=gridView.findViewById(R.id.img);
        TextView Dec=gridView.findViewById(R.id.text_decription);
        TextView Name=gridView.findViewById(R.id.text_name);
        TextView Price=gridView.findViewById(R.id.txt_price);
        imageView.setImageResource(icons[i]);
        Dec.setText(Decription[i]);
        Name.setText(Letter[i]);
        Price.setText(price[i]);
        if(like){
            ImageView likeimg=gridView.findViewById(R.id.img_like);
            likeimg.setImageResource(R.drawable.like_fill);
        }
        return gridView;
    }
}
