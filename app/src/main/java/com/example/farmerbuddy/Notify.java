package com.example.farmerbuddy;

public class Notify {
    String id;
    String username;
    String cropname;
    String quantities;
    String addres;
    String phone;

    public Notify(String id,String username ,String cropname, String quantities, String addres,String phone) {
        this.id = id;
        this.username = username;
        this.cropname = cropname;
        this.quantities = quantities;
        this.addres = addres;
        this.phone = phone;
    }

    public Notify(){}

    public String getUsername() { return username; }

    public String getId() {
        return id;
    }

    public String getCropname() {
        return cropname;
    }

    public String getQuantities() {
        return quantities;
    }

    public String getAddres() {
        return addres;
    }

    public String getPhone() {
        return phone;
    }
}
