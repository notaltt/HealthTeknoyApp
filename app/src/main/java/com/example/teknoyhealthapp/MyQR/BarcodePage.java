package com.example.teknoyhealthapp.MyQR;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.example.teknoyhealthapp.Dashboard;
import com.example.teknoyhealthapp.LoginForm;
import com.example.teknoyhealthapp.R;
import com.example.teknoyhealthapp.RegistrationForm;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class BarcodePage extends AppCompatActivity {

    private ImageView barcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_page);

        barcode = findViewById(R.id.barcode);

        Intent intent = getIntent();
        String name = intent.getStringExtra("username");

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(name, BarcodeFormat.QR_CODE, 850, 850);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            barcode.setImageBitmap(bitmap);
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(barcode.getApplicationWindowToken(), 0);
        } catch (WriterException e){
            e.printStackTrace();
        }

    }
}