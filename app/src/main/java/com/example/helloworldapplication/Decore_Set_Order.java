package com.example.helloworldapplication;

public class Decore_Set_Order {

    public String set_code,set_name,set_date,set_price,set_add,status;

    public Decore_Set_Order() {
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

    public Decore_Set_Order(String set_code, String set_name, String set_date, String set_price, String set_add, String status) {
        this.set_code = set_code;
        this.set_name = set_name;
        this.set_date = set_date;
        this.set_price = set_price;
        this.set_add = set_add;
        this.status = status;
    }
}
