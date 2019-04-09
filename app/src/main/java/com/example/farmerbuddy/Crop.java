package com.example.farmerbuddy;

public class Crop {
    String cropid;
    String cropname;
    String quantities;
    String addres;
    String detail;
    String phone;

    public Crop(String cropid, String cropname, String quantities, String addres, String detail, String phone) {
        this.cropid = cropid;
        this.cropname = cropname;
        this.quantities = quantities;
        this.addres = addres;
        this.detail = detail;
        this.phone = phone;
    }

    public Crop(){

    }

    public String getCropid() {
        return cropid;
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
