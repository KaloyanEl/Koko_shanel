package bg.smg;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication extends JFrame {

    private CardLayout cardLayout;
    private JPanel cards; // A panel that uses CardLayout
    private List<Product> productList;

    public MainApplication() {
        //data
        productList = loadProducts();
        //UI
        initializeUI();
    }

    private List<Product> loadProducts(){

        List<Product>  products = new ArrayList<>();

        File file = new File("products.txt");

        // Create a Scanner for the file
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Read data from a file
        while (input.hasNext()) {
            String name = input.next();
            String description = input.next();
            String image = input.next();
            String price = input.next();

            Product p = new Product(name, description, image, price);
            products.add(p);
        }

        // Close the file

        //StringBuilder
        input.close();
        return products;
    }

    private void initializeUI() {
        setTitle("KOKO SHOP");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        JPanel loginPanel = new LoginPanel(this, cardLayout, cards);
        JPanel productCreationPanel = new ProductCreationPanel(this, cardLayout, cards, productList);
        JPanel productDisplayPanel = new ProductDisplayPanel(cardLayout, cards, productList);

        cards.add(loginPanel, "LoginPanel");
        cards.add(productCreationPanel, "ProductCreationPanel");
        cards.add(productDisplayPanel, "ProductDisplayPanel");

        add(cards, BorderLayout.CENTER);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApplication app = new MainApplication();
            app.setVisible(true);
//            JFrame frame = new JFrame("Koko Shop");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(800, 600);
//            frame.add(new ProductDisplayPanel());
//            frame.setVisible(true);
        });


    }




}
