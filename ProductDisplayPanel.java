
import java.awt.GridLayout;

import javax.swing.*;

public class ProductDisplayPanel extends JPanel {

    public ProductDisplayPanel() {
        setLayout(new GridLayout(0, 1));
    }

    public static void addProduct(Product product) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.add(new JLabel(product.getName()));
        productPanel.add(new JLabel(product.getDescription()));
        // In a real application, we should scale the image accordingly
       
    }
}