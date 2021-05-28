package com.example.helloworldapplication;

public class User {
    public String name,email,phone,add,dist;
    public User() {
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdd() {
        return add;
    }

    public String getDist() {
        return dist;
    }

    public User(String name, String email, String phone, String add, String dist){
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.add=add;
        this.dist=dist;
    }

}
