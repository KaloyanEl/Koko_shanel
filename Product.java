import javax.swing.*;

public class Product {
    private String name;
    private String description;
    private ImageIcon image;

    public Product(String name, String description, ImageIcon image) {
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

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
}