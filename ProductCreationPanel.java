import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProductCreationPanel extends JPanel {


    JTextField productNameField = new JTextField();
    JTextField productDescriptionArea = new JTextField();
    JButton uploadButton = new JButton("Upload Image");
    JButton nextButton = new JButton("Next");
    JLabel nameProduct = new JLabel("Name of the product:");
    JLabel description = new JLabel("Description:");
    JLabel productImg = new JLabel();
    JLabel productImgName = new JLabel();

    public ProductCreationPanel(JFrame frame, CardLayout cardLayout, JPanel cards) {
        setLayout(null);
        setSize(800, 150);
        setBackground(Color.MAGENTA.darker());



        nameProduct.setBounds(20, 0, 200, 40);
        description.setBounds(20, 50, 120, 40);
        uploadButton.setBounds(100, 170, 200, 40);
        nextButton.setBounds(100, 220, 100, 40);
        productDescriptionArea.setBounds(200, 0, 400, 40);
        productNameField.setBounds(200, 50, 400, 100);

        nextButton.addActionListener(e -> cardLayout.show(cards, "ProductDisplayPanel"));


        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFileUpload();
            }
        });

        add(nameProduct);
        add(productNameField);
        add(description);
        add(productDescriptionArea);
        add(uploadButton);
        add(nextButton);
        add(productImgName);
        add(productImg);
    }


    private void handleFileUpload() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);

        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            productImgName.setText("File Selected: " + file.getName());
            try {
                Path resourceDirectory = Paths.get("src","resources");
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

