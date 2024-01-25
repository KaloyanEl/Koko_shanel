package bg.smg;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.awt.Dimension;


public class ProductDisplayPanel extends JPanel {
    private String absolutePath;
    private JTextField searchField;
    JLabel shopTitle = new JLabel("KOKO SHOP", SwingConstants.CENTER);
    private JLabel imageLabel;
    JTextArea descriptionArea = new JTextArea("");
    JButton uploadbutton;
    JPanel gridPanel = new JPanel();
    JButton searchbutton;

    public ProductDisplayPanel(CardLayout cardLayout, JPanel cards,List<Product> productList) {


        searchField = new JTextField();

        uploadbutton = new JButton("Upload Item");
        uploadbutton.setBackground(Color.ORANGE);
        uploadbutton.setForeground(Color.BLUE);
        uploadbutton.setFont(new Font("Arial", Font.BOLD, 20));
        uploadbutton.setBorder(new LineBorder(Color.BLUE,5));
        uploadbutton.setFocusPainted(false);
        uploadbutton.setOpaque(true);
        uploadbutton.setBorderPainted(true);

        searchbutton = new JButton("Search:");
        searchbutton.setBackground(Color.ORANGE);
        searchbutton.setForeground(Color.BLUE);
        searchbutton.setFont(new Font("Arial", Font.BOLD, 12));
        searchbutton.setBorder(new LineBorder(Color.BLUE,5));
        searchbutton.setFocusPainted(false);
        searchbutton.setOpaque(true);
        searchbutton.setBorderPainted(true);
        Path resourceDirectory = Paths.get("resources");
        absolutePath = resourceDirectory.toFile().getAbsolutePath();
        setLayout(null);
        setBackground(Color.ORANGE);

        shopTitle.setFont(new Font("Arial", Font.BOLD, 50));
        shopTitle.setBounds(20, 60, 760, 40);
        add(shopTitle);

        // Load and set the image below the shop title
        ImageIcon imageIcon = new ImageIcon("/bg/smg/logo.png"); // Load the image icon
        imageLabel = new JLabel(imageIcon); // Create a label with the image
        imageLabel.setBounds(20, 110, imageIcon.getIconWidth(), imageIcon.getIconHeight()); // Set bounds (x, y, width, height)
        add(imageLabel);


        // Search bar at the top
        searchField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        searchField.setBackground(Color.ORANGE);
        Border seacrchBorder = new LineBorder(Color.BLUE, 5, true); // Create a bold border
        searchField.setBorder(seacrchBorder);
        searchField.setBounds(100, 20, 400, 30);
        add(searchField);

        searchbutton.setBounds(10, 20, 80, 30);
        add(searchbutton);
        uploadbutton.setBounds(550, 20, 200, 50);
        add(uploadbutton);





        uploadbutton.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent e) {
                                               cardLayout.show(cards, "ProductCreationPanel");
                                           }
                                       }
        );

        // Shop title
        shopTitle.setBounds(20, 90, 760, 40);
        shopTitle.setFont(new Font("Arial", Font.ITALIC, 50));
        setBackground(new Color(255, 248, 220)); // A light cream background
        shopTitle.setFont(new Font("Georgia", Font.BOLD, 60)); // More elegant font
        shopTitle.setForeground(new Color(0, 51, 102)); // Deep blue color

        gridPanel.setLayout(new GridLayout(2, 4, 15, 15)); // Increase grid spacing
        gridPanel.setBounds(20, 160, 760, 340);
        gridPanel.setBackground(new Color(255, 248, 220));
        add(shopTitle);


        descriptionArea.setBounds(20, 110, 760, 40);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.ORANGE);
        add(descriptionArea);


       // gridPanel.setLayout(new GridLayout(2, 4, 10, 10));
        gridPanel.setBounds(20, 200, 760, 300);
       add(gridPanel);



        for (Product p : productList) {
            JPanel productPanel = createProductPanel(p);
            gridPanel.add(productPanel);
            //gridPanel.setVisible(true);
        }

    }
    public void loadProductsFromFile(List<Product> productList) {
        Set<String> existingProductNames = new HashSet<>();
        Set<String> existingProductDescriptions = new HashSet<>();
        for (Product product : productList) {
            existingProductNames.add(product.getName());
            existingProductNames.add(product.getDescription());
        }

        try (BufferedReader br = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productData = line.split("\\|");
                if (productData.length == 3 && !existingProductNames.contains(productData[0])) {
                    productList.add(new Product(productData[0], productData[1], productData[2],productData[3]));
                    existingProductNames.add(productData[0]);
                    existingProductDescriptions.add(productData[1]);
                    existingProductNames.add(productData[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Update the display with loaded products
    }
    private JPanel createProductPanel(Product p) {
        JPanel productPanel = new JPanel();
        //productPanel.setLayout(new BoxLayout(productPanel, 2));

        productPanel.setBorder(new LineBorder(new Color(0, 51, 102), 1)); // Stylish blue border
        productPanel.setBackground(new Color(240, 255, 255)); // Light sky blue background

        ImageIcon originalIcon = new ImageIcon(absolutePath + "/" + p.getImage());
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);



        JLabel price = new JLabel(p.getPrice() + "$");
        price.setFont(new Font("Arial", Font.BOLD, 20));
        price.setForeground(new Color(0, 0, 255));

        //imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);ad

        // Product Name
        JLabel nameLabel = new JLabel(p.getName());
        nameLabel.setFont(new Font("Georgia", Font.BOLD, 20)); // Larger, bold font for product name
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Product Description
        JTextArea descriptionArea = new JTextArea(p.getDescription());
        descriptionArea.setFont(new Font("Georgia", Font.PLAIN, 10));
        //descriptionArea.setAlignmentX(Component.BOTTOM_ALIGNMENT);
        //aamddescriptionArea.setBounds(20,400,40,50);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false); // Set text area background to be transparent


        JButton details = new JButton("Details");
        productPanel.add(imageLabel);
        productPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
        productPanel.add(nameLabel);
        productPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
        productPanel.add(descriptionArea);
        productPanel.add(details);
        productPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        productPanel.add(price);

        return productPanel;
    }

}
