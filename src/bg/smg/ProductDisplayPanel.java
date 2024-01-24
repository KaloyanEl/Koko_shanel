package bg.smg;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ProductDisplayPanel extends JPanel {
    private String absolutePath;
    private JTextField searchField;
    JLabel shopTitle = new JLabel("KOKO SHOP", SwingConstants.CENTER);
    private JLabel imageLabel;
    JTextArea descriptionArea = new JTextArea("");
    JButton uploadbutton;
    JPanel gridPanel = new JPanel();

    public ProductDisplayPanel(CardLayout cardLayout, JPanel cards,List<Product> productList) {



        searchField = new JTextField("Searching for .....");
        uploadbutton= new JButton("Upload Item");

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
        searchField.setBounds(20, 20, 500, 30);

        uploadbutton.setBounds(550, 20 , 200, 50);
        add(searchField);
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
        add(shopTitle);


        descriptionArea.setBounds(20, 110, 760, 40);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.ORANGE);
        add(descriptionArea);


        gridPanel.setLayout(new GridLayout(2, 4, 10, 10));
        gridPanel.setBounds(20, 200, 760, 300);
        add(gridPanel);


        for (Product p : productList) {
            JPanel productPanel = new JPanel(new BorderLayout(6, 6));
            ImageIcon imgIcon = new ImageIcon(absolutePath+"/"+p.getImage());
            JLabel imagePlaceholder = new JLabel(imgIcon, SwingConstants.CENTER);
            imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            JLabel textPlaceholder = new JLabel(p.getName(), SwingConstants.CENTER);
            JLabel textDescription = new JLabel(p.getDescription(), SwingConstants.CENTER);
            JButton buttonPlaceholder = new JButton("Details");

            productPanel.add(imagePlaceholder, BorderLayout.CENTER);
            productPanel.add(textPlaceholder, BorderLayout.SOUTH);


            gridPanel.add(productPanel);
        }
    }

}
