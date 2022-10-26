package com.example.teknoyhealthapp.Update;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.teknoyhealthapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdatePage extends AppCompatActivity {

    private TextView updateText, updateDate, toolbar_title;
    private String currentDate;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("EEE, MMMM d, yyyy");
        currentDate = simpleDateFormat.format(calendar.getTime());

        updateText = findViewById(R.id.updateText);
        updateDate = findViewById(R.id.updateDate);
        toolbar_title = findViewById(R.id.toolbar_title);

        Intent intent = getIntent();

        String currentUpdate = intent.getStringExtra("update");

        updateText.setText(currentUpdate);
        updateDate.setText(currentDate);
        toolbar_title.setText("School Update");
    }
}