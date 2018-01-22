package com.example.root.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class Cable extends AppCompatActivity {
    String Name="كابل usb";
    String Dec="Usb cabl";
    String Price="10ريال";
    String Prices[]={Price,Price,Price,Price};
    String Names[]={Name,Name,Name,Name};
    String Decs[]={Dec,Dec,Dec,Dec};
    int icons[]={R.drawable.covers,R.drawable.covers,R.drawable.covers,R.drawable.covers};
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cable);
        gridView=findViewById(R.id.gridView);
        GridViewMobile gridViewMobile=new GridViewMobile(icons,Names,this,Decs,Prices);
        gridView.setAdapter(gridViewMobile);

    }
}
