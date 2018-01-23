package com.example.root.shop;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_SCREEN_LENGHT=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
            }
        },SPLASH_SCREEN_LENGHT);
    }
}
