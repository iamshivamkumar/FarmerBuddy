package com.example.farmerbuddy;

public class ProfileData {

    String firstname;
    String lastname;
    String address;
    String phone;

    public ProfileData(String firstname, String lastname, String address, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
    }

    public ProfileData(){}

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
