package com.example.farmerbuddy;

public class Crop {
    String id;
    String cropname;
    String quantities;
    String addres;
    String detail;
    String phone;

    public Crop(String id, String cropname, String quantities, String addres, String detail, String phone) {
        this.id = id;
        this.cropname = cropname;
        this.quantities = quantities;
        this.addres = addres;
        this.detail = detail;
        this.phone = phone;
    }

    public Crop(){

    }

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

    public String getDetail() {
        return detail;
    }

    public String getPhone() {
        return phone;
    }
}
