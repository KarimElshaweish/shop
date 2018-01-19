package com.example.root.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.Inet4Address;

public class FristActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnNewUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        btnLogin=findViewById(R.id.btn_login);
        btnNewUser=findViewById(R.id.btn_newUser);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),loginActivity.class);
                startActivity(intent);
            }
        });
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),SignupActivity.class);
                startActivity(intent);
            }
        });
    }


}
