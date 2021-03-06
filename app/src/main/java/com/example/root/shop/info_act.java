package com.example.root.shop;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.shop.config.Config;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
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
    String amount;
    private static final int  PAYPAL_REQUEST_CODE=7171;
    private PayPalConfiguration config=new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);
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
        Button btn=findViewById(R.id.cheap_btn);
        Intent intent=new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getBaseContext(), "تم الشحن للعنوان المدون", Toast.LENGTH_SHORT).show();
                processAmount();
            }
        });
        spinner1=findViewById(R.id.spinner1);
        String[]items=new String[]{"اللون","أسود","ابيض"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,items);
        spinner1.setAdapter(adapter);
    }
    private void processAmount(){
        amount="5";
        PayPalPayment payPalPayment=new PayPalPayment(new BigDecimal(String.valueOf(amount)),"USD",
                "Donate for EDMTDev",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent=new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PAYPAL_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                PaymentConfirmation confirmation=data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation!=null){
                    try{
                        String paymentDeatils=confirmation.toJSONObject().toString(4);
                        Toast.makeText(this, paymentDeatils+"$$"+amount, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(resultCode== Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cansel", Toast.LENGTH_SHORT).show();
        }
        else if(resultCode==PaymentActivity.RESULT_EXTRAS_INVALID){
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }
}
