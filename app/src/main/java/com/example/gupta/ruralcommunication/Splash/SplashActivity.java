package com.example.gupta.ruralcommunication.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.example.gupta.ruralcommunication.MainActivity;
import com.example.gupta.ruralcommunication.R;

public class SplashActivity extends AppCompatActivity {

    LottieAnimationView mIndiaAnimationView;
    LottieAnimationView mScanningAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mIndiaAnimationView=findViewById(R.id.india_animation);
        mScanningAnimationView=findViewById(R.id.scanning_animation);
        mScanningAnimationView.playAnimation();
        mIndiaAnimationView.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
