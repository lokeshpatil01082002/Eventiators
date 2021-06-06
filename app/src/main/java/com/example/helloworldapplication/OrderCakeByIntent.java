package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class OrderCakeByIntent extends AppCompatActivity {
    ImageView imageView_cake_view;
    TextView cake_name_tx,cake_price_tx,cake_dis_tx,cake_code_tx,tv;

    Button order_cake_button,b1;
    DatePicker dp;
    EditText ed_cake_add,ed_cake_message;
    FirebaseDatabase db;
    FirebaseAuth auth;
    ProgressBar bar;
    String check_for_quant="0.5";
    String string_date="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cake_by_intent);

        imageView_cake_view=findViewById(R.id.imageinintent_cake);

        ed_cake_add=findViewById(R.id.editText_for_add_cake);

        ed_cake_message=findViewById(R.id.edittext_for_cake_message);


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



        dp = (DatePicker) findViewById(R.id.datePickercake);
        b1 = (Button) findViewById(R.id.button_to_display_date_cake);
        tv = (TextView) findViewById(R.id.textview);

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


        order_cake_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long time=System.currentTimeMillis();
                String time_string = time+"";

                String string_for_event_add=ed_cake_add.getText().toString();

                String string_for_message=ed_cake_message.getText().toString();
















                if(string_for_event_add.isEmpty())
                {
                    ed_cake_add.setError("Enter Full Address");
                    ed_cake_add.requestFocus();
                }
                else if(string_date.isEmpty()){
                    Toast.makeText(OrderCakeByIntent.this, "Select Date ", Toast.LENGTH_SHORT).show();
                }

                else if(!string_for_event_add.isEmpty() && !string_date.isEmpty()) {



                    AlertDialog.Builder builder=new AlertDialog.Builder(OrderCakeByIntent.this);
                    builder.setTitle("Order Confirmation");
                    builder.setMessage("Do you want to confirm your order ?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes,Comfirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {







                            bar.setVisibility(View.VISIBLE);

                            String status="Submitted ...Waiting To Accpeted ";
                            CakeOrderDb order = new CakeOrderDb(cake_code,cake_name,string_for_message,check_for_quant,string_date,cake_price,string_for_event_add,status,time_string);


                            String path = "Cake_Order_Of_UserId___" + FirebaseAuth.getInstance().getCurrentUser().getUid() ;
                            db.getInstance().getReference(path).child(String.valueOf(time)).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        bar.setVisibility(View.GONE);
                                        //Toast.makeText(OrderCakeByIntent.this, "Order Placed Succesfully...", Toast.LENGTH_SHORT).show();



                                        AlertDialog.Builder builder_new=new AlertDialog.Builder(OrderCakeByIntent.this);
                                        builder_new.setTitle("Order Execution");
                                        builder_new.setMessage("Your Order has been placed successfully.\nYou can check your order status from my order section.\nOnce your order get accepted ,order will be delievered to your address. \nYou can pay on deleivery\nThank you for ordering !");
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
                                        Toast.makeText(OrderCakeByIntent.this, "Error Occcured !!!!", Toast.LENGTH_SHORT).show();

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















                  /*  bar.setVisibility(View.VISIBLE);

                    String status="Submitted ...Waiting To Accpeted ";
                    CakeOrderDb order = new CakeOrderDb(cake_code,cake_name,string_for_message,check_for_quant,string_date,cake_price,string_for_event_add,status,time_string);


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

                   */

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
                    check_for_quant = "0.5";

                break;
            case R.id.radio_2:
                if (checked)
                    check_for_quant = "1";
                break;
            case R.id.radio_3:
                if (checked)
                    check_for_quant = "2";
                break;

        }
    }





}