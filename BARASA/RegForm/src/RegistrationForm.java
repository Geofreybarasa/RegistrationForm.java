import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegistrationForm extends JFrame {

    private Container container;
    private JLabel title, idLabel, nameLabel, genderLabel, addressLabel, contactLabel, photoLabel;
    private JTextField idField, nameField, addressField, contactField, photoField;
    private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    private ButtonGroup genderGroup;
    private JButton registerButton, loginButton, exitButton, browseButton;
    private JTable table;
    private DefaultTableModel model;
    private List<User> users;

    public RegistrationForm() {
        initializeUI();
        users = new ArrayList<>();
    }

    private void initializeUI() {
        setTitle("Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(50, 30, 300, 30);
        container.add(title);

        idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        idLabel.setBounds(50, 80, 100, 20);
        container.add(idLabel);

        idField = new JTextField();
        idField.setFont(new Font("Arial", Font.PLAIN, 18));
        idField.setBounds(200, 80, 150, 20);
        container.add(idField);

        nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setBounds(50, 120, 100, 20);
        container.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        nameField.setBounds(200, 120, 300, 20);
        container.add(nameField);

        genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        genderLabel.setBounds(50, 160, 100, 20);
        container.add(genderLabel);

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
        maleRadioButton.setSelected(true);
        maleRadioButton.setBounds(200, 160, 80, 20);
        container.add(maleRadioButton);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
        femaleRadioButton.setBounds(290, 160, 100, 20);
        container.add(femaleRadioButton);

        otherRadioButton = new JRadioButton("Other");
        otherRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
        otherRadioButton.setBounds(400, 160, 100, 20);
        container.add(otherRadioButton);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        addressLabel.setBounds(50, 200, 100, 20);
        container.add(addressLabel);

        addressField = new JTextField();
        addressField.setFont(new Font("Arial", Font.PLAIN, 18));
        addressField.setBounds(200, 200, 300, 20);
        container.add(addressField);

        contactLabel = new JLabel("Contact");
        contactLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contactLabel.setBounds(50, 240, 100, 20);
        container.add(contactLabel);

        contactField = new JTextField();
        contactField.setFont(new Font("Arial", Font.PLAIN, 18));
        contactField.setBounds(200, 240, 150, 20);
        container.add(contactField);

        photoLabel = new JLabel("Passport Photo");
        photoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        photoLabel.setBounds(50, 280, 150, 20);
        container.add(photoLabel);

        photoField = new JTextField();
        photoField.setFont(new Font("Arial", Font.PLAIN, 18));
        photoField.setEditable(false); // Disable editing of photo field
        photoField.setBounds(200, 280, 250, 20);
        container.add(photoField);

        browseButton = new JButton("Browse");
        browseButton.setFont(new Font("Arial", Font.PLAIN, 14));
        browseButton.setBounds(460, 280, 90, 20);
        container.add(browseButton);

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        registerButton.setBounds(150, 320, 120, 30);
        container.add(registerButton);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBounds(300, 320, 100, 30);
        container.add(loginButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.setBounds(440, 320, 100, 30);
        container.add(exitButton);

        String[] columnNames = {"ID", "Name", "Gender", "Address", "Contact"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 370, 800, 150);
        container.add(scrollPane);

        table.setBackground(Color.LIGHT_GRAY);

        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(container);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    photoField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void registerUser() {
        String id = idField.getText();
        String name = nameField.getText();
        String gender = maleRadioButton.isSelected() ? "Male" : (femaleRadioButton.isSelected() ? "Female" : "Other");
        String address = addressField.getText();
        String contact = contactField.getText();
        String photoPath = photoField.getText();

        if (id.isEmpty() || name.isEmpty() || address.isEmpty() || contact.isEmpty() || photoPath.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields including passport photo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Store user locally
        User newUser = new User(id, name, gender, address, contact, photoPath);
        users.add(newUser);
        model.addRow(new Object[]{id, name, gender, address, contact});
        JOptionPane.showMessageDialog(null, "User registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        clearFields();
    }

    private void loginUser() {
        String id = idField.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter ID to login", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Search for user locally
        for (User user : users) {
            if (user.getId().equals(id)) {
                nameField.setText(user.getName());
                if ("Male".equals(user.getGender())) {
                    maleRadioButton.setSelected(true);
                } else if ("Female".equals(user.getGender())) {
                    femaleRadioButton.setSelected(true);
                } else {
                    otherRadioButton.setSelected(true);
                }
                addressField.setText(user.getAddress());
                contactField.setText(user.getContact());
                photoField.setText(user.getPhotoPath());
                JOptionPane.showMessageDialog(null, "User found. Details populated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "User with ID " + id + " not found", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        maleRadioButton.setSelected(true);
        addressField.setText("");
        contactField.setText("");
        photoField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegistrationForm();
            }
        });
    }

    // Inner class to represent a User
    private static class User {
        private String id;
        private String name;
        private String gender;
        private String address;
        private String contact;
        private String photoPath;

        public User(String id, String name, String gender, String address, String contact, String photoPath) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.address = address;
            this.contact = contact;
            this.photoPath = photoPath;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public String getAddress() {
            return address;
        }

        public String getContact() {
            return contact;
        }

        public String getPhotoPath() {
            return photoPath;
        }
    }
}

