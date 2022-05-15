package presentation;

import dao.Serializator;
import model.User;
import role.Roles;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * The type Sign up page.
 */
public class SignUpPage {

    /**
     * The constant USERS_TXT.
     */
    public static final String USERS_TXT = "src/main/resources/users.txt";
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField rePasswordField;

    /**
     * Create the application.
     */
    public SignUpPage() {
        initialize();
    }

    /**
     * Launch the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUpPage window = new SignUpPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        usernameField = new JTextField();
        usernameField.setBounds(260, 124, 400, 40);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setColumns(10);
        passwordField.setBounds(260, 174, 400, 40);
        frame.getContentPane().add(passwordField);

        rePasswordField = new JPasswordField();
        rePasswordField.setEchoChar('*');
        rePasswordField.setColumns(10);
        rePasswordField.setBounds(260, 224, 400, 40);
        frame.getContentPane().add(rePasswordField);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        usernameLabel.setBounds(10, 122, 240, 40);
        frame.getContentPane().add(usernameLabel);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(10, 174, 240, 40);
        frame.getContentPane().add(lblPassword);

        JLabel lblReenterPassword = new JLabel("Re-enter Password");
        lblReenterPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblReenterPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblReenterPassword.setBounds(10, 224, 240, 40);
        frame.getContentPane().add(lblReenterPassword);

        Vector<Roles> v = new Vector<>();
        v.add(Roles.ADMINISTRATOR);
        v.add(Roles.EMPLOYEE);
        v.add(Roles.CLIENT);
        ComboBoxModel model = new DefaultComboBoxModel(v);
        JComboBox comboBox = new JComboBox(model);
        comboBox.setBounds(260, 274, 400, 40);
        frame.getContentPane().add(comboBox);

        JLabel roleLabel = new JLabel("Please Select Your Role");
        roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        roleLabel.setBounds(10, 288, 240, 13);
        frame.getContentPane().add(roleLabel);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(380, 486, 164, 40);
        frame.getContentPane().add(signUpButton);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        signUP(comboBox, signUpButton);
    }

    private void signUP(JComboBox comboBox, JButton signUpButton) {
        signUpButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();
            char[] rePassword = rePasswordField.getPassword();
            Roles role = (Roles) comboBox.getSelectedItem();
            if (!Arrays.equals(password, rePassword)) {
                System.out.println("Passwords must be matching");
                JOptionPane.showMessageDialog(frame, "Passwords must be matching");
            }
            User currentUser = new User(username, Arrays.toString(password), role);
            Serializator<User> userSerializator = new Serializator<>();
            List<User> usersList = userSerializator.read(USERS_TXT);
            for (User user : usersList) {
                if (currentUser.getUsername().equals(user.getUsername())) {
                    JOptionPane.showMessageDialog(frame, "User already exists");
                    throw new RuntimeException("User Already Exists");
                }
            }
            usersList.add(currentUser);
            userSerializator.write(USERS_TXT, usersList);
            frame.setVisible(false);
        });
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }
}
