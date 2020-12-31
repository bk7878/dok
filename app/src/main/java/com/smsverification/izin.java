package com.smsverification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class izin extends AppCompatActivity {
    private static final int REQ_USER_CONSENT = 200;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izin);



        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              kapat();
            }
        }, 10500);




        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        );


    }


    public void kapat(){

        this.finish();
    }




}
