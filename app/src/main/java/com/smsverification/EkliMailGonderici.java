package com.smsverification;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

// https://harun.xyz/

public class EkliMailGonderici extends AsyncTask<Void,Void,String> {

    private Context context;
    private Session session;

    // Mesajın gideceği adres
    private String alici_mail_adresi ;
    private String konu;
    private String mesaj;

    private ProgressDialog progressDialog;

    public EkliMailGonderici(Context context, String alici_mail_adresi , String konu, String mesaj){
        this.context = context;
        this.alici_mail_adresi  = alici_mail_adresi;
        this.konu = konu;
        this.mesaj = mesaj;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,"Mail Yollanıyor","Lütfen Bekleyiniz...",false,false);
    }

    @Override
    protected String doInBackground(Void... voids) {

        Properties props = new Properties();

        //Mail konfigürasyon ayarları gmail kullandığımız için bunlar sabit kalsın
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    //Ana Mailimize giriş yapıyoruz
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(YapilandirmaVerileri.MAIL, YapilandirmaVerileri.SIFRE);
                    }

                });

        try {

            MimeMessage mm = new MimeMessage(session);

            //Gönderici adresi
            mm.setFrom(new InternetAddress(YapilandirmaVerileri.MAIL));

            //Alıcı adresi
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(alici_mail_adresi));

            //Konu
            mm.setSubject(konu);

            //Mesaj
            //mm.setText(mesaj);

            // Android Studio Ekli Mail Gönderme
            //************************************************************************

            // UTF-8 Destekli Mesajı Eklediğimiz Kısım
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(mesaj, "text/plain; charset=UTF-8");

            // Dosya eki olacağını ve multi data girebilmek için MimeMultipart oluşturduğumuz kısım
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            MimeMultipart mimeMultipart = new MimeMultipart();

            // Göndereceğimiz pdfin ismini girdiğimiz yer (Bu pdf dahili depolama alanında bulunduğu varsayılan bir pdftir)
            File pdfDosyamiz = new File(Environment.getExternalStorageDirectory(),"test.pdf");

            // Pdf dosyamızı mail ekine eklediğimiz kısım
            DataSource source = new FileDataSource(pdfDosyamiz);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(pdfDosyamiz.getName());

            // MultiParta Mesajı ve Pdfi eklediğimiz kısım
            mimeMultipart.addBodyPart(attachmentBodyPart,0);
            mimeMultipart.addBodyPart(messageBodyPart,1);

            // Maile ekleri koyduğumuz kısım
            mm.setContent(mimeMultipart);

            // Android Studio Otomatik Mail Gönderme
            //************************************************************************

            //Mail gönderme anı
            Transport.send(mm);

            return "basarili";

        } catch (MessagingException e) {

            Log.e("hata","Hata : "+e.getMessage());

            return null;

        }

    }

    @Override
    protected void onPostExecute(String sonuc) {

        progressDialog.dismiss();

        // Eğer dönen sonuç null değilse mesaj gitmiştir
        if(sonuc != null){
            Toast.makeText(context,"İlgili Bilgiler Mailinize Gönderildi!",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(context,"İşlem Başarısız, Dahili Depolamada test.pdf isimli dosyanın varlığından emin olunuz! Ayrıca uygulamaya okuma izni veriniz!",Toast.LENGTH_LONG).show();
        }

    }
}
