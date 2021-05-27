package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class admin_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        Toast.makeText(admin_home.this, "Incorrect Email Or Password !!", Toast.LENGTH_SHORT).show();

    }
}