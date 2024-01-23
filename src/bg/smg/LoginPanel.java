package bg.smg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    public LoginPanel(JFrame frame, CardLayout cardLayout, JPanel cards) {
        setLayout(null);
        setSize(800, 150);
        setBackground(Color.red);

        JTextField emailField = new JTextField();

//        emailField.setPreferredSize(new Dimension(200, 30));
        JPasswordField passwordField = new JPasswordField( 10);
        JButton loginButton = new JButton("Log In");

        JLabel emailLbl = new JLabel("Email:");
        JLabel pswLbl = new JLabel("Password:");

        emailLbl.setBounds(170,50,80,40);
        emailLbl.setFont(new Font("Verdana", Font.BOLD, 15));


        emailField.setBounds(290,50,200,40);

        pswLbl.setBounds(170,100,100,40);
        pswLbl.setFont(new Font("Verdana", Font.BOLD, 15));

        passwordField.setBounds(290,100,200,40);

        loginButton.setBounds(320,180,100,40);

        add(emailLbl);
        add(emailField);
        add(pswLbl);
        add(passwordField);
        add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());
                User user = new User();
                if(username.equals("admin") && password.equals("123")) {
                    cardLayout.show(cards, "ProductDisplayPanel");
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Моля въведете правилните потребител и парола!",
                            "Грешен вход",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }


}
