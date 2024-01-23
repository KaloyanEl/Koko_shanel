package bg.smg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
        setBackground(Color.MAGENTA.darker());


        // Labels
        nameProduct.setBounds(270, 10, 200, 30);
        productNameField.setBounds(270, 40, 280, 30);
        description.setBounds(270, 80, 200, 30);


        productDescription.setBounds(270, 110, 280, 90);
        productDescription.setLineWrap(true);
        productDescription.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(productDescription); // To add scroll bars to the text area

        scrollPane.setBounds(270, 110, 280, 90);

        priceLabel.setBounds(270, 210, 200, 30);
        productPriceField.setBounds(270, 240, 280, 30);

        uploadButton.setBounds(270, 280, 280, 30);
        saveButton.setBounds(270, 320, 135, 30);
        nextButton.setBounds(415, 320, 135, 30);

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
        output.print(productDescription.getText());
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


