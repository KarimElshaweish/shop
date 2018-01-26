package com.example.root.shop;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 1/26/18.
 */

public class ListViewAdapt extends BaseAdapter {
    ArrayList<Integer> icons;
    ArrayList<String> Names,Decs,Prices;
    Context ctx;
    LayoutInflater layoutInflater;

    public ListViewAdapt(ArrayList<Integer>icons, ArrayList<String> names, ArrayList<String> decs, ArrayList<String> prices, Context ctx) {
        this.icons = icons;
        Names = names;
        Decs = decs;
        Prices = prices;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return Names.size();
    }

    @Override
    public String getItem(int i) {
        return Names.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View lisview=view;
        if(lisview==null){
            layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            lisview=layoutInflater.inflate(R.layout.item_shop,null);
        }
        ImageView imgview=lisview.findViewById(R.id.item_image);
        TextView txt_Name=lisview.findViewById(R.id.name_text);
        TextView txt_Price=lisview.findViewById(R.id.price_text);
        Button btn_remove=lisview.findViewById(R.id.btn_remove);
        imgview.setImageResource(icons.get(i));
        txt_Name.setText(Names.get(i));
        txt_Price.setText(Prices.get(i));
        return lisview;
    }
}
