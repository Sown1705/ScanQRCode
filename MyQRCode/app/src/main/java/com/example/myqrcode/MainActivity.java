package com.example.myqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    TextView  tvResult;
    IntentIntegrator intentIntegrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        tvResult = findViewById(R.id.tvOther);
        intentIntegrator = new IntentIntegrator(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            tvResult.setText(result.getContents());
            tvResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(tvResult.getText().toString()));
                    startActivity(intent);
                }
            });
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(400);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.scanQR:
                ScanQR();
                return true;
            case R.id.newQR:
                NewQR();
                return true;
            case R.id.about:
                About();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void About() {
        startActivity(new Intent(MainActivity.this,AboutActivity.class));
    }

    private void NewQR() {
//        Intent inten = new Intent(Intent.ACTION_VIEW);
//        inten.setData(Uri.parse("https://9qrcode.com/?lang=vi"));
        Intent intent = new Intent(MainActivity.this, GenerateActivity.class);
        startActivity(intent);
    }

    private void ScanQR() {
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        intentIntegrator.setPrompt("Đang đọc ma QR, Pleases waiting..!!!");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.initiateScan();
    }
}