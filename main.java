import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Application {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> new GUI().setVisible(true));
    }
}


class Book {
    private String title;
    private String author;
    private ImageIcon coverImage;

    public Book(String title, String author, ImageIcon coverImage) {
        this.title = title;
        this.author = author;
        this.coverImage = coverImage;
    }

    
}


class GUI extends JFrame {
    private List<Book> books = new ArrayList<>();  
    private JTextField titleField, authorField;
    private JLabel imageLabel;
    private ImageIcon bookImage;  

    public GUI() {
        createUI();  
    }

    private void createUI() {
        
        setTitle("Item Manager");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        
        add(new JLabel("Username:"));
        add(new JTextField(20));
        add(new JLabel("Password:"));
        add(new JPasswordField(20));
        add(new JButton("Login"));  

        
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        JButton uploadButton = new JButton("Upload Image of Item");
        JButton saveButton = new JButton("Save Item");
        imageLabel = new JLabel();

        add(new JLabel("Heading:"));
        add(titleField);
        add(new JLabel("Description:"));
        add(authorField);
        add(uploadButton);
        add(imageLabel);
        add(saveButton);

      
        uploadButton.addActionListener(e -> uploadImage());
        
        saveButton.addActionListener(e -> saveBook());
    }

    private void uploadImage() {
       
    }

    private void saveBook() {
        Book book = new Book(titleField.getText(), authorField.getText(), bookImage);
        books.add(book);  
        JOptionPane.showMessageDialog(this, "Item Saved!");
    }
}
