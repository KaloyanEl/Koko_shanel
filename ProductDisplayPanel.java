

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductDisplayPanel extends JPanel {

    JTextField searchField = new JTextField();
    JLabel shopTitle = new JLabel("KOKO SHOP", SwingConstants.CENTER);
    JTextArea descriptionArea = new JTextArea("Lorem ipsum dolor sit amet et delectus...");
    JPanel gridPanel = new JPanel();

    public ProductDisplayPanel() {
        setLayout(null);
        setBackground(Color.WHITE);

        // Search bar at the top
        searchField.setBounds(20, 20, 760, 30);
        add(searchField);

        // Shop title
        shopTitle.setBounds(20, 60, 760, 40);
        shopTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(shopTitle);

        // Description beneath the title
        descriptionArea.setBounds(20, 110, 760, 40);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        add(descriptionArea);

        
        gridPanel.setLayout(new GridLayout(2, 4, 10, 10));
        gridPanel.setBounds(20, 160, 760, 300);
        add(gridPanel);

        
        for (int i = 0; i < 8; i++) {
            JPanel productPanel = new JPanel(new BorderLayout(5, 5));
            JLabel imagePlaceholder = new JLabel(" ", SwingConstants.CENTER);
            imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel textPlaceholder = new JLabel("Lorem ipsum", SwingConstants.CENTER);
            JButton buttonPlaceholder = new JButton("Details");

            productPanel.add(imagePlaceholder, BorderLayout.CENTER);
            productPanel.add(textPlaceholder, BorderLayout.SOUTH);
            gridPanel.add(productPanel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Koko Shop");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new ProductDisplayPanel());
            frame.setVisible(true);
        });
    }
}
