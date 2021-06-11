package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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

import java.util.Calendar;

public class DecoreEventByIntent extends AppCompatActivity {
    ImageView imageView;
    String check_for_time = "1";
    TextView set_name_tx,price,dis_tx,code,tv;

    Button order_set_button,b1;
    EditText edit_add;
    FirebaseDatabase db;
    FirebaseAuth auth;
    ProgressBar bar;
    DatePicker dp;
    String string_date="" ;



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





        dp = (DatePicker) findViewById(R.id.datePicker);
        b1 = (Button) findViewById(R.id.button_to_display_date);
        tv = (TextView) findViewById(R.id.textview_for_date_text);
        Calendar today = Calendar.getInstance();
        long now = today.getTimeInMillis();
        dp.setMinDate(now);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer();
                sb.append(dp.getDayOfMonth() + "/");
                sb.append((dp.getMonth() + 1) + "/");
                sb.append(dp.getYear());

                tv.setText(sb.toString());

                string_date=sb.toString();
            }
        });














        order_set_button=findViewById(R.id.button_for_order_decore_set);

        order_set_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long time=System.currentTimeMillis();
                String time_string = time+"";

                String string_for_event_add=edit_add.getText().toString();

                if(string_for_event_add.isEmpty())
                {
                    edit_add.setError("Enter Full Address");
                    edit_add.requestFocus();
                }
                else if(string_date.isEmpty())
                {
                    Toast.makeText(DecoreEventByIntent.this, "Select Date OF Event", Toast.LENGTH_SHORT).show();
                }
                else if(!string_for_event_add.isEmpty() && !string_date.isEmpty()) {


                    AlertDialog.Builder builder=new AlertDialog.Builder(DecoreEventByIntent.this);
                    builder.setTitle("Order Confirmation");
                    builder.setMessage("Do you want to confirm your order ?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes,Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            bar.setVisibility(View.VISIBLE);

                            String status="Submitted ...Waiting To Accpeted ";
                            String payment_status="Pending";
                            Decore_Set_Order order = new Decore_Set_Order(set_code, set_name, string_date, set_price, string_for_event_add,status,time_string,check_for_time,payment_status,FirebaseAuth.getInstance().getCurrentUser().getUid());
                            String path = "Decoration_Order_Of_UserId__" + FirebaseAuth.getInstance().getCurrentUser().getUid();
                            db.getInstance().getReference(path).child(String.valueOf(time)).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        bar.setVisibility(View.GONE);
                                     //   Toast.makeText(DecoreEventByIntent.this, "Order Placed Succesfully...", Toast.LENGTH_SHORT).show();




                                        AlertDialog.Builder builder_new=new AlertDialog.Builder(DecoreEventByIntent.this);
                                        builder_new.setTitle("Order Execution");
                                        builder_new.setMessage("Your Order has been placed successfully.\nYou can check your order status from my order section.\nOnce your order get accepted our team will contact you soon.\n.You can pay at time of service delievery\nThank you for ordering !");
                                        builder.setCancelable(false);

                                        builder_new.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();

                                                finish();


                                            }
                                        });

                                        builder_new.create().show();











                                    } else {
                                        bar.setVisibility(View.GONE);
                                        Toast.makeText(DecoreEventByIntent.this, "Error Occcured !!!!", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });




                        }
                    });
                    builder.setNegativeButton("Recheck", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    builder.create().show();












                }
            }

        });

    }





    public void onRadioButtonClicked (View  view) {


        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_1:
                if (checked)
                    check_for_time = "1 Day";

                break;
            case R.id.radio_2:
                if (checked)
                    check_for_time = "2 Days";
                break;
            case R.id.radio_3:
                if (checked)
                    check_for_time = "3 Days";
                break;

        }
    }
}