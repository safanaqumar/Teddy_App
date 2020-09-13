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

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }
}
