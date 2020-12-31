package com.smsverification;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;


public class tikla extends AppCompatActivity implements View.OnClickListener {
    private Button mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStart = findViewById(R.id.start);
        mStart.setOnClickListener(this);
        Toast.makeText(this, "acildi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Log.d("tikla", "onClick");
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(getBaseContext(), "開啟AutoClick輔助功能", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, AutoService.class);
                startService(intent);

            }
        }
    }

}
