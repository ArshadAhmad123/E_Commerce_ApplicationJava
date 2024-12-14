package com.example.bazaarwala.model;

public class MegaDiscount {
    String brandName;
    String brandCompany;
    String image1;
    String catagory;
    int id;
    public MegaDiscount(int image){
        this.id = image;

    }

    public MegaDiscount(String brandName, String brandCompany, String image, String catagory, int id) {
        this.brandName = brandName;
        this.brandCompany = brandCompany;
        this.image1 = image;
        this.catagory = catagory;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCompany() {
        return brandCompany;
    }

    public void setBrandCompany(String brandCompany) {
        this.brandCompany = brandCompany;
    }

    public String getImage() {
        return image1;
    }

    public void setImage(String image) {
        this.image1 = image;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }
}
