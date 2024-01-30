package bg.smg;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JPanel {

    public SignUp(JFrame frame, CardLayout cardLayout, JPanel cards) {
        setLayout(null);
        setSize(800, 150);
        setBackground(Color.ORANGE);


        JPanel productPanel = new JPanel(new BorderLayout(5, 5));
        ImageIcon imgIcon = new ImageIcon("bg/smg/logo.png");
        JLabel imagePlaceholder = new JLabel(imgIcon, SwingConstants.CENTER);
        imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel title = new JLabel("Create Account");

        //  imagePlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ;


        JTextField emailField = new JTextField();

//        emailField.setPreferredSize(new Dimension(200, 30));
        JPasswordField passwordField = new JPasswordField(10);
        JButton prevButton = new JButton("Back");
        JButton SignUpButton = new JButton("Save");

        JLabel emailLbl = new JLabel("Email:");
        JLabel pswLbl = new JLabel("Password:");


        title.setBounds(140, 60, 760, 40);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(0, 0, 255));
        add(title);

        emailLbl.setBounds(170, 150, 100, 40);
        emailLbl.setFont(new Font("Verdana", Font.BOLD, 17));


        emailField.setBounds(340, 150, 200, 40);

        pswLbl.setBounds(170, 200, 100, 40);
        pswLbl.setFont(new Font("Verdana", Font.BOLD, 17));

        passwordField.setBounds(340, 200, 200, 40);

        prevButton.setBounds(320, 280, 150, 60);


        prevButton.setBackground(new Color(0, 0, 255));
        prevButton.setForeground(Color.WHITE);
        prevButton.setFont(new Font("Arial", Font.BOLD, 20));
        prevButton.setBorder(new LineBorder(Color.BLUE));
        prevButton.setFocusPainted(false);
        prevButton.setOpaque(true);
        prevButton.setBorderPainted(true);


        SignUpButton.setBounds(320,350,150,60);


        SignUpButton.setBackground(new Color(0, 0, 255));
        SignUpButton.setForeground(Color.WHITE);
        SignUpButton.setFont(new Font("Arial", Font.BOLD, 20));
        SignUpButton.setBorder(new LineBorder(Color.BLUE));
        SignUpButton.setFocusPainted(false);
        SignUpButton.setOpaque(true);
        SignUpButton.setBorderPainted(true);


        add(emailLbl);
        add(emailField);
        add(pswLbl);
        add(passwordField);
        add(prevButton);
        add(SignUpButton);

        //add(imagePlaceholder);


        emailField.setFont(new Font("Serif", Font.BOLD, 16)); // Set an interesting font and make it bold
        Border textFieldBorder = new LineBorder(Color.BLUE, 2, true); // Create a bold border
        emailField.setBorder(textFieldBorder);

        passwordField.setFont(new Font("Serif", Font.BOLD, 16)); // Set an interesting font and make it bold
        Border passwordFieldBorder = new LineBorder(Color.BLUE, 2, true); // Create a bold border
        passwordField.setBorder(passwordFieldBorder);
        prevButton.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             cardLayout.show(cards, "LoginPanel");
                                         }
                                     }
        );


    }
}

