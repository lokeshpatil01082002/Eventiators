package com.example.helloworldapplication.ui.my_order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.helloworldapplication.Decore_Set_Order;
import com.example.helloworldapplication.MyAdapter;
import com.example.helloworldapplication.MyAdapterForDecorationOrder;
import com.example.helloworldapplication.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class my_orderFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView;
        DatabaseReference database;
        MyAdapterForDecorationOrder myAdapter;
        ArrayList<Decore_Set_Order> list;


        View root = inflater.inflate(R.layout.fragment_my_order, container, false);
        recyclerView = root.findViewById(R.id.decoration_order);





        database = FirebaseDatabase.getInstance().getReference("Decoration_Order_Of_UserId___" + FirebaseAuth.getInstance().getCurrentUser().getUid());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        list = new ArrayList<>();
        myAdapter = new MyAdapterForDecorationOrder(getContext(),list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Decore_Set_Order order = dataSnapshot.getValue(Decore_Set_Order.class);
                    list.add(order);


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }
}