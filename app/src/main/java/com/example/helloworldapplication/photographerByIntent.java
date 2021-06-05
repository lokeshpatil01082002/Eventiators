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

import java.security.cert.Extension;

import static android.widget.Toast.*;

public class photographerByIntent extends AppCompatActivity {

    String check_for_time = "1";
    DatePicker dp;
    Button b1, appoint_photographer;
    TextView tv, photographer_type, photographer_code, photographer_price, photographer_dis, photographer_includes;
    EditText event_address;
    ImageView imageView;
    ProgressBar pbar;
    String string_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_by_intent);

        pbar = findViewById(R.id.progressBar_photographer);
        pbar.setVisibility(View.INVISIBLE);
        appoint_photographer = findViewById(R.id.button_for_appoint_photographer);
        event_address = findViewById(R.id.photographer_order_event_address);
        imageView = findViewById(R.id.photographerimageinintent);


        photographer_type = findViewById(R.id.photographer_type);
        photographer_code = findViewById(R.id.photographer_code);
        photographer_price = findViewById(R.id.photographer_Price);
        photographer_dis = findViewById(R.id.photographer_dis);
        photographer_includes = findViewById(R.id.photographer_includes);
        Bundle bundle = getIntent().getExtras();

        int resId = bundle.getInt("resId");
        imageView.setImageResource(resId);


        String String_photographer_type = getIntent().getExtras().getString("photographer_type");
        photographer_type.setText(String_photographer_type);

        String String_photographer_code = getIntent().getExtras().getString("photographer_code");
        photographer_code.setText("Photographer Code :\t" + String_photographer_code);

        String String_photographer_dis = getIntent().getExtras().getString("dis");
        photographer_dis.setText("Photographer Discription :\n" + String_photographer_dis);


        String String_photographer_price = getIntent().getExtras().getString("price");
        photographer_price.setText("Photographer Price :\t" + String_photographer_price);


        String String_photographer_includes = getIntent().getExtras().getString("icludes");
        photographer_includes.setText("Package Includes :\t" + String_photographer_includes);




        dp = (DatePicker) findViewById(R.id.datePicker);
        b1 = (Button) findViewById(R.id.button_to_display_date);
        tv = (TextView) findViewById(R.id.textview);

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


        appoint_photographer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long time=System.currentTimeMillis();
               // String time_string=Long.toString(time);
                String string_for_event_address = event_address.getText().toString();
                String time_string = time+"";
                if(string_for_event_address.isEmpty()){
                    event_address.setError("Address Required !");
                    event_address.requestFocus();
                }
                else {




                    AlertDialog.Builder builder=new AlertDialog.Builder(photographerByIntent.this);
                    builder.setTitle("Order Confirmation");
                    builder.setMessage("Do you want to confirm your order ?");
                    builder.setCancelable(false);

                    builder.setPositiveButton("Yes,Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {





                            pbar.setVisibility(View.VISIBLE);

                            String status="Submitted ...Waiting To Accpeted ";
                            AppointPhotographerDatabase order = new AppointPhotographerDatabase(String_photographer_type,String_photographer_code,String_photographer_price,check_for_time,string_date,string_for_event_address,status,String_photographer_includes,time_string);
                            String path = "Photographer_Order_Of_UserId__" + FirebaseAuth.getInstance().getCurrentUser().getUid();
                            FirebaseDatabase.getInstance().getReference(path).child(String.valueOf(time_string)).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        pbar.setVisibility(View.GONE);
                                        //Toast.makeText(photographerByIntent.this, "Order Placed Succesfully...", Toast.LENGTH_SHORT).show();









                                        AlertDialog.Builder builder_new=new AlertDialog.Builder(photographerByIntent.this);
                                        builder_new.setTitle("Booking Execution");
                                        builder_new.setMessage("Your booking has been done successfully.\nYou can check your order status from my order section.\nOnce your order get accepted our team will contact you soon.\nThank you for booking !");
                                        builder.setCancelable(false);

                                        builder_new.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });

                                        builder_new.create().show();



                                    } else {
                                        pbar.setVisibility(View.GONE);
                                        Toast.makeText(photographerByIntent.this, "Error Occcured !!!!", Toast.LENGTH_SHORT).show();

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










                   /* pbar.setVisibility(View.VISIBLE);

                    String status="Submitted ...Waiting To Accpeted ";
                    AppointPhotographerDatabase order = new AppointPhotographerDatabase(String_photographer_type,String_photographer_code,String_photographer_price,check_for_time,string_date,string_for_event_address,status,String_photographer_includes,time_string);
                    String path = "Photographer_Order_Of_UserId__" + FirebaseAuth.getInstance().getCurrentUser().getUid();
                    FirebaseDatabase.getInstance().getReference(path).child(String.valueOf(time_string)).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                pbar.setVisibility(View.GONE);
                                Toast.makeText(photographerByIntent.this, "Order Placed Succesfully...", Toast.LENGTH_SHORT).show();


                            } else {
                                pbar.setVisibility(View.GONE);
                                Toast.makeText(photographerByIntent.this, "Error Occcured !!!!", Toast.LENGTH_SHORT).show();

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
                        check_for_time = "1";

                    break;
                case R.id.radio_2:
                    if (checked)
                        check_for_time = "2";
                    break;
                case R.id.radio_3:
                    if (checked)
                        check_for_time = "3";
                    break;

            }
        }


}
