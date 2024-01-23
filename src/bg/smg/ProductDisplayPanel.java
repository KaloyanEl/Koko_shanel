package bg.smg;


import javax.swing.*;
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
    JTextArea descriptionArea = new JTextArea("");
    JButton uploadbutton;
    JPanel gridPanel = new JPanel();

    public ProductDisplayPanel(CardLayout cardLayout, JPanel cards,List<Product> productList) {
        searchField = new JTextField();
        uploadbutton= new JButton("Upload Item");

        Path resourceDirectory = Paths.get("resources");
        absolutePath = resourceDirectory.toFile().getAbsolutePath();
        setLayout(null);
        setBackground(Color.WHITE);

        // Search bar at the top
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
        shopTitle.setBounds(20, 60, 760, 40);
        shopTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(shopTitle);


        descriptionArea.setBounds(20, 110, 760, 40);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        add(descriptionArea);


        gridPanel.setLayout(new GridLayout(2, 4, 10, 10));
        gridPanel.setBounds(20, 160, 760, 300);
        add(gridPanel);


        for (Product p : productList) {
            JPanel productPanel = new JPanel(new BorderLayout(5, 5));
            ImageIcon imgIcon = new ImageIcon(absolutePath+"/"+p.getImage());
            JLabel imagePlaceholder = new JLabel(imgIcon, SwingConstants.CENTER);
            imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel textPlaceholder = new JLabel("asddsadadadasdsadas", SwingConstants.CENTER);
            JButton buttonPlaceholder = new JButton("Details");

            productPanel.add(imagePlaceholder, BorderLayout.CENTER);
            productPanel.add(textPlaceholder, BorderLayout.SOUTH);
            gridPanel.add(productPanel);
        }
    }

}