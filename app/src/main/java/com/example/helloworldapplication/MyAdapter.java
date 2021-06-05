package com.example.helloworldapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;


    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.userid.setText(user.getUid());
        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
        holder.address.setText(user.getAdd());
        holder.email.setText(user.getEmail());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,phone,address,email,userid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userid=itemView.findViewById(R.id.tvUidAdmin);
            name = itemView.findViewById(R.id.tvNameAdmin);
            phone = itemView.findViewById(R.id.tvPhoneAdmin);
            address = itemView.findViewById(R.id.tvaddressAdmin);
            email=itemView.findViewById(R.id.tvEmailAdmin);


        }
    }

}