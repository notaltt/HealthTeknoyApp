package com.example.teknoyhealthapp.MyAccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teknoyhealthapp.Dashboard;
import com.example.teknoyhealthapp.R;
import com.example.teknoyhealthapp.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyAccountPage extends AppCompatActivity {

    //variables
    private TextView textFullName, textEmail2, textAddress, textPhone, toolbarTitle;
    private EditText autoComplete;
    private AppCompatButton updateButton;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_page);

        //hook up xml files
        textFullName = findViewById(R.id.textFullName);
        textEmail2 = findViewById(R.id.textEmail2);
        textAddress = findViewById(R.id.textAddress);
        textPhone = findViewById(R.id.textPhone);
        autoComplete = findViewById(R.id.textGender);
        updateButton = findViewById(R.id.updateButton);
        toolbarTitle = findViewById(R.id.toolbar_title);

        Intent intent = getIntent();

        String usernameCurrent = intent.getStringExtra("username");
        String nameCurrent = intent.getStringExtra("fullName");
        String emailCurrent = intent.getStringExtra("email");
        String phoneCurrent = intent.getStringExtra("phoneNumber");
        String passwordCurrent = intent.getStringExtra("password");
        String addressCurrent = intent.getStringExtra("address");
        String genderCurrent = intent.getStringExtra("classification");
        String updateCurrent = intent.getStringExtra("update");
        String barcodeCurrent = intent.getStringExtra("barcode");
        String symptomsCurrent = intent.getStringExtra("symptoms");
        String exposureCurrent = intent.getStringExtra("recentExposure");
        String tempCurrent = intent.getStringExtra("temperature");

        textFullName.setText(nameCurrent);
        textEmail2.setText(emailCurrent);
        textPhone.setText(phoneCurrent);
        textAddress.setText(addressCurrent);
        autoComplete.setText(genderCurrent);

        toolbarTitle.setText(usernameCurrent);

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

                User useR = new User(name, gender, address, email, passwordCurrent, number, "", tempCurrent, usernameCurrent, "", exposureCurrent, symptomsCurrent, "", barcodeCurrent, updateCurrent);

                reference.child(usernameCurrent).setValue(useR);

                Toast.makeText(getApplicationContext(), "User Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}