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

public class GridViewAdapter extends BaseAdapter {
    private int icons[];
    private String Letter[];
    private Context ctx;
    private LayoutInflater layoutInflater;

    public GridViewAdapter(int icons[], String[] letter, Context ctx) {
        this.icons = icons;
        Letter = letter;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return  Letter.length;
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
            gridView=layoutInflater.inflate(R.layout.item_main,null);
        }
        ImageView icon=gridView.findViewById(R.id.img);
        TextView txt=gridView.findViewById(R.id.text);
        icon.setImageResource(icons[i]);
        txt.setText(Letter[i]);
        return gridView;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
