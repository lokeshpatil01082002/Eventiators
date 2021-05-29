package com.example.helloworldapplication.ui.my_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.helloworldapplication.Decore_Set_Order;
import com.example.helloworldapplication.MyAdapter;
import com.example.helloworldapplication.MyAdapterForDecorationOrder;
import com.example.helloworldapplication.NewReg;
import com.example.helloworldapplication.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.helloworldapplication.View_Decoration_orders;
import com.example.helloworldapplication.View_Gift_orders;
import com.example.helloworldapplication.View_cake_orders;
import com.example.helloworldapplication.View_catering_service_orders;
import com.example.helloworldapplication.View_photographer_bookings;
import com.example.helloworldapplication.nav_act_home;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class my_orderFragment extends Fragment {


        Button decore,photogrpaher,cake,gift,catering;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_my_order, container, false);

        decore=root.findViewById(R.id.button_to_display_decoration_order);
        photogrpaher=root.findViewById(R.id.button_to_display_photographer_bookings);
        cake=root.findViewById(R.id.button_to_display_cake_order);
        gift=root.findViewById(R.id.button_to_display_gift_order);
        catering=root.findViewById(R.id.button_to_display_catering_service_orders);



        decore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), View_Decoration_orders.class);
                startActivity(i);
            }
        });

        photogrpaher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), View_photographer_bookings.class);
                startActivity(i);
            }
        });


        catering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), View_catering_service_orders.class);
                startActivity(i);
            }
        });


        cake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), View_cake_orders.class);
                startActivity(i);
            }
        });


        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), View_Gift_orders.class);
                startActivity(i);
            }
        });

        return root;
    }
}