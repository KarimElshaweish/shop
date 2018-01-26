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

import java.util.HashMap;

public class Covers extends AppCompatActivity {

    String Name="كافر ايفون X";
    String Dec="iPhone X Cover";
    String Price="50ريال";
    String Prices[]={Price,Price,Price,Price};
    String Names[]={Name,Name,Name,Name};
    String Decs[]={Dec,Dec,Dec,Dec};
    int icons[]={R.drawable.covers,R.drawable.covers,R.drawable.covers,R.drawable.covers};
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covers);
        gridView=findViewById(R.id.gridView);
        GridViewMobile gridViewMobile=new GridViewMobile(icons,Names,this,Decs,Prices);
        gridView.setAdapter(gridViewMobile);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final info inf=new info();
                final ImageView imageView=view.findViewById(R.id.img_like);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BitmapDrawable bitmapDrawable= (BitmapDrawable) imageView.getDrawable();
                        Bitmap imgviewBitmap=bitmapDrawable.getBitmap();
                        Drawable likcom=getResources().getDrawable(R.drawable.like_iconmdpi);
                        Bitmap like=((BitmapDrawable)likcom).getBitmap();
                        if(like.sameAs(imgviewBitmap)){
                            imageView.setImageResource(R.drawable.like_fill);
                            inf.addDec(Decs[i]);
                            inf.addDraw(icons[i]);
                            inf.addPrice(Prices[i]);
                            inf.addName(Names[i]);
                            String method="insertLike";
                            Background_Task background_task=new Background_Task(getBaseContext());
                            UserSessionManager sessionManager=new UserSessionManager(getBaseContext());
                            HashMap<String,String> User=sessionManager.getUserDatails();
                            background_task.execute(method,Names[i],User.get("email"),"covers");

                        }
                        else{
                            imageView.setImageResource(R.drawable.like_iconmdpi);
                        }
                    }
                });

            }
        });
    }
}
