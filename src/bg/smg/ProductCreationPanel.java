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
    JTextField productDescription = new JTextField();
    JButton uploadButton = new JButton("Upload Image");
    JButton saveButton = new JButton("Save");
    JLabel nameProduct = new JLabel("Name of the product:");
    JLabel description = new JLabel("Description:");
    JLabel productImg = new JLabel();
    JLabel productImgName = new JLabel();

    public ProductCreationPanel(JFrame frame, CardLayout cardLayout, JPanel cards, List<Product> productList) {
        setLayout(null);
        setSize(800, 150);
        setBackground(Color.MAGENTA.darker());



        nameProduct.setBounds(20, 0, 200, 40);
        description.setBounds(20, 50, 120, 40);
        uploadButton.setBounds(100, 170, 200, 40);
        saveButton.setBounds(100, 220, 100, 40);
        productDescription.setBounds(200, 0, 400, 40);
        productNameField.setBounds(200, 50, 400, 100);

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
        add(productDescription);
        add(uploadButton);
        add(saveButton);
        add(productImgName);
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

