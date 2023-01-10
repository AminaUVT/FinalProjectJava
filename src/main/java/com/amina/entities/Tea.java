package com.amina.entities;

public class Tea  extends Entity {

    private int tea_id;
    private String brand;
    private String flavor;
    private float price;

    public int getTea_id() {
        return tea_id;
    }

    public void setTea_id(int tea_id) {
        this.tea_id = tea_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Book(" +
                "TEA_ID=%s," +
                "brand=%s," +
                "flavor=%s," +
                "price=%s" +
                ")", tea_id, brand, flavor, price);
    }
}
