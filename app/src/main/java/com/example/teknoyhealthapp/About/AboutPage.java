package com.example.teknoyhealthapp.About;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teknoyhealthapp.Dashboard;
import com.example.teknoyhealthapp.R;

public class AboutPage extends AppCompatActivity {

    private TextView toolbar_title, information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        toolbar_title = findViewById(R.id.toolbar_title);
        information = findViewById(R.id.information);

        toolbar_title.setText("About Health Teknoy");
        information.setText("Health Teknoy is an application that will be in service to make filling out health declaration form much easier and quicker by generating a QR code. After the code is scanned, information will be directed to the database that is monitored by an admin. Moreover, users’ data are being assured to be protected and safe. Lastly, this application includes the following functions: my calendar tab, my QR code, home, my account, and update which will be discuss elaborately in this document." +
                "\n\nThe application is accessible to everyone but solely used when entering CIT-U premises. The target users of this app are CIT-U students, faculty and staff. Users need a mobile device with internet connectivity to run and use the application. Once users have already inputted their data, they can quickly generate a code. On the other hand, admin is the only privileged to access the database using a desktop to conveniently monitor the statistics being covered by the application.");
    }
}