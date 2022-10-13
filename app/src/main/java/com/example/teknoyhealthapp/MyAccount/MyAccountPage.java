package com.example.teknoyhealthapp.MyAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.teknoyhealthapp.R;
import com.example.teknoyhealthapp.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyAccountPage extends AppCompatActivity {

    //variables
    private TextView textUsername, textFullName, textEmail2, textAddress, textPhone;
    private AutoCompleteTextView autoComplete;
    private AppCompatButton updateButton;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private ArrayAdapter<String> adapter;
    private String[] arrayGender = {"MALE", "FEMALE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_page);

        //hook up xml files
        textUsername = findViewById(R.id.textUsername);
        textFullName = findViewById(R.id.textFullName);
        textEmail2 = findViewById(R.id.textEmail2);
        textAddress = findViewById(R.id.textAddress);
        textPhone = findViewById(R.id.textPhone);
        autoComplete = findViewById(R.id.autoComplete);
        updateButton = findViewById(R.id.updateButton);

        adapter = new ArrayAdapter<String>(this, R.layout.layout_gender,arrayGender);
        autoComplete.setAdapter(adapter);

        Intent intent = getIntent();

        String usernameCurrent = intent.getStringExtra("username");
        String nameCurrent = intent.getStringExtra("fullName");
        String emailCurrent = intent.getStringExtra("email");
        String phoneCurrent = intent.getStringExtra("phoneNumber");
        String passwordCurrent = intent.getStringExtra("password");
        String addressCurrent = intent.getStringExtra("address");
        String genderCurrent = intent.getStringExtra("classification");

        textUsername.setText(usernameCurrent);
        textFullName.setText(nameCurrent);
        textEmail2.setText(emailCurrent);
        textPhone.setText(phoneCurrent);
        textAddress.setText(addressCurrent);
        autoComplete.setText(genderCurrent);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance("https://teknoyhealthapp-default-rtdb.asia-southeast1.firebasedatabase.app");
                reference = rootNode.getReference("User");

                String email = textEmail2.getText().toString();
                String name = textFullName.getText().toString();
                String number = textPhone.getText().toString();
                String address = textAddress.getText().toString();
                String gender = autoComplete.getText().toString();

                User useR = new User(name, gender, address, email, passwordCurrent, number, "", "", usernameCurrent, "", "", "");

                reference.child(usernameCurrent).setValue(useR);
            }
        });
    }
}