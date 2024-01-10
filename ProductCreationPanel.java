import javax.swing.*;
import java.awt.*;

public class ProductCreationPanel extends JPanel {
    
    public ProductCreationPanel(JFrame frame, CardLayout cardLayout, JPanel cards) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField productNameField = new JTextField(20);
        JTextArea productDescriptionArea = new JTextArea(5, 20);
        JButton uploadButton = new JButton("Upload Image");
        JButton nextButton = new JButton("Next");

        add(new JLabel("Name of the product:"));
        add(productNameField);
        add(new JLabel("Description:"));
        add(new JScrollPane(productDescriptionArea));
        add(Box.createVerticalStrut(10));
        add(uploadButton);
        add(nextButton);

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                // Handle file selection
            }
        });

        nextButton.addActionListener(e -> {
            String name = productNameField.getText();
            String description = productDescriptionArea.getText();
            // Here you can handle image upload and create a new product object
            // For now, we'll just use a placeholder for the image
            ImageIcon image = new ImageIcon(); // Placeholder
            Product product = new Product(name, description, image);
            ProductDisplayPanel.addProduct(product);
            cardLayout.show(cards, "ProductDisplayPanel");
        });
    }
}
