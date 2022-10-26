package com.example.teknoyhealthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.teknoyhealthapp.About.AboutPage;
import com.example.teknoyhealthapp.Calendar.CalendarPage;
import com.example.teknoyhealthapp.Update.UpdatePage;
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
        String emailCurrent = intent.getStringExtra("email");
        String barcodeCurrent = intent.getStringExtra("barcode");

        textEmail.setText(emailCurrent);
    }

    public void openCalendar(View view) {
        intentUser(CalendarPage.class);
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
                    String updateDB = snapshot.child(emailCurrent).child("update").getValue(String.class);
                    String barcodeDB = snapshot.child(emailCurrent).child("barcode").getValue(String.class);
                    String genderDB = snapshot.child(emailCurrent).child("classification").getValue(String.class);

                    Intent intent;
                    if(barcodeDB.equals("YES")){
                        intent = new Intent(getApplicationContext(), BarcodePage.class);

                        intent.putExtra("fullName", namedDB);
                        intent.putExtra("email", emailDB);
                        intent.putExtra("phoneNumber", phoneDB);
                        intent.putExtra("username", usernameDB);
                        intent.putExtra("password", passwordDB);
                        intent.putExtra("address", addressDB);
                        intent.putExtra("symptoms", symptomsDB);
                        intent.putExtra("recentExposure", recentDB);
                        intent.putExtra("update", updateDB);
                        intent.putExtra("barcode", barcodeDB);
                        intent.putExtra("classification", genderDB);

                        startActivity(intent);
                    }else{
                        if(namedDB.isEmpty() || emailDB.isEmpty() || addressDB.isEmpty() || phoneDB.isEmpty() || genderDB.isEmpty()){
                            AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                            builder.setTitle("Fill out all data in the My Account Page")
                                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    }).show();
                        }else{
                            intent = new Intent(getApplicationContext(), MyQRPage.class);

                            intent.putExtra("fullName", namedDB);
                            intent.putExtra("email", emailDB);
                            intent.putExtra("phoneNumber", phoneDB);
                            intent.putExtra("username", usernameDB);
                            intent.putExtra("password", passwordDB);
                            intent.putExtra("address", addressDB);
                            intent.putExtra("symptoms", symptomsDB);
                            intent.putExtra("recentExposure", recentDB);
                            intent.putExtra("update", updateDB);
                            intent.putExtra("barcode", barcodeDB);
                            intent.putExtra("classification", genderDB);

                            startActivity(intent);
                        }
                    }
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openAccount(View view) {
        intentUser(MyAccountPage.class);
    }

    public void openUpdate(View view) {
        intentUser(UpdatePage.class);
    }

    public void openAbout(View view) {
        intentUser(AboutPage.class);
    }

    public void intentUser(Class c){
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
                    String updateDB = snapshot.child(emailCurrent).child("update").getValue(String.class);
                    String barcodeDB = snapshot.child(emailCurrent).child("barcode").getValue(String.class);
                    String genderDB = snapshot.child(emailCurrent).child("classification").getValue(String.class);
                    String tempDB = snapshot.child(emailCurrent).child("temperature").getValue(String.class);

                    Intent intent;
                    intent = new Intent(getApplicationContext(), c);

                    intent.putExtra("fullName", namedDB);
                    intent.putExtra("email", emailDB);
                    intent.putExtra("phoneNumber", phoneDB);
                    intent.putExtra("username", usernameDB);
                    intent.putExtra("password", passwordDB);
                    intent.putExtra("address", addressDB);
                    intent.putExtra("symptoms", symptomsDB);
                    intent.putExtra("recentExposure", recentDB);
                    intent.putExtra("update", updateDB);
                    intent.putExtra("barcode", barcodeDB);
                    intent.putExtra("classification", genderDB);
                    intent.putExtra("temperature", tempDB);

                    startActivity(intent);
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}