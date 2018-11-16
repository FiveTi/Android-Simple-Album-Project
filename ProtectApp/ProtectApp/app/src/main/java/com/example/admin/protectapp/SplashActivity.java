package com.example.admin.protectapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    SharedPreferences sharedPreferences;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //load password
        sharedPreferences = getSharedPreferences("PREFERENCES", 0);
        pass = sharedPreferences.getString("password","");


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(pass.equals("")){
                    //Nếu không có password
                    Intent intent = new Intent(getApplicationContext(), CreatePassActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    //Nếu có password
                    Intent intent = new Intent(getApplicationContext(), ConfirmPassActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 1500);
    }
}
