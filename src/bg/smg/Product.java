package bg.smg;

import javax.swing.*;

public class Product {
    private String name;
    private String description;
    private String image;

    public Product(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
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
}