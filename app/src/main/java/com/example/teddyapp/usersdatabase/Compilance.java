package com.example.teddyapp.usersdatabase;

public class Compilance {

    public  String Name,password,gender,email,contactnumber,position,address,username;

    public Compilance()
    {

    }


    public Compilance(String name, String password,String gender, String email, String contactnumber, String position, String address, String username) {
        this.Name = name;
        this.password=password;
        this.gender = gender;
        this.email = email;
        this.contactnumber = contactnumber;
        this.position = position;
        this.address = address;
        this.username = username;
    }

    public String getName() {
        return Name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public String getPosition() {
        return position;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }
}
