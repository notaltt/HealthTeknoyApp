package com.example.teknoyhealthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.example.teknoyhealthapp.MyAccount.MyAccountPage;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginForm extends AppCompatActivity {

    //variables
    private AppCompatButton loginButton;
    private TextInputLayout username, password;
    private DatabaseReference reference;
    String nameDB, emailDB, phoneDB, usernameDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_form);

        //hook up text input layout xml elements to activity_login_form.xml
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        //login button in to the database
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validatePassword() || !validateUsername()){
                    return;
                }else{
                    clickLogin();
                }
            }
        });//end method of login button
    }//end method of onCreate

    private void clickLogin() {
        String enteredUsername = username.getEditText().getText().toString().trim();
        String enteredPassword = password.getEditText().getText().toString().trim();

        //create instance and reference in the firebase
        reference = FirebaseDatabase.getInstance("https://teknoyhealthapp-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("User");;

        Query checkUser = reference.orderByChild("username").equalTo(enteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordDB = snapshot.child(enteredUsername).child("password").getValue(String.class);
                    if(passwordDB.equals(enteredPassword)){
                        String namedDB = snapshot.child(enteredUsername).child("fullName").getValue(String.class);
                        String emailDB = snapshot.child(enteredUsername).child("email").getValue(String.class);
                        String phoneDB = snapshot.child(enteredUsername).child("phoneNumber").getValue(String.class);
                        String usernameDB = snapshot.child(enteredUsername).child("username").getValue(String.class);
                        String passwordDB1 = snapshot.child(enteredUsername).child("password").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);

                        intent.putExtra("fullName", namedDB);
                        intent.putExtra("email", emailDB);
                        intent.putExtra("phoneNumber", phoneDB);
                        intent.putExtra("username", usernameDB);
                        intent.putExtra("password", passwordDB1);

                        startActivity(intent);
                    }else{
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }else{
                    username.setError("Username doesn't exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

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

    //validate user name or email
    private boolean validateUsername(){
        String val = username.getEditText().getText().toString();

        if(val.isEmpty()) {
            username.setError("Input your Email");
            return false;
        }else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }//end of the method

    //open register form method
    public void registerForm(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoginForm.this, RegistrationForm.class));
                finish();
            }
        },100);
    }//end of the method
}