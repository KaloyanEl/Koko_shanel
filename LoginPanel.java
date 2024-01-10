import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel(JFrame frame, CardLayout cardLayout, JPanel cards) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Log In");

        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(Box.createVerticalStrut(10));
        add(loginButton);

        loginButton.addActionListener(e -> cardLayout.show(cards, "ProductCreationPanel"));
    }
}