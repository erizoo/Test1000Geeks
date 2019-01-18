package com.a1000geeks.test.data.models;

import com.google.gson.annotations.SerializedName;

public class ResponseCars {

    @SerializedName("objectId")
    private String id;
    @SerializedName("price")
    private int price;
    @SerializedName("name")
    private String name;
    @SerializedName("name_manufacturer")
    private String nameManufacturer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameManufacturer() {
        return nameManufacturer;
    }

    public void setNameManufacturer(String nameManufacturer) {
        this.nameManufacturer = nameManufacturer;
    }
}
