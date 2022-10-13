package com.example.teknoyhealthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.teknoyhealthapp.About.AboutPage;
import com.example.teknoyhealthapp.Calendar.CalendarPage;
import com.example.teknoyhealthapp.History.HistoryPage;
import com.example.teknoyhealthapp.MyAccount.MyAccountPage;
import com.example.teknoyhealthapp.MyQR.BarcodePage;
import com.example.teknoyhealthapp.MyQR.MyQRPage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    private TextView textEmail;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //hook up xml file from activity_dashboard.xml
        textEmail = findViewById(R.id.textEmail);

        Intent intent = getIntent();
        String emailCurrent = intent.getStringExtra("username");

        textEmail.setText(emailCurrent);
    }

    public void openCalendar(View view) {
        Intent intent = new Intent(this, CalendarPage.class);
        startActivity(intent);
    }

    public void openQR(View view) {
        Intent intent = getIntent();
        String emailCurrent = intent.getStringExtra("username");
        reference = FirebaseDatabase.getInstance("https://teknoyhealthapp-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("User");
        Query checkUser = reference.orderByChild("username").equalTo(emailCurrent);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String namedDB = snapshot.child(emailCurrent).child("fullName").getValue(String.class);
                    String emailDB = snapshot.child(emailCurrent).child("email").getValue(String.class);
                    String phoneDB = snapshot.child(emailCurrent).child("phoneNumber").getValue(String.class);
                    String usernameDB = snapshot.child(emailCurrent).child("username").getValue(String.class);
                    String passwordDB = snapshot.child(emailCurrent).child("password").getValue(String.class);
                    String addressDB = snapshot.child(emailCurrent).child("address").getValue(String.class);
                    String symptomsDB = snapshot.child(emailCurrent).child("symptoms").getValue(String.class);
                    String recentDB = snapshot.child(emailCurrent).child("recentExposure").getValue(String.class);

                    Intent intent;
                    if(symptomsDB.equals("YES") && recentDB.equals("YES")){
                        intent = new Intent(getApplicationContext(), BarcodePage.class);
                    }else{
                        intent = new Intent(getApplicationContext(), MyQRPage.class);
                    }
                    intent.putExtra("fullName", namedDB);
                    intent.putExtra("email", emailDB);
                    intent.putExtra("phoneNumber", phoneDB);
                    intent.putExtra("username", usernameDB);
                    intent.putExtra("password", passwordDB);
                    intent.putExtra("address", addressDB);
                    intent.putExtra("symptoms", symptomsDB);
                    intent.putExtra("recentExposure", recentDB);

                    startActivity(intent);
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openAccount(View view) {
        Intent intent = getIntent();
        String emailCurrent = intent.getStringExtra("username");
        reference = FirebaseDatabase.getInstance("https://teknoyhealthapp-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("User");
        Query checkUser = reference.orderByChild("username").equalTo(emailCurrent);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String namedDB = snapshot.child(emailCurrent).child("fullName").getValue(String.class);
                    String emailDB = snapshot.child(emailCurrent).child("email").getValue(String.class);
                    String phoneDB = snapshot.child(emailCurrent).child("phoneNumber").getValue(String.class);
                    String usernameDB = snapshot.child(emailCurrent).child("username").getValue(String.class);
                    String passwordDB = snapshot.child(emailCurrent).child("password").getValue(String.class);
                    String addressDB = snapshot.child(emailCurrent).child("address").getValue(String.class);

                    Intent intent = new Intent(getApplicationContext(), MyAccountPage.class);

                    intent.putExtra("fullName", namedDB);
                    intent.putExtra("email", emailDB);
                    intent.putExtra("phoneNumber", phoneDB);
                    intent.putExtra("username", usernameDB);
                    intent.putExtra("password", passwordDB);
                    intent.putExtra("address", addressDB);

                    startActivity(intent);
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openHistory(View view) {
        Intent intent = getIntent();
        String emailCurrent = intent.getStringExtra("username");
        reference = FirebaseDatabase.getInstance("https://teknoyhealthapp-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("User");
        Query checkUser = reference.orderByChild("username").equalTo(emailCurrent);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String namedDB = snapshot.child(emailCurrent).child("fullName").getValue(String.class);
                    String emailDB = snapshot.child(emailCurrent).child("email").getValue(String.class);
                    String phoneDB = snapshot.child(emailCurrent).child("phoneNumber").getValue(String.class);
                    String usernameDB = snapshot.child(emailCurrent).child("username").getValue(String.class);
                    String passwordDB = snapshot.child(emailCurrent).child("password").getValue(String.class);
                    String addressDB = snapshot.child(emailCurrent).child("address").getValue(String.class);
                    String symptomsDB = snapshot.child(emailCurrent).child("symptoms").getValue(String.class);
                    String recentDB = snapshot.child(emailCurrent).child("recentExposure").getValue(String.class);

                    Intent intent;
                    intent = new Intent(getApplicationContext(), HistoryPage.class);

                    intent.putExtra("fullName", namedDB);
                    intent.putExtra("email", emailDB);
                    intent.putExtra("phoneNumber", phoneDB);
                    intent.putExtra("username", usernameDB);
                    intent.putExtra("password", passwordDB);
                    intent.putExtra("address", addressDB);
                    intent.putExtra("symptoms", symptomsDB);
                    intent.putExtra("recentExposure", recentDB);

                    startActivity(intent);
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openAbout(View view) {
        Intent intent = new Intent(this, AboutPage.class);
        startActivity(intent);
    }
}