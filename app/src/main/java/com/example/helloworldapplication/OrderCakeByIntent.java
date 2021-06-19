package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.util.Log;
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
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OrderCakeByIntent extends AppCompatActivity {
    ImageView imageView_cake_view;
    TextView cake_name_tx,cake_price_tx,cake_dis_tx,cake_code_tx,tv,result_tv;
    Button paynow;

    int multiplication_value=1;
    final int UPI_PAYMENT = 0;
    Button order_cake_button,b1;
    DatePicker dp;
    EditText ed_cake_add,ed_cake_message;
    FirebaseDatabase db;
    FirebaseAuth auth;
    ProgressBar bar;
    String check_for_quant="0.5";
    String string_date="";
    String payment_status="";


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
        paynow=findViewById(R.id.pay_now);
        paynow.setVisibility(View.INVISIBLE);

        result_tv=findViewById(R.id.result_tv);
        result_tv.setVisibility(View.INVISIBLE);


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
                else if(payment_status.isEmpty()){
                    Toast.makeText(OrderCakeByIntent.this, "Your Payment Is Not Done Yet ...You Can Choose Pay On Delievery Option If Any Issue", Toast.LENGTH_SHORT).show();
                }

                else if(!string_for_event_add.isEmpty() && !string_date.isEmpty()&&!payment_status.isEmpty()) {



                    AlertDialog.Builder builder=new AlertDialog.Builder(OrderCakeByIntent.this);
                    builder.setTitle("Order Confirmation");
                    builder.setMessage("Do you want to confirm your order ?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes,Comfirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {







                            bar.setVisibility(View.VISIBLE);

                            String status="Submitted ...Waiting To Accpeted ";
                            CakeOrderDb order = new CakeOrderDb(cake_code,cake_name,string_for_message,check_for_quant,string_date,cake_price,string_for_event_add,status,time_string,FirebaseAuth.getInstance().getUid(),payment_status);


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

















                }
            }

        });
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String transcId = df.format(c);

       paynow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


                String price_pay=cake_price.substring(0,3);

                int price_convert=Integer.parseInt(price_pay);

                double int_amount=price_convert * multiplication_value;
              //  int int_amount=(int)final_price-599;
              // Toast.makeText(OrderCakeByIntent.this,String.valueOf(int_value), Toast.LENGTH_SHORT).show();

               String amount=String.valueOf(int_amount);
               String note = "Cake Order Payment Of User -"+FirebaseAuth.getInstance().getCurrentUser().getUid();
               String name = FirebaseAuth.getInstance().getCurrentUser().getEmail();
               String upiId = "7057292479@ybl";
               makePayment(amount, upiId, name, note, transcId);



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
                multiplication_value=1;

                break;
            case R.id.radio_2:
                if (checked)
                    check_for_quant = "1";
                multiplication_value=2;
                break;
            case R.id.radio_3:
                if (checked)
                    check_for_quant = "2";
                multiplication_value=4;
                break;

            case R.id.pay_on_delievery:
                if (checked)
                  payment_status="On Delievery";
                paynow.setVisibility(View.INVISIBLE);
                break;

            case R.id.pay_upi:
                if (checked)
                paynow.setVisibility(View.VISIBLE);
                break;

        }
    }


    private void makePayment(String amount, String upi, String name, String desc, String transcationId) {
        // on below line we are calling an easy payment method and passing
        // all parameters to it such as upi id,name, description and others.
        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(this)
                // on below line we are adding upi id.
                .setPayeeVpa(upi)
                // on below line we are setting name to which we are making oayment.
                .setPayeeName(name)
                // on below line we are passing transaction id.
                .setTransactionId(transcationId)
                // on below line we are passing transaction ref id.
                .setTransactionRefId(transcationId)
                // on below line we are adding description to payment.
                .setDescription(desc)
                // on below line we are passing amount which is being paid.
                .setAmount(amount)
                // on below line we are calling a build method to build this ui.
                .build();
        // on below line we are calling a start
        // payment method to start a payment.
        easyUpiPayment.startPayment();
        // on below line we are calling a set payment
        // status listener method to call other payment methods.
        easyUpiPayment.setPaymentStatusListener(new PaymentStatusListener() {
            @Override
            public void onTransactionCompleted(TransactionDetails transactionDetails) {
                String transcDetails = transactionDetails.getStatus().toString() + "\n" + "Transaction Timing  : " + transactionDetails.getTransactionId();
                result_tv.setVisibility(View.VISIBLE);
                // on below line we are setting details to our text view.
                result_tv.setText(transcDetails);
            }

            @Override
            public void onTransactionSuccess() {
                Toast.makeText(OrderCakeByIntent.this, "Transaction successfully completed..", Toast.LENGTH_SHORT).show();
                payment_status="Paid";
            }

            @Override
            public void onTransactionSubmitted() {

            }

            @Override
            public void onTransactionFailed() {
                Toast.makeText(OrderCakeByIntent.this, "Failed to complete transaction", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransactionCancelled() {
                Toast.makeText(OrderCakeByIntent.this, "Transaction Cancelled By User", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAppNotFound() {
                Toast.makeText(OrderCakeByIntent.this, "No app found for making transaction..", Toast.LENGTH_SHORT).show();
            }
        });
    }




}