package bg.smg;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.border.Border;

public class ProductCreationPanel extends JPanel {


    JTextField productNameField = new JTextField();
    JTextArea productDescription = new JTextArea();
    JButton uploadButton = new JButton("Upload Image");
    JButton saveButton = new JButton("Save");
    JButton nextButton = new JButton("Next"); // Added based on the wireframe
    JLabel nameProduct = new JLabel("Product Name:");
    JLabel description = new JLabel("Description:");
    JLabel priceLabel = new JLabel("Price:"); // Added based on the wireframe
    JTextField productPriceField = new JTextField(); // Added based on the wireframe
    JLabel productImg = new JLabel();
    JLabel productImgName = new JLabel();
    public ProductCreationPanel(JFrame frame, CardLayout cardLayout, JPanel cards, List<Product> productList) {
        setLayout(null);
        setSize(800, 150);
        setBackground(Color.ORANGE);


        // Labels

        nameProduct.setFont(new Font("Arial", Font.BOLD, 25));
        nameProduct.setForeground(new Color(0, 0, 255));
        nameProduct.setBounds(270, 110, 200, 30);


        productNameField.setBounds(270, 140, 300, 40);
      //  productNameField.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        Border SeacrchBorder = new LineBorder(Color.BLUE, 5, true); // Create a bold border
        productNameField.setBorder(SeacrchBorder);



        description.setFont(new Font("Arial", Font.BOLD, 25));
        description.setForeground(new Color(0, 0, 255));
        description.setBounds(270, 180, 200, 30);


        productDescription.setBounds(270, 210, 400, 90);
      //  productDescription.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        Border seacrchBorder = new LineBorder(Color.BLUE, 5, true); // Create a bold border
        productDescription.setBorder(seacrchBorder);
        productDescription.setLineWrap(true);
        productDescription.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(productDescription); // To add scroll bars to the text area

        scrollPane.setBounds(270, 210, 280, 90);



        priceLabel.setFont(new Font("Arial", Font.BOLD, 25));
        priceLabel.setForeground(new Color(0, 0, 255));
        priceLabel.setBounds(270, 310, 200, 30);

        productPriceField.setBounds(270, 340, 280, 30);
        Border SeacrchBorder1 = new LineBorder(Color.BLUE, 5, true); // Create a bold border
        productPriceField.setBorder(SeacrchBorder1);
        uploadButton.setBounds(270, 380, 280, 30);
        uploadButton.setBackground(new Color(0, 0, 255));
        uploadButton.setForeground(Color.WHITE);
        uploadButton.setFont(new Font("Arial", Font.BOLD, 20));
        uploadButton.setBorder(new LineBorder(Color.BLUE));
        //uploadButton.setBorder(new RoundedBorder(10));
        uploadButton.setFocusPainted(false);
        uploadButton.setOpaque(true);
        uploadButton.setBorderPainted(true);


        saveButton.setBounds(270, 420, 135, 30);
        nextButton.setBounds(415, 420, 135, 30);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    save(productList);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });


        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFileUpload();
            }
        });

        add(nameProduct);
        add(productNameField);
        add(description);
        add(scrollPane); // Add the scroll pane instead of the text area directly
        add(priceLabel);
        add(productPriceField);
        add(uploadButton);
        add(saveButton);
        add(nextButton);
       // add(productImg);
    }

    public void save(List<Product> productList) throws FileNotFoundException {
       Product p = new Product(productNameField.getText(),productDescription.getText(), productImgName.getText());

        //add to list
        productList.add(p);
        //add to file
        File file = new File("products.txt");

        // Create a file
        PrintWriter output = new PrintWriter(file);

        // Write formatted output to the file
        output.print(productNameField.getText());
        output.print(" ");
        output.print(productDescription.getText());
        output.print(" ");
        output.print(productImgName.getText());
        // Close the file
        output.close();
    }

    private void handleFileUpload() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);

        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            productImgName.setText("File Selected: " + file.getName());
            try {
                Path resourceDirectory = Paths.get("src", "resources");
                String absolutePath = resourceDirectory.toFile().getAbsolutePath();

                FileChannel src = new FileInputStream(file).getChannel();
                FileChannel dest = new FileOutputStream(new File(absolutePath+"/"+file.getName())).getChannel();
                dest.transferFrom(src, 0, src.size());
                src.close();
                dest.close();
                ImageIcon imgIcon = new ImageIcon(absolutePath+"/"+file.getName());
                productImg.setIcon(imgIcon);
            } catch (Exception ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        }else{
            productImgName.setText("Open command canceled");

        }
    }
}






