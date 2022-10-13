package com.example.teknoyhealthapp.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;

import com.example.teknoyhealthapp.R;

import java.util.Calendar;

public class CalendarPage extends AppCompatActivity {

    private Calendar calendar;
    private CalendarView calendarView;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);

        calendarView = findViewById(R.id.calendarView);;

        calendarView.setFirstDayOfWeek(calendar.MONDAY);
        calendarView.setFocusedMonthDateColor(getResources().getColor(R.color.maroon));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

            }
        });
    }
}