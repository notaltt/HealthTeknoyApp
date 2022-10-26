package com.example.teknoyhealthapp.MyQR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teknoyhealthapp.Dashboard;
import com.example.teknoyhealthapp.R;
import com.example.teknoyhealthapp.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyQRPage extends AppCompatActivity {

    private EditText textYesNo, textSpecify;
    private ListView listview_symptoms, listview_exposed;
    private TextView toolbar_title;
    private AppCompatButton generateButton;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference, reference2;
    private String currentDate;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private int i;
    private long maxId;
    String[] arraySymptoms = {"FEVER", "COUGH", "SNEEZE", "CHILLS", "COLDS", "DIFFICULTY BREATHING",
            "MUSCLE PAIN", " SORE THROAT", "LOSS OF SENSE OF TASTE/SMELL", "HEADACHE", "NONE"};
    String[] arrayExposed = {"EXPOSED TO COVID19 POSITIVE", "FAMILY MEMBER/S HAS SYMPTOMS", "NONE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qrpage);

        //hook up xml file
        textYesNo = findViewById(R.id.textYesNo);
        textSpecify = findViewById(R.id.textSpecify);
        listview_symptoms = findViewById(R.id.listview_symptoms);
        listview_exposed = findViewById(R.id.listview_exposed);
        generateButton = findViewById(R.id.generateButton);
        toolbar_title = findViewById(R.id.toolbar_title);

        toolbar_title.setText("Health Declaration Form");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arraySymptoms);
        listview_symptoms.setAdapter(adapter);

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayExposed);
        listview_exposed.setAdapter(adapter2);

        Intent intent = getIntent();

        rootNode = FirebaseDatabase.getInstance("https://teknoyhealthapp-default-rtdb.asia-southeast1.firebasedatabase.app");
        reference = rootNode.getReference("User");

        String usernameCurrent = intent.getStringExtra("username");
        String nameCurrent = intent.getStringExtra("fullName");
        String emailCurrent = intent.getStringExtra("email");
        String phoneCurrent = intent.getStringExtra("phoneNumber");
        String passwordCurrent = intent.getStringExtra("password");
        String addressCurrent = intent.getStringExtra("address");
        String genderCurrent = intent.getStringExtra("classification");
        String recentCurrent = intent.getStringExtra("recentExposure");
        String symptomsCurrent = intent.getStringExtra("symptoms");
        String temperatureCurrent = intent.getStringExtra("temperature");
        String timeCurrent = intent.getStringExtra("timeVisit");
        String updateCurrent = intent.getStringExtra("update");

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateSpecify() || !validateTextYesNo() || printSelectedSymptoms().isEmpty() || printSelectedRecent().isEmpty()){
                    Toast.makeText(getApplicationContext(), "FIELDS CANNOT BE EMPTY", Toast.LENGTH_SHORT).show();
                    return;
                }
                    if(printSelectedRecent().equals("NONE") && printSelectedSymptoms().equals("NONE")){
                        String travel = textYesNo.getText().toString();
                        String specify = textSpecify.getText().toString();

                        if(travel.equals("NO")){
                            specify = "";
                        }else{
                        }

                        User useR = new User(nameCurrent, genderCurrent, addressCurrent, emailCurrent, passwordCurrent, phoneCurrent, timeCurrent,
                                temperatureCurrent, usernameCurrent, specify, "NO", "NO", "", "YES", updateCurrent);

                        reference.child(usernameCurrent).setValue(useR);

                        reference2 = rootNode.getReference("Dates");

                        calendar = Calendar.getInstance();
                        simpleDateFormat = new SimpleDateFormat("EEE, MMMM d, yyyy");
                        currentDate = simpleDateFormat.format(calendar.getTime());

                        User user2 = new User(currentDate);

                        reference2.child(usernameCurrent).child(currentDate).setValue(user2);
                        makeIntent();

                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyQRPage.this);
                        builder.setTitle("Sorry you are not allowed" +
                                "\nto generate a barcode.")
                                .setMessage("contact admin for assistance")
                                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        String travel = textYesNo.getText().toString();
                                        String specify = textSpecify.getText().toString();
                                        String exposure, symptoms;

                                        if(travel.equals("NO")){
                                            specify = "";
                                        }else{
                                        }

                                        if(printSelectedRecent().equals("NONE")){
                                            exposure = "NO";
                                        }else{
                                            exposure = "YES";
                                        }

                                        if(printSelectedSymptoms().equals("NONE")){
                                            symptoms = "NO";
                                        }else{
                                            symptoms = "YES";
                                        }

                                        User useR = new User(nameCurrent, genderCurrent, addressCurrent, emailCurrent, passwordCurrent, phoneCurrent, "",
                                                "", usernameCurrent, specify, exposure, symptoms, "", "NO", updateCurrent);

                                        reference.child(usernameCurrent).setValue(useR);
                                    }
                                }).show();
                    }
            }
        });
    }



    public String printSelectedRecent(){
        String itemSelected ="";
        for(i = 0; i < listview_exposed.getCount(); i++){
            if(listview_exposed.isItemChecked(i)){
                itemSelected += listview_exposed.getItemAtPosition(i);
            }
        }
        return itemSelected;
    }

    public String printSelectedSymptoms(){
        String itemSelected ="";
        for(i = 0; i < listview_symptoms.getCount(); i++){
            if(listview_symptoms.isItemChecked(i)){
                itemSelected += listview_symptoms.getItemAtPosition(i);
            }
        }
        return itemSelected;
    }

    public void makeIntent(){
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

                    Intent intent = new Intent(getApplicationContext(), BarcodePage.class);

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

    public boolean validateTextYesNo(){
        String val = textYesNo.getText().toString();

        if(val.isEmpty()){
            textYesNo.setError("Field cannot be empty.");
            return false;
        }
        if (val.equals("YES") || val.equals("NO")) {
            textYesNo.setError(null);
            return true;
        }else {
            textYesNo.setError("Only accepts YES or NO");
            return false;
        }
    }

    public boolean validateSpecify(){
        String val = textSpecify.getText().toString();
        String val2 = textYesNo.getText().toString();
        if(val2.equals("NO")){
            textSpecify.setError(null);
            return true;
        }else{
            if(val.isEmpty()){
                textSpecify.setError("Field cannot be empty.");
                return false;
            }else{
                textSpecify.setError(null);
                return true;
            }
        }
    }
}