package com.example.teknoyhealthapp.MyQR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teknoyhealthapp.History.HistoryPage;
import com.example.teknoyhealthapp.LoginForm;
import com.example.teknoyhealthapp.MainActivity;
import com.example.teknoyhealthapp.MyAccount.MyAccountPage;
import com.example.teknoyhealthapp.R;
import com.example.teknoyhealthapp.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MyQRPage extends AppCompatActivity {

    private EditText textYesNo, textSpecify, textTemperature;
    private ListView listview_symptoms, listview_exposed;
    private AppCompatButton generateButton;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private int i;
    String[] arraySymptoms = {"FEVER", "COUGH", "SNEEZE", "CHILLS", "COLDS", "DIFFICULTY BREATHING",
            "MUSCLE PAIN", " SORE THROAT", "LOSS OF SENSE OF TASTE/SMELL", "HEADACHE", "NONE"};
    String[] arrayExposed = {"EXPOSED TO COVID19 POSITIVE", "FAMILY MEMBER/S HAS SYMPTOMS BUT NOT CONFIRMED", "NONE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qrpage);

        //hook up xml file
        textYesNo = findViewById(R.id.textYesNo);
        textSpecify = findViewById(R.id.textSpecify);
        textTemperature = findViewById(R.id.textTemperature);
        listview_symptoms = findViewById(R.id.listview_symptoms);
        listview_exposed = findViewById(R.id.listview_exposed);
        generateButton = findViewById(R.id.generateButton);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arraySymptoms);
        listview_symptoms.setAdapter(adapter);

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayExposed);
        listview_exposed.setAdapter(adapter2);

        Intent intent = getIntent();

        String usernameCurrent = intent.getStringExtra("username");
        String nameCurrent = intent.getStringExtra("fullName");
        String emailCurrent = intent.getStringExtra("email");
        String phoneCurrent = intent.getStringExtra("phoneNumber");
        String passwordCurrent = intent.getStringExtra("password");
        String addressCurrent = intent.getStringExtra("address");
        String genderCurrent = intent.getStringExtra("classification");
        String recentCurrent = intent.getStringExtra("recentExposure");
        String symptomsCurrent = intent.getStringExtra("symptoms");

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(printSelectedRecent().equals("NONE") && printSelectedSymptoms().equals("NONE")){
                        rootNode = FirebaseDatabase.getInstance("https://teknoyhealthapp-default-rtdb.asia-southeast1.firebasedatabase.app");
                        reference = rootNode.getReference("User");

                        String travel = textYesNo.getText().toString();
                        String specify = textSpecify.getText().toString();
                        String temperature = textTemperature.getText().toString();
                        String recent = printSelectedRecent();
                        String symptoms = printSelectedSymptoms();

                        if(recent.equals("NONE")){
                            recent = "YES";
                        }else {
                            recent = "NO";
                        }

                        if(symptoms.equals("NONE")){
                            symptoms = "YES";
                        }else{
                            symptoms = "NO";
                        }

                        if(travel.equals("NO")){
                            specify = "";
                        }else{
                        }

                        User useR = new User(nameCurrent, genderCurrent, addressCurrent, emailCurrent, passwordCurrent, phoneCurrent, "",
                                temperature, usernameCurrent, specify, recent, symptoms);

                        reference.child(usernameCurrent).setValue(useR);

                        makeIntent();
                        /*String finalSpecify = specify;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                User useR = new User(nameCurrent, genderCurrent, addressCurrent, emailCurrent, passwordCurrent, phoneCurrent, "",
                                        temperature, usernameCurrent, finalSpecify, "", "");

                                reference.child(usernameCurrent).setValue(useR);
                            }
                        },20000);*/
                    }else{
                        Toast.makeText(getApplicationContext(), "SORRY YOU CAN'T GENERATE A BARCODE. PLEASE CONSULT A DOCTOR.", Toast.LENGTH_SHORT).show();
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
}