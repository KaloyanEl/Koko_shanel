import javax.swing.*;
import java.awt.*;

public class ProductCreationPanel extends JPanel {
    
    public ProductCreationPanel(JFrame frame, CardLayout cardLayout, JPanel cards) {
        setLayout(null);
        setSize(800, 150);

        JTextField productNameField = new JTextField();
        JTextField productDescriptionArea = new JTextField();
        JButton uploadButton = new JButton("Upload Image");
        JButton nextButton = new JButton("Next");
        JLabel nameProduct = new JLabel("Name of the product:");
        JLabel description = new JLabel("Description:");

        nameProduct.setBounds(20,0,200,40);
        description.setBounds(20,50,120,40);
        uploadButton.setBounds(100,170,200,40);
        nextButton.setBounds(100,220,100,40);
        productDescriptionArea.setBounds(200,0,400,40);
        productNameField.setBounds(200,50,400,100);


        add(nameProduct);
        add(productNameField);
        add(description);
        add(productDescriptionArea);
        add(uploadButton);
        add(nextButton);

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {

            }
        });

        nextButton.addActionListener(e -> {
            String ime = productNameField.getText();
            String opisanie = productDescriptionArea.getText();
            ImageIcon image = new ImageIcon();
            Product product = new Product(ime, opisanie, image);
            ProductDisplayPanel.addProduct(product);
            cardLayout.show(cards, "ProductDisplayPanel");
        });
    }
}

