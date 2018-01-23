package com.example.root.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class info_act extends AppCompatActivity {

    ImageView head;
    ImageView mInfoImageView;
    ExpandableRelativeLayout expandableRelativeLayout;
    Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_act);
        head=findViewById(R.id.headimg);
        info inf=new info();
        head.setImageResource(inf.img);
        TextView txt=findViewById(R.id.text_decription);
        txt.setText(getIntent().getStringExtra("dec"));
        expandableRelativeLayout=findViewById(R.id.moreinfo);
        mInfoImageView=findViewById(R.id.image_view_seeinfo);
        mInfoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableRelativeLayout.toggle();
            }
        });

        spinner1=findViewById(R.id.spinner1);
        String[]items=new String[]{"اللون","أسود","ابيض"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,items);
        spinner1.setAdapter(adapter);
    }
}
