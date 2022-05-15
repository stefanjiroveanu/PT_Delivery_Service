package presentation;

import bll.DeliveryService;
import dao.Serializator;
import model.User;
import role.Roles;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The type Login page.
 */
public class LoginPage {
    /**
     * The constant USERS_TXT.
     */
    public static final String USERS_TXT = "src/main/resources/users.txt";
    /**
     * The Admin interface.
     */
    AdminInterface adminInterface = new AdminInterface();
    /**
     * The Client interface.
     */
    ClientInterface clientInterface = new ClientInterface();
    /**
     * The Employee interface.
     */
    EmployeeInterface employeeInterface = new EmployeeInterface();
    /**
     * The Sign up page.
     */
    SignUpPage signUpPage = new SignUpPage();
    private JFrame frame;
    private JTextField username;
    private JPasswordField password;

    /**
     * Create the application.
     */
    public LoginPage() {
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
                    LoginPage window = new LoginPage();
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
        adminInterface.getFrame().setVisible(false);
        signUpPage.getFrame().setVisible(false);
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        username = new JTextField();
        username.setBounds(227, 141, 413, 30);
        frame.getContentPane().add(username);
        username.setColumns(10);

        password = new JPasswordField();
        password.setColumns(10);
        password.setEchoChar('*');
        password.setBounds(227, 228, 413, 30);
        frame.getContentPane().add(password);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        usernameLabel.setBounds(92, 140, 124, 30);
        frame.getContentPane().add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordLabel.setBounds(93, 228, 124, 30);
        frame.getContentPane().add(passwordLabel);

        JButton signinButton = new JButton("Sign In");
        signinButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

        signinButton.setBounds(351, 284, 160, 58);
        frame.getContentPane().add(signinButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        signUpButton.setBounds(317, 437, 226, 50);
        signUpButton.addActionListener(e -> {
            SignUpPage.main(null);
        });
        signinButton.addActionListener(e -> {
            String user = username.getText();
            char[] pass = password.getPassword();
            Serializator<User> userSerializator = new Serializator<>();
            List<User> readUsers = userSerializator.read(USERS_TXT);
            AtomicBoolean foundOne = new AtomicBoolean(false);
            readUsers.forEach(listUser -> {
                if (listUser.getUsername().equals(user) && listUser.getPassword().equals(Arrays.toString(pass))) {
                    Roles role = listUser.getRole();
                    foundOne.set(true);
                    if (role.equals(Roles.ADMINISTRATOR)) {
                        System.out.println("good");
                        adminInterface.getFrame().setVisible(true);
                    } else if (role.equals(Roles.CLIENT)) {
                        System.out.println("good Client");
                        clientInterface.getFrame().setVisible(true);
                        DeliveryService.currentUser(listUser);
                    } else {
                        System.out.println("good employee");
                        employeeInterface.getFrame().setVisible(true);
                    }
                }

            });
            if(!foundOne.get()) JOptionPane.showMessageDialog(frame, "Incorrect username or password");
        });
        frame.getContentPane().add(signUpButton);

        JLabel accountLabel = new JLabel("You don't have an account ? Sign Up Now !");
        accountLabel.setHorizontalAlignment(SwingConstants.LEFT);
        accountLabel.setBounds(311, 414, 280, 13);
        frame.getContentPane().add(accountLabel);
    }

    /**
     * Gets admin interface.
     *
     * @return the admin interface
     */
    public AdminInterface getAdminInterface() {
        return adminInterface;
    }

    /**
     * Gets sign up page.
     *
     * @return the sign up page
     */
    public SignUpPage getSignUpPage() {
        return signUpPage;
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets client interface.
     *
     * @return the client interface
     */
    public ClientInterface getClientInterface() {
        return clientInterface;
    }

    /**
     * Gets employee interface.
     *
     * @return the employee interface
     */
    public EmployeeInterface getEmployeeInterface() {
        return employeeInterface;
    }
}
