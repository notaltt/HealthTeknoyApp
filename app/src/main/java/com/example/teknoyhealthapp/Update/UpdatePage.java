package com.example.teknoyhealthapp.Update;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.teknoyhealthapp.R;

public class UpdatePage extends AppCompatActivity {

    private TextView updateText, updateDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);

        updateText = findViewById(R.id.updateText);
        updateDate = findViewById(R.id.updateDate);

        Intent intent = getIntent();

       String currentUpdate = intent.getStringExtra("update");

       updateText.setText(currentUpdate);


    }
}