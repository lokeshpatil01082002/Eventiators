package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminNewHome extends AppCompatActivity {

        Button view_user_list,view_details_with_orders,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_home);

        view_user_list=findViewById(R.id.view_user_list);
        view_details_with_orders=findViewById(R.id.view_user_list_with_details_and_orders);
        logout=findViewById(R.id.admin_log_out);



        view_user_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminNewHome.this,admin_home.class);
                startActivity(i);
            }
        });

        view_details_with_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminNewHome.this,admin_home.class);
                startActivity(i);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminNewHome.this,LogIn.class);
                Toast.makeText(AdminNewHome.this, "Admin Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();
            }
        });
    }
}