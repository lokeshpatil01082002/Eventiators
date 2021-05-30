package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class OrderCakeByIntent extends AppCompatActivity {
    ImageView imageView_cake_view;
    TextView cake_name_tx,cake_price_tx,cake_dis_tx,cake_code_tx;

    Button order_cake_button;
    EditText ed_cake_add,ed_cake_date,ed_cake_message,ed_cake_quantity;
    FirebaseDatabase db;
    FirebaseAuth auth;
    ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cake_by_intent);

        imageView_cake_view=findViewById(R.id.imageinintent_cake);

        ed_cake_add=findViewById(R.id.editText_for_add_cake);
        ed_cake_date=findViewById(R.id.edittext_for_date_cake);
        ed_cake_message=findViewById(R.id.edittext_for_cake_message);
        ed_cake_quantity=findViewById(R.id.edittext_for_quantity_cake);

        cake_name_tx=findViewById(R.id.cake_name);
        cake_price_tx=findViewById(R.id.cake_Price);
        cake_dis_tx=findViewById(R.id.cake_dis);
        cake_code_tx=findViewById(R.id.cake_id);


        order_cake_button =findViewById(R.id.button_for_order_cake);

        bar=findViewById(R.id.progressBar_cake);
        bar.setVisibility(View.INVISIBLE);

        Bundle bundle = getIntent().getExtras();

        int resId = bundle.getInt("resId");
        imageView_cake_view.setImageResource(resId);


        String cake_name = getIntent().getExtras().getString("cake_name");
        cake_name_tx.setText(cake_name);


        String cake_price = getIntent().getExtras().getString("cake_price");
        cake_price_tx.setText("Price Charge :\t" + cake_price);


        String cake_code=getIntent().getExtras().getString("cake_code");
        cake_code_tx.setText("Set Code :\t"+ cake_code);

        String cake_disp=getIntent().getExtras().getString("cake_dis");
        cake_dis_tx.setText("Description Box  :\n"+ cake_disp);



        order_cake_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long time=System.currentTimeMillis();
                String string_for_event_date=ed_cake_date.getText().toString();
                String string_for_event_add=ed_cake_add.getText().toString();
                String string_for_quantity=ed_cake_quantity.getText().toString();
                String string_for_message=ed_cake_message.getText().toString();

                if(string_for_event_add.isEmpty())
                {
                    ed_cake_add.setError("Enter Full Address");
                    ed_cake_add.requestFocus();
                }
                else if(string_for_event_date.isEmpty())
                {
                    ed_cake_date.setError("Enter Date Of Event");
                    ed_cake_date.requestFocus();
                }
                else if(!string_for_event_add.isEmpty() && !string_for_event_date.isEmpty()) {

                    bar.setVisibility(View.VISIBLE);

                    String status="Submitted ...Waiting To Accpeted ";
                    CakeOrderDb order = new CakeOrderDb(cake_code,cake_name,string_for_message,string_for_quantity,string_for_event_date,cake_price,string_for_event_add,status);


                    String path = "Cake_Order_Of_UserId___" + FirebaseAuth.getInstance().getCurrentUser().getUid() ;
                    db.getInstance().getReference(path).child(String.valueOf(time)).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                bar.setVisibility(View.GONE);
                                Toast.makeText(OrderCakeByIntent.this, "Order Placed Succesfully...", Toast.LENGTH_SHORT).show();


                            } else {
                                bar.setVisibility(View.GONE);
                                Toast.makeText(OrderCakeByIntent.this, "Error Occcured !!!!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }

        });



    }
}