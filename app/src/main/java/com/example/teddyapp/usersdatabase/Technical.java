package com.example.teddyapp.usersdatabase;

public class Technical{

    public  String Name,gender,email,contactnumber,position,address,username;
    public Technical()
    {

    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return gender;
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

    public Technical(String name, String gender, String email, String contactnumber, String position, String address, String username) {
        this.Name = name;
        this.gender = gender;
        this.email = email;
        this.contactnumber = contactnumber;
        this.position = position;
        this.address = address;
        this.username = username;
    }
}
