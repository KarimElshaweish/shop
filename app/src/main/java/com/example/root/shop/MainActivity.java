package com.example.root.shop;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private UserSessionManager session;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentManager  fragmentManager=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_categpory:
                    fragmentTransaction.replace(R.id.content,new Category()).commit();
                    return  true;
                case R.id.navigation_home:
                    fragmentTransaction.replace(R.id.content,new Home()).commit();
                    return true;
                case R.id.navigation_account:
                    fragmentTransaction.replace(R.id.content,new Profile()).commit();
                    return true;
                case R.id.navigation_shop:
                    fragmentTransaction.replace(R.id.content,new ShopFragment()).commit();
                    return true;
                case R.id.navigation_likes:
                    fragmentTransaction.replace(R.id.content,new Likes()).commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation =findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        android.support.v4.app.FragmentManager  fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content,new Category()).commit();
        session=new UserSessionManager(getApplicationContext());
        if(session.checkLogin())
        {
            finish();
            Intent intent=new Intent(getApplicationContext(),FristActivity.class);
            startActivity(intent);
        }
    }

}
