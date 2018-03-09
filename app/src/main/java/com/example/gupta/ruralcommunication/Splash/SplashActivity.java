package com.example.gupta.ruralcommunication.Splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.gupta.ruralcommunication.HomeActivity.View.HomeActivity;
import com.example.gupta.ruralcommunication.R;
import com.example.gupta.ruralcommunication.SelectVillage.View.SelectVillageActivity;

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
                //Intent intent=new Intent(SplashActivity.this, MainActivity.class);
//                View view1=getLayoutInflater().inflate(R.layout.report_problem_sheet,null);
//                BottomSheetDialog sheetDialog=new BottomSheetDialog(SplashActivity.this);
//                sheetDialog.setCanceledOnTouchOutside(false);
//                Button reportButton=view1.findViewById(R.id.write_mail_button);
//                reportButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent((Intent.ACTION_VIEW), Uri.parse("mailto:"+"rural.heavean@rucomm.com"));
//                        intent.putExtra(Intent.EXTRA_SUBJECT,"New Issue in Concerned Region");
//                        startActivity(intent);
//                    }
//                });
//                sheetDialog.setContentView(view1);
//                sheetDialog.show();
                Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
