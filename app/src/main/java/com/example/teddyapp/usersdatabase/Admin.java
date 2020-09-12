package com.example.teddyapp.usersdatabase;

public class Admin {

    public  String name,gender,email,contactnumber,position,address,userid;

    public Admin()
    {

    }

    public Admin(String name, String gender, String email, String contactnumber, String position, String address, String userid) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.contactnumber = contactnumber;
        this.position = position;
        this.address = address;
        this.userid = userid;
    }
}
