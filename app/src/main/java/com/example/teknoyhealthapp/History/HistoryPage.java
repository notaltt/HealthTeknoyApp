package com.example.teknoyhealthapp.History;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.teknoyhealthapp.R;

public class HistoryPage extends AppCompatActivity {

    private TextView timeIn, timeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        timeIn = findViewById(R.id.timeIn);
        timeOut = findViewById(R.id.timeOut);



    }
}