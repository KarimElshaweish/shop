package com.example.root.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class Electornic extends AppCompatActivity {
    String Name="جهاز كمبيوتر";
    String Dec="Computer devices";
    String Price="2000ريال";
    String Prices[]={Price,Price,Price,Price};
    String Names[]={Name,Name,Name,Name};
    String Decs[]={Dec,Dec,Dec,Dec};
    int icons[]={R.drawable.iphonex,R.drawable.iphonex,R.drawable.iphonex,R.drawable.iphonex};
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electornic);
        gridView=findViewById(R.id.gridView);
        GridViewMobile gridViewMobile=new GridViewMobile(icons,Names,this,Decs,Prices);
        gridView.setAdapter(gridViewMobile);

    }
}
