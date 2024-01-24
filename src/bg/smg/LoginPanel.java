package bg.smg;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    public LoginPanel(JFrame frame, CardLayout cardLayout, JPanel cards) {
        setLayout(null);
        setSize(800, 150);
        setBackground(Color.ORANGE);


        JPanel productPanel = new JPanel(new BorderLayout(5, 5));
        ImageIcon imgIcon = new ImageIcon("bg/smg/logo.png");
        JLabel imagePlaceholder = new JLabel(imgIcon, SwingConstants.CENTER);
        imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel title = new JLabel("Welcome in KOKO SHANEL");

        //  imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       ;





        JTextField emailField = new JTextField();

//        emailField.setPreferredSize(new Dimension(200, 30));
        JPasswordField passwordField = new JPasswordField( 10);
        JButton loginButton = new JButton("Log In");

        JLabel emailLbl = new JLabel("Email:");
        JLabel pswLbl = new JLabel("Password:");



        title.setBounds(140, 60, 760, 40);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(0, 0, 255));
        add(title);

        emailLbl.setBounds(170,150,100,40);
        emailLbl.setFont(new Font("Verdana", Font.BOLD, 17));


        emailField.setBounds(340,150,200,40);

        pswLbl.setBounds(170,200,100,40);
        pswLbl.setFont(new Font("Verdana", Font.BOLD, 17));

        passwordField.setBounds(340,200,200,40);

        loginButton.setBounds(320,280,150,60);


        loginButton.setBackground(new Color(0, 0, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBorder(new LineBorder(Color.BLUE));
        loginButton.setFocusPainted(false);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(true);


        add(emailLbl);
        add(emailField);
        add(pswLbl);
        add(passwordField);
        add(loginButton);
        //add(imagePlaceholder);


        emailField.setFont(new Font("Serif", Font.BOLD, 16)); // Set an interesting font and make it bold
        Border textFieldBorder = new LineBorder(Color.BLUE, 2, true); // Create a bold border
        emailField.setBorder(textFieldBorder);

        passwordField.setFont(new Font("Serif", Font.BOLD, 16)); // Set an interesting font and make it bold
        Border passwordFieldBorder = new LineBorder(Color.BLUE, 2, true); // Create a bold border
        passwordField.setBorder(passwordFieldBorder);
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

