package com.example.helloworldapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldapplication.ui.my_order.my_orderFragment;

import java.util.ArrayList;



public class MyAdapterForDecorationOrder extends RecyclerView.Adapter<MyAdapterForDecorationOrder.MyViewHolder2> {

    Context context;

    ArrayList<Decore_Set_Order> list;


    public MyAdapterForDecorationOrder(Context context, ArrayList<Decore_Set_Order> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_decoration_order,parent,false);
        return  new MyViewHolder2(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        Decore_Set_Order order = list.get(position);
        holder.tv_set_id1.setText(order.getSet_code());
        holder.tv_set_name1.setText(order.getSet_name());
        holder.tv_set_price1.setText(order.getSet_price());
        holder.tv_set_add1.setText(order.getSet_add());
        holder.tv_set_status1.setText(order.getStatus());
        holder.tv_set_date1.setText(order.getSet_date());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder{

        TextView tv_set_id1,tv_set_name1,tv_set_price1,tv_set_add1,tv_set_status1,tv_set_date1;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            tv_set_id1= itemView.findViewById(R.id.tv_set_id);
            tv_set_name1=itemView.findViewById(R.id.tv_set_name);
            tv_set_price1=itemView.findViewById(R.id.tv_order_price);
            tv_set_add1=itemView.findViewById(R.id.tv_event_address);
            tv_set_status1=itemView.findViewById(R.id.tv_order_status);
            tv_set_date1=itemView.findViewById(R.id.tv_date_event);

        }
    }

}
