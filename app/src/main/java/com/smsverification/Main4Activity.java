package com.smsverification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {
EditText deneme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        deneme = findViewById(R.id.aaab);



        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        deneme.setText(sessionId);




    }
}
