package com.example.root.shop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private Button btn_login;
    private Button btn_creat;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText=findViewById(R.id.txt_email);
        passwordText=findViewById(R.id.txt_password);
        btn_creat=findViewById(R.id.btn_login);
        btn_login=findViewById(R.id.btn_newUser);
        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progress);
        session=new UserSessionManager(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }
    private void userLogin(){
        final String Email=emailText.getText().toString();
        final String Password=passwordText.getText().toString();
        if(Email.isEmpty()){
            emailText.setError("من فضلك أدخل بريدك الألكترونى");
            return;
        }
        if(Password.isEmpty()){
            passwordText.setError("من فضلك أدخل كلمة المرور");
            return;
        }
           progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        session.CreatUserLoginSession(Email,Password);
                        Intent intent=new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(loginActivity.this, "كلمة المرور او البريد اللالكترونى غير صحيح", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        
    }
}
