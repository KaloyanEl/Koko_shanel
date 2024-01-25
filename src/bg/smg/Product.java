package bg.smg;

import javax.swing.*;

public class Product {
    private String name;
    private String description;

    private String price;
    private String image;


    public Product(String name, String description,String image, String price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    // Getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImageName(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}