package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworldapplication.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DecoreEventByIntent extends AppCompatActivity {
ImageView imageView;
TextView set_name_tx,price,dis_tx,code;

Button order_set_button;
EditText edit_add,edit_date;
FirebaseDatabase db;
FirebaseAuth auth;
ProgressBar bar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decore_event_by_intent);
        imageView = findViewById(R.id.imageinintent);
        set_name_tx=findViewById(R.id.set_name);
        price=findViewById(R.id.set_Price);
        code=findViewById(R.id.set_code);
        dis_tx=findViewById(R.id.set_dis);
        edit_add=findViewById(R.id.editText_for_add);
        edit_date=findViewById(R.id.edittext_for_date);
        bar=findViewById(R.id.progressBar_decore);



            bar.setVisibility(View.INVISIBLE);
        Bundle bundle = getIntent().getExtras();

            int resId = bundle.getInt("resId");
            imageView.setImageResource(resId);




            String set_name = getIntent().getExtras().getString("set_name");
            set_name_tx.setText(set_name);


            String set_price = getIntent().getExtras().getString("price");
            price.setText("Price Charge :\t" + set_price);

            String set_disp = getIntent().getExtras().getString("dis");
            dis_tx.setText("Discrpition :\n" + set_disp);

            String set_code=getIntent().getExtras().getString("set_code");
            code.setText("Set Code :\t"+ set_code);

            order_set_button=findViewById(R.id.button_for_order_decore_set);

       order_set_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               long time=System.currentTimeMillis();
               String string_for_event_date=edit_date.getText().toString();
               String string_for_event_add=edit_add.getText().toString();

               if(string_for_event_add.isEmpty())
               {
                   edit_add.setError("Enter Full Address");
                   edit_add.requestFocus();
               }
               else if(string_for_event_date.isEmpty())
               {
                   edit_date.setError("Enter Date Of Event");
                   edit_date.requestFocus();
               }
               else if(!string_for_event_add.isEmpty() && !string_for_event_date.isEmpty()) {

                   bar.setVisibility(View.VISIBLE);

                    String status="Submitted ...Waiting To Accpeted ";
                   Decore_Set_Order order = new Decore_Set_Order(set_code, set_name, string_for_event_date, set_price, string_for_event_add,status);


                   String path = "Decoration_Order_Of_UserId___" + FirebaseAuth.getInstance().getCurrentUser().getUid() ;
                   db.getInstance().getReference(path).child(String.valueOf(time)).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()) {
                               bar.setVisibility(View.GONE);
                               Toast.makeText(DecoreEventByIntent.this, "Order Placed Succesfully...", Toast.LENGTH_SHORT).show();


                           } else {
                               bar.setVisibility(View.GONE);
                               Toast.makeText(DecoreEventByIntent.this, "Error Occcured !!!!", Toast.LENGTH_SHORT).show();

                           }
                       }
                   });

               }
           }

       });

    }
}