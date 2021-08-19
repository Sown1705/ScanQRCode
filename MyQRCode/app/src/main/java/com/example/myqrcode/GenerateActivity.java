package com.example.myqrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateActivity extends AppCompatActivity {
    EditText edtInput;
    Button btnGenerate;
    ImageView imgOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_q_r);
        init();
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText=edtInput.getText().toString().trim();
                //Initialize multi format writer
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    //Initialize bitmatrix
                    BitMatrix bitMatrix=writer.encode(sText, BarcodeFormat.QR_CODE,350,350);
                    //Initialize barcode encoder
                    BarcodeEncoder  encoder = new BarcodeEncoder();
                    //Initialize bitmap
                    Bitmap bitmap =encoder.createBitmap(bitMatrix);
                    //set bitmap on image view
                    imgOutput.setImageBitmap(bitmap);
                    //Initialize input manager
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    //hide soft keybroad
                    inputMethodManager.hideSoftInputFromWindow(edtInput.getApplicationWindowToken(),0);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }

    private void init() {
        edtInput=findViewById(R.id.edtInput);
        btnGenerate=findViewById(R.id.btnGenerate);
        imgOutput=findViewById(R.id.imgOutput);
    }
}