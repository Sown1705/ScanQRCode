package com.example.myqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    TextView tvFB,tvAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tvFB=findViewById(R.id.tvFB);
        tvAddress=findViewById(R.id.tvAdress);
        tvFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    goToURL("https://www.facebook.com/TrinhCongSown/");
            }
        });
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToURL("https://goo.gl/maps/T36a4YArmcRiTd5T9");
            }
        });
    }
    private void goToURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}