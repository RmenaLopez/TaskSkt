package com.skt.finaltask.commonLibrary.model;

import java.io.Serializable;

public class Product implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private String brand;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
