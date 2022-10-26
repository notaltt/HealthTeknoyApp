package com.example.teknoyhealthapp.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.teknoyhealthapp.R;
import com.example.teknoyhealthapp.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarPage extends AppCompatActivity {

    TextView currentDateText, toolbar_title;
    RecyclerView listView;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String date;

    private DatabaseReference reference;
    private FirebaseDatabase rootNode;

    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);

        currentDateText = findViewById(R.id.currentDateText);
        listView = findViewById(R.id.listview_dates);
        toolbar_title = findViewById(R.id.toolbar_title);

        calendar = Calendar.getInstance();

        simpleDateFormat = new SimpleDateFormat("EEE, MMMM d, yyyy");
        date = simpleDateFormat.format(calendar.getTime());
        currentDateText.setText(date);

        toolbar_title.setText("QR code History");

        Intent intent = getIntent();
        String usernameCurrent = intent.getStringExtra("username");


        rootNode = FirebaseDatabase.getInstance("https://teknoyhealthapp-default-rtdb.asia-southeast1.firebasedatabase.app");
        reference = rootNode.getReference().child("Dates").child(usernameCurrent);

        listView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(reference, User.class)
                .build();

        adapter = new Adapter(options);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}