package com.example.teknoyhealthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationForm extends AppCompatActivity {

    //variables
    private TextInputLayout fullName, phoneNumber, email, password, username;
    private AppCompatButton createButton;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration_form);

        //hook up all xml elements in the activity_registration_form.xml
        fullName = findViewById(R.id.regfullName);
        phoneNumber = findViewById(R.id.regPhoneNumber);
        email = findViewById(R.id.regEmail);
        password = findViewById(R.id.regPassword);
        username = findViewById(R.id.regUsername);
        createButton = findViewById(R.id.createButton);

        //register user in the firebase database
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //create instance and reference in the firebase
                rootNode = FirebaseDatabase.getInstance("https://teknoyhealthapp-default-rtdb.asia-southeast1.firebasedatabase.app");
                reference = rootNode.getReference("User");

                //validate
                if(!validateName() || !validateUsername() || !validateNumber() || !validatePassword()){
                    return;
                }

                //get all data from xml file
                String emaill = email.getEditText().getText().toString();
                String name = fullName.getEditText().getText().toString();
                String number = phoneNumber.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();
                String user = username.getEditText().getText().toString();

                //user object
                User useR = new User(name, "", "", emaill, pass, number, "", "", user, "", "", "", "", "","");

                //will register the user in the firebase database
                reference.child(user).setValue(useR);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(RegistrationForm.this, LoginForm.class));
                        finish();
                    }
                },250);

                Toast.makeText(getApplicationContext(), "Account Registered", Toast.LENGTH_SHORT).show();
            }
        });//end of the method
    }//end of the method

    //validate user name or email
    private boolean validateUsername(){
        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            email.setError("Input your Email");
            return false;
        }if(!val.matches(emailPattern)){
            email.setError("Invalid Email Address");
            return false;
        }else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }//end of the method

    //validate full name
    private boolean validateName(){
        String val = fullName.getEditText().getText().toString();

        if(val.isEmpty()){
            fullName.setError("Input your Full Name");
            return false;
        }else{
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }//end of the method

    //validate phone number
    private boolean validateNumber(){
        String val = phoneNumber.getEditText().getText().toString();

        if(val.isEmpty()) {
            phoneNumber.setError("Input your Phone Number");
            return false;
        }if(val.length() == 10){
            phoneNumber.setError("Invalid Phone Number");
            return false;
        }else{
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }//end of the method

    //validate password
    private boolean validatePassword(){
        String val = password.getEditText().getText().toString();

        if(val.isEmpty()){
            password.setError("Input your Password");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }//end of the method


    //open login form
    public void loginForm(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(RegistrationForm.this, LoginForm.class));
                finish();
            }
        },100);
    }//end of the method
}