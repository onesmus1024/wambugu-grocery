package com.example.wambugugrocery;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;
    private boolean isAvailable;
    private String description;
    private String imageUrl;
    private BigDecimal price;

    public Product(Long id, String name, boolean isAvailable, String description, String imageUrl, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }



}
