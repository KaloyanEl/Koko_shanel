
import java.awt.*;

import javax.swing.*;

public class ProductDisplayPanel extends JPanel {
    JButton prevButton = new JButton("Previous ");
    JPanel productPanel = new JPanel();




    public ProductDisplayPanel(JFrame frame, CardLayout cardLayout, JPanel cards) {


        setLayout(null);
        setSize(2000, 1000);
        setBackground(Color.MAGENTA.darker());



        prevButton.setBounds(100,180,100,40);

        prevButton.addActionListener(e -> cardLayout.show(cards, "ProductCreationPanel"));


        add(prevButton);
       
    }
}
