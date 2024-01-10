import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame {

    private CardLayout cardLayout;
    private JPanel cards; // A panel that uses CardLayout

    public MainApplication() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("KOKO SHOP");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        JPanel loginPanel = new LoginPanel(this, cardLayout, cards);
        JPanel productCreationPanel = new ProductCreationPanel(this, cardLayout, cards);
        JPanel productDisplayPanel = new ProductDisplayPanel();

        cards.add(loginPanel, "LoginPanel");
        cards.add(productCreationPanel, "ProductCreationPanel");
        cards.add(productDisplayPanel, "ProductDisplayPanel");

        add(cards, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApplication app = new MainApplication();
            app.setVisible(true);
        });
    }
}
