package com.example.teddyapp.usersdatabase;

public class Compilance {

    public  String Name,gender,email,contactnumber,position,address,username;

    public Compilance()
    {

    }

    public Compilance(String name, String gender, String email, String contactnumber, String position, String address, String username) {
        Name = name;
        this.gender = gender;
        this.email = email;
        this.contactnumber = contactnumber;
        this.position = position;
        this.address = address;
        this.username = username;
    }
}
