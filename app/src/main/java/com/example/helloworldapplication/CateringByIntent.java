package com.example.helloworldapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CateringByIntent extends AppCompatActivity {

        TextView textview_catring_name,tv,tv_get_plate_price,menu_list;
        EditText edit_catering_address;
        Button b1,order_catering_button,generate_price_per_plate,get_menu_string;

        CheckBox drink1,drink2,drink3,drink4,drink5;
        CheckBox chat1,chat2,chat3,chat4,chat5;
        CheckBox dry1,dry2,dry3,dry4,dry5,dry6,dry7,dry8,dry9;
        CheckBox gravy1,gravy2,gravy3,gravy4,gravy5,gravy6;
        CheckBox roti1,roti2,roti3,roti4;
        CheckBox dessert1,dessert2,dessert3,dessert4,dessert5,dessert6;
        ProgressBar pbar;
        FirebaseDatabase db;
    double price_calculate=0;
    String order_plate="";
    int item_count=0;
    DatePicker dp;
    String string_date="";
    String guest_no="";
    String requiring="";
    String address="";
    String time_string_cal="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_by_intent);
        Bundle bundle = getIntent().getExtras();




        textview_catring_name=findViewById(R.id.catering_type);

        String catering_type = getIntent().getExtras().getString("catering_type");
        textview_catring_name.setText(catering_type);

        int extra_value = getIntent().getIntExtra("extra_price", 0);


        pbar=findViewById(R.id.progressBar_catering);
        pbar.setVisibility(View.INVISIBLE);

        drink1=findViewById(R.id.drink1);
        drink2=findViewById(R.id.drink2);
        drink3=findViewById(R.id.drink3);
        drink4=findViewById(R.id.drink4);
        drink5=findViewById(R.id.drink5);



        chat1=findViewById(R.id.chat1);
        chat2=findViewById(R.id.chat2);
        chat3=findViewById(R.id.chat3);
        chat4=findViewById(R.id.chat4);
        chat5=findViewById(R.id.chat5);





        dry1=findViewById(R.id.dry1);
        dry2=findViewById(R.id.dry2);
        dry3=findViewById(R.id.dry3);
        dry4=findViewById(R.id.dry4);
        dry5=findViewById(R.id.dry5);
        dry6=findViewById(R.id.dry6);
        dry7=findViewById(R.id.dry7);
        dry8=findViewById(R.id.dry8);
        dry9=findViewById(R.id.dry9);





        gravy1=findViewById(R.id.gravy1);
        gravy2=findViewById(R.id.gravy2);
        gravy3=findViewById(R.id.gravy3);
        gravy4=findViewById(R.id.gravy4);
        gravy5=findViewById(R.id.gravy5);
        gravy6=findViewById(R.id.gravy6);


        roti1=findViewById(R.id.roti1);
        roti2=findViewById(R.id.roti2);
        roti3=findViewById(R.id.roti3);
        roti4=findViewById(R.id.roti4);



        dessert1=findViewById(R.id.dessert1);
        dessert2=findViewById(R.id.dessert2);
        dessert3=findViewById(R.id.dessert3);
        dessert4=findViewById(R.id.dessert4);
        dessert5=findViewById(R.id.dessert5);
        dessert6=findViewById(R.id.dessert6);



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
                Toast.makeText(CateringByIntent.this, String.valueOf(extra_value), Toast.LENGTH_SHORT).show();
            }
        });



        edit_catering_address=findViewById(R.id.address_catering);

        tv_get_plate_price=findViewById(R.id.textview_for_plate_price);
        generate_price_per_plate=findViewById(R.id.getpriceplate);
        menu_list=findViewById(R.id.textview_for_menu_String);

        get_menu_string=findViewById(R.id.get_menu_string);
        get_menu_string.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_list.setText(order_plate);
            }
        });

        generate_price_per_plate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price_calculate=0;
                order_plate="";
                item_count=0;


                if(drink1.isChecked()){
                    price_calculate=price_calculate+25;
                    order_plate=order_plate+"* Iced Tea\n";
                    item_count=item_count+1;

                }
                if(drink2.isChecked()){
                    price_calculate=price_calculate+30;
                    order_plate=order_plate+"* Cold Coffee\n";
                    item_count=item_count+1;

                }
                if(drink3.isChecked()){
                    price_calculate=price_calculate+40;
                    order_plate=order_plate+"* Lassi\n";
                    item_count=item_count+1;

                }

                if(drink4.isChecked()){
                    price_calculate=price_calculate+40;
                    order_plate=order_plate+"* Thandai\n";
                    item_count=item_count+1;

                }
                if(drink5.isChecked()){
                    price_calculate=price_calculate+35;
                    order_plate=order_plate+"* Orange Juice\n";
                    item_count=item_count+1;

                }


                if(chat1.isChecked()){
                    price_calculate=price_calculate+30;
                    order_plate=order_plate+"* Pani Poori\n";
                    item_count=item_count+1;

                }
                if(chat2.isChecked()){
                    price_calculate=price_calculate+40;
                    order_plate=order_plate+"* Aloo Tikki\n";
                    item_count=item_count+1;

                }

                if(chat3.isChecked()){
                    price_calculate=price_calculate+40;
                    order_plate=order_plate+"* Cocktail Kachori\n";
                    item_count=item_count+1;

                }
                if(chat4.isChecked()){
                    price_calculate=price_calculate+40;
                    order_plate=order_plate+"* Bhel Puri\n";
                    item_count=item_count+1;

                }
                if(chat5.isChecked()){
                    price_calculate=price_calculate+40;
                    order_plate=order_plate+"* Samosa Chat\n";
                    item_count=item_count+1;

                }

                if(dry1.isChecked()){
                    price_calculate=price_calculate+150;
                    order_plate=order_plate+"* Panner Capsicum Masala\n";
                    item_count=item_count+1;

                }

                if(dry2.isChecked()){
                    price_calculate=price_calculate+100;
                    order_plate=order_plate+"* Stuffed Bhindi\n";
                    item_count=item_count+1;

                }
                if(dry3.isChecked()){
                    price_calculate=price_calculate+130;
                    order_plate=order_plate+"* Mixed Veg\n";
                    item_count=item_count+1;

                }
                if(dry4.isChecked()){
                    price_calculate=price_calculate+200;
                    order_plate=order_plate+"* Shahi Paneer \n";
                    item_count=item_count+1;

                }
                if(dry5.isChecked()){
                    price_calculate=price_calculate+170;
                    order_plate=order_plate+"* Kadai Panner  \n";
                    item_count=item_count+1;

                }
                if(dry6.isChecked()){
                    price_calculate=price_calculate+120;
                    order_plate=order_plate+"* Jeera Aloo \n";
                    item_count=item_count+1;

                }
                if(dry7.isChecked()){
                    price_calculate=price_calculate+150;
                    order_plate=order_plate+"* Matar Panner \n";
                    item_count=item_count+1;

                }
                if(dry8.isChecked()){
                    price_calculate=price_calculate+160;
                    order_plate=order_plate+"* Palak Panner \n";
                    item_count=item_count+1;

                }
                if(dry9.isChecked()){
                    price_calculate=price_calculate+150;
                    order_plate=order_plate+"* Stuffed Baingan \n";
                    item_count=item_count+1;

                }


                if(gravy1.isChecked()){
                    price_calculate=price_calculate+250;
                    order_plate=order_plate+"* Malai Kofta \n";
                    item_count=item_count+1;

                }
                if(gravy2.isChecked()){
                    price_calculate=price_calculate+250;
                    order_plate=order_plate+"* Paneer Lababdar \n";
                    item_count=item_count+1;

                }
                if(gravy3.isChecked()){
                    price_calculate=price_calculate+250;
                    order_plate=order_plate+"* Sarso Ka Saag \n";
                    item_count=item_count+1;

                }
                if(gravy4.isChecked()){
                    price_calculate=price_calculate+200;
                    order_plate=order_plate+"* Kashmiri Dum Aloo \n";
                    item_count=item_count+1;

                }
                if(gravy5.isChecked()){
                    price_calculate=price_calculate+250;
                    order_plate=order_plate+"* Amritsari Paneer Tikka \n";
                    item_count=item_count+1;

                }
                if(gravy6.isChecked()){
                    price_calculate=price_calculate+250;
                    order_plate=order_plate+"* Mixed Vegetables Makhani \n";
                    item_count=item_count+1;

                }
                if(roti1.isChecked()){
                    price_calculate=price_calculate+30;
                    order_plate=order_plate+"* Puri \n";
                    item_count=item_count+1;

                }
                if(roti2.isChecked()){
                    price_calculate=price_calculate+50;
                    order_plate=order_plate+"* Tawa Roti Or Chapati \n";
                    item_count=item_count+1;

                }
                if(roti3.isChecked()){
                    price_calculate=price_calculate+40;
                    order_plate=order_plate+"* Bajra Rotla \n";
                    item_count=item_count+1;

                }
                if(roti4.isChecked()){
                    price_calculate=price_calculate+40;
                    order_plate=order_plate+"* Paratha \n";
                    item_count=item_count+1;

                }

                if(dessert1.isChecked()){
                    price_calculate=price_calculate+50;
                    order_plate=order_plate+"* Gulab Jamun \n";
                    item_count=item_count+1;

                }
                if(dessert2.isChecked()){
                    price_calculate=price_calculate+40;
                    order_plate=order_plate+"* Jalebi \n";
                    item_count=item_count+1;

                }
                if(dessert3.isChecked()){
                    price_calculate=price_calculate+50;
                    order_plate=order_plate+"* Dry Fruits Kheer \n";
                    item_count=item_count+1;

                }
                if(dessert4.isChecked()){
                    price_calculate=price_calculate+50;
                    order_plate=order_plate+"* Rasgulla \n";
                    item_count=item_count+1;

                }
                if(dessert5.isChecked()){
                    price_calculate=price_calculate+40;
                    order_plate=order_plate+"* Ladoo \n";
                    item_count=item_count+1;

                }
                if(dessert6.isChecked()){
                    price_calculate=price_calculate+50;
                    order_plate=order_plate+"* Shrikhand \n";
                    item_count=item_count+1;

                }
                if(item_count>5 && item_count<10){
                    price_calculate=(price_calculate * 0.7) + extra_value;


                    time_string_cal = price_calculate+"";
                    tv_get_plate_price.setText(time_string_cal+" Rs./Plate");
                }
                if(item_count>=10 ){
                  price_calculate=(price_calculate * 0.6)+extra_value;
                     time_string_cal = price_calculate+"";
                    tv_get_plate_price.setText(time_string_cal + " Rs./Plate");
                }
                if(item_count<=5 ){
                 price_calculate=(price_calculate * 0.9)+extra_value;
                     time_string_cal = price_calculate+"Rs./Plate";
                    tv_get_plate_price.setText(time_string_cal + " Rs./Plate");
                }
            }
        });



        order_catering_button=findViewById(R.id.button_for_order_catering_service);
        order_catering_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    address=edit_catering_address.getText().toString();
                    if(address.isEmpty()){
                        edit_catering_address.setError("address Required");
                        edit_catering_address.requestFocus();
                    }
                    else if(string_date.isEmpty()){
                        Toast.makeText(CateringByIntent.this, "Select Date Of Your Event", Toast.LENGTH_SHORT).show();

                    }
                    else if(guest_no.isEmpty()){
                        Toast.makeText(CateringByIntent.this, "Select No Of Guests", Toast.LENGTH_SHORT).show();
                    }
                    else if(item_count<5){
                        Toast.makeText(CateringByIntent.this, "Select At Least 5 items For Menu", Toast.LENGTH_SHORT).show();
                    }
                    else{


                        long time=System.currentTimeMillis();
                        String time_string = time+"";



                        AlertDialog.Builder builder=new AlertDialog.Builder(CateringByIntent.this);
                        builder.setTitle("Order Confirmation");
                        builder.setMessage("Do you want to confirm your order ?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Yes,Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                pbar.setVisibility(View.VISIBLE);

                                String status="Submitted ...Waiting To Accpeted ";
                                String payment_status="Pending";
                                cateringDatabase order = new cateringDatabase(time_string,catering_type,order_plate,time_string_cal,guest_no,string_date,requiring,address,status,payment_status,FirebaseAuth.getInstance().getCurrentUser().getUid());
                                String path = "Catering_Order_Of_UserId__" + FirebaseAuth.getInstance().getCurrentUser().getUid();
                                db.getInstance().getReference(path).child(String.valueOf(time)).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            pbar.setVisibility(View.GONE);



                                            AlertDialog.Builder builder_new=new AlertDialog.Builder(CateringByIntent.this);
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
                                            pbar.setVisibility(View.GONE);
                                            Toast.makeText(CateringByIntent.this, "Error Occcured !!!!", Toast.LENGTH_SHORT).show();

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
            case R.id.radio_catering1:
                if (checked)
                    guest_no = "40 To 50 People";

                break;
            case R.id.radio_catering2:
                if (checked)
                    guest_no = "80 To 100 People";
                break;
            case R.id.radio_catering3:
                if (checked)
                    guest_no = "150 To 200 People";
                break;
            case R.id.radio_catering4:
                if (checked)
                    guest_no = "300 To 400 People";
                break;
            case R.id.radio_day_1:
                if (checked)
                    requiring="1 Day";
                break;
            case R.id.radio_day_2:
                if (checked)
                    requiring="2 Days";
                break;
            case R.id.radio_day_3:
                if (checked)
                    requiring="3 Days";
                break;
            case R.id.radio_day_4:
                if (checked)
                    requiring="4 Days";
                break;


        }
    }


}