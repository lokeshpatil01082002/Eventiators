package com.example.helloworldapplication;

public class Decore_Set_Order {

    public String set_code,set_name,set_date,set_price,set_add,status,order_id;

    public Decore_Set_Order() {
    }

    public Decore_Set_Order(String set_code, String set_name, String set_date, String set_price, String set_add, String status, String order_id) {
        this.set_code = set_code;
        this.set_name = set_name;
        this.set_date = set_date;
        this.set_price = set_price;
        this.set_add = set_add;
        this.status = status;
        this.order_id = order_id;
    }

    public String getSet_code() {
        return set_code;
    }

    public String getSet_name() {
        return set_name;
    }

    public String getSet_date() {
        return set_date;
    }

    public String getSet_price() {
        return set_price;
    }

    public String getSet_add() {
        return set_add;
    }

    public String getStatus() {
        return status;
    }

    public String getOrder_id() {
        return order_id;
    }
}
