package Views;


import Controller.UserController;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPage extends JFrame implements ActionListener {
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblEmail;
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JTextField txtEmail;
    private JButton btnSignUp;
    private JButton btnBack;

    public SignUpPage() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        lblUsername = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(lblUsername, constraints);

        txtUserName = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(txtUserName, constraints);

        lblPassword = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(lblPassword, constraints);

        txtPassword = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(txtPassword, constraints);

        lblEmail = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(lblEmail, constraints);

        txtEmail = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(txtEmail, constraints);

        btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(btnSignUp, constraints);

        btnBack = new JButton("Go Back");
        btnBack.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(btnBack, constraints);

        setTitle("SignUp Page");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnSignUp) {
            String username = txtUserName.getText();
            String password = new String(txtPassword.getPassword());
            String email = txtEmail.getText();

            if (!validateUsername(username)) {
                JOptionPane.showMessageDialog(this, "Invalid username. Please enter a valid username.");
            } else if (!validatePassword(password)) {
                JOptionPane.showMessageDialog(this, "Invalid password. Please enter a valid password.");
            } else if (!validateEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid email. Please enter a valid email address.");
            } else {
                User user = new User(username, password, email);
                UserController userController = new UserController();
                int result = userController.registerCustomerPreparedStatement(user);
                if (result == 1) {
                    JOptionPane.showMessageDialog(this, "Sign up successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Sign up failed. Please try again later.");
                }
            }
        } else if (event.getSource() == btnBack) {
            LoginPage loginScreen = new LoginPage();
            loginScreen.setVisible(true);
            dispose();
        }
    }


    private boolean validateUsername(String username) {
// validation logic for username
        return !username.isEmpty();
    }

    private boolean validatePassword(String password) {
// validation logic for password
        return password.length() >= 8;
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }




}