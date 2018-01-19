package com.example.root.shop;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNewUser;
    EditText email_txt;
    EditText password_txt;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnNewUser=findViewById(R.id.btn_newUser);
        email_txt=findViewById(R.id.email_txt);
        password_txt=findViewById(R.id.pass_txt);
        progressBar=findViewById(R.id.progress);
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }
    private void registerUser(){
        String Email=email_txt.getText().toString();
        String Password=password_txt.getText().toString();
        if(Email.isEmpty()){
            email_txt.setError("ادخل بريدك الالكترونى");
            email_txt.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email_txt.setError("رجاء ادخال البريد الالكترونى الصحيح");
            email_txt.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            password_txt.setError("ادخل الرقم السرى");
            password_txt.requestFocus();
            return;
        }
        if(Password.length()<6){
            email_txt.setError("كلمة السر قصيره جدا");
            email_txt.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "user register sucessful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(SignupActivity.this,"البريد الالكترونى مسجل من قبل",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(SignupActivity.this, "حدث خطأ", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_newUser:
                break;

        }
    }
}
