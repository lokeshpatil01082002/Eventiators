package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppointChef extends AppCompatActivity {
    TextView no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_chef);
        no=findViewById(R.id.textView_chef);



        FirebaseDatabase database;
        DatabaseReference userRef;
        ProgressBar progressBar;



        String email_string= FirebaseAuth.getInstance().getCurrentUser().getEmail();


        database=FirebaseDatabase.getInstance();
        userRef=database.getReference("Users");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email_string)){
                        no.setText(ds.child("phone").getValue(String.class));
                        String uid_string=FirebaseAuth.getInstance().getCurrentUser().getUid();
                        Toast.makeText(AppointChef.this, "Uid is -" + uid_string, Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}