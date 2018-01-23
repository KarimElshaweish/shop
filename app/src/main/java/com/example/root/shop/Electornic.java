package com.example.root.shop;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

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
        final info inf=new info();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final ImageView imageView=view.findViewById(R.id.img_like);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BitmapDrawable bitmapDrawable= (BitmapDrawable) imageView.getDrawable();
                        Bitmap imgviewBitmap=bitmapDrawable.getBitmap();
                        Drawable likcom=getResources().getDrawable(R.drawable.like_iconmdpi);
                        Bitmap like=((BitmapDrawable)likcom).getBitmap();
                        if(like.sameAs(imgviewBitmap)) {
                            imageView.setImageResource(R.drawable.like_fill);
                            inf.addDec(Decs[i]);
                            inf.addDraw(icons[i]);
                            inf.addPrice(Prices[i]);
                            inf.addName(Names[i]);
                        }
                        else
                            imageView.setImageResource(R.drawable.like_iconmdpi);
                    }
                });

            }
        });

    }
}
