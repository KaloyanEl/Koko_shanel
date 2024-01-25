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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.border.Border;

public class ProductCreationPanel extends JPanel {


    JTextField productNameField = new JTextField();
    JTextField productDescription = new JTextField();
    JButton uploadButton = new JButton("Upload Image");
    JButton saveButton = new JButton("Save");
    JButton PreviousButton = new JButton("Previous"); // Added based on the wireframe
    JLabel nameProduct = new JLabel("Product Name:");
    JLabel description = new JLabel("Description:");
    JLabel priceLabel = new JLabel("Price:"); // Added based on the wireframe
    JTextField productPriceField = new JTextField(); // Added based on the wireframe
    JLabel productImg = new JLabel();
    JLabel productImgName = new JLabel();

    JLabel Heading = new JLabel("Create Product");
    public ProductCreationPanel(JFrame frame, CardLayout cardLayout, JPanel cards, List<Product> productList) {
        setLayout(null);
        setSize(800, 150);
        setBackground(Color.ORANGE);



        Heading.setBounds(250, 10, 600, 100);
        Heading.setFont(new Font("Arial", Font.BOLD, 60));
        // Labels

        nameProduct.setFont(new Font("Arial", Font.BOLD, 25));
        nameProduct.setForeground(new Color(0, 0, 255));
        nameProduct.setBounds(270, 110, 200, 30);


        productNameField.setBounds(270, 140, 400, 40);
      //  productNameField.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        Border SeacrchBorder = new LineBorder(Color.BLUE, 5, true); // Create a bold border
        productNameField.setBorder(SeacrchBorder);



        description.setFont(new Font("Arial", Font.BOLD, 25));
        description.setForeground(new Color(0, 0, 255));
        description.setBounds(270, 180, 200, 30);


        productDescription.setBounds(270, 210, 400, 90);
        Border DescriptionFiledBorder= new LineBorder(Color.BLUE, 5, true); // Create a bold border
        productDescription.setBorder(DescriptionFiledBorder);






        priceLabel.setFont(new Font("Arial", Font.BOLD, 25));
        priceLabel.setForeground(new Color(0, 0, 255));
        priceLabel.setBounds(270, 310, 200, 30);

        productPriceField.setBounds(270, 340, 100, 30);
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



        PreviousButton.setBounds(415, 480, 135, 30);
        PreviousButton.setBackground(Color.BLUE);
        PreviousButton.setForeground(Color.WHITE);
        PreviousButton.setFont(new Font("Arial", Font.BOLD, 14));



        saveButton.setBounds(270, 480, 135, 30);
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    save(productList);
                } catch (IOException ex) { // Catch IOException instead of FileNotFoundException
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
        PreviousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cards.getComponent(1) instanceof ProductDisplayPanel) {
                    ((ProductDisplayPanel) cards.getComponent(1)).loadProductsFromFile(productList);
                }
                cardLayout.show(cards, "ProductDisplayPanel");
            }
        });

        add(nameProduct);
        add(productNameField);
        add(productDescription);
        add(description);
        add(priceLabel);
        add(productPriceField);
        add(uploadButton);
        add(saveButton);
        add(PreviousButton);
        add(Heading);
       // add(productImg);
    }

    // In ProductCreationPanel class
    public void save(List<Product> productList) throws IOException {
        Product p = new Product(productNameField.getText(), productDescription.getText(), productImgName.getText(),productPriceField.getText());
        productList.add(p); // Add to in-memory list

        // Append to file
        try (FileWriter fw = new FileWriter("products.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(productNameField.getText() + " " + productDescription.getText() + " " + productImgName.getText() + " " + productPriceField.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleFileUpload() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);

        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            productImgName.setText(file.getName());
            try {
                Path resourceDirectory = Paths.get("resources");
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






