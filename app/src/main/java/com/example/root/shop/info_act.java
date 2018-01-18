package com.example.root.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class info_act extends AppCompatActivity {

    BannerSlider bannerSlider;
    ImageView mInfoImageView;
    ExpandableRelativeLayout expandableRelativeLayout;
    Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_act);
        bannerSlider=findViewById(R.id.banner_slider_info);
        List<Banner> banners=new ArrayList<>();
        //add banner using image url
        // banners.add(new RemoteBanner("Put banner image url here ..."));
        //add banner using resource drawable
        banners.add(new DrawableBanner(R.drawable.iphonex));
        banners.add(new DrawableBanner(R.drawable.iphonex));
        banners.add(new DrawableBanner(R.drawable.iphonex));
        mInfoImageView=findViewById(R.id.image_view_seeinfo);
        expandableRelativeLayout=findViewById(R.id.moreinfo);
        mInfoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableRelativeLayout.toggle();
            }
        });
        spinner1=findViewById(R.id.spinner1);
        String[]items=new String[]{"اللون","1","2","3"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,items);
        spinner1.setAdapter(adapter);
    }
}
