package com.example.gupta.ruralcommunication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserLogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log_in);
        TextView t1=(TextView) findViewById(R.id.name);
        if(t1.isHovered())
            t1.setText("");
    }
}
