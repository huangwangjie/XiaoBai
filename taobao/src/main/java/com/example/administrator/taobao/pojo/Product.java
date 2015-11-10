package com.example.administrator.taobao.pojo;

import java.io.Serializable;

/**
 * Created by Jason on 2015-10-24.
 */
public class Product implements Serializable{
    private String name;
    private String createDate;
    private Double price;
    private String image;

    public Product(String name, String image, Double price, String createDate) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.createDate = createDate;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", createDate='" + createDate + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
