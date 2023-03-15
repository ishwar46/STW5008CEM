package Views;

import Controller.UserController;
import Model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame implements ActionListener {
    public static int USER_ID = 0;
    private JLabel lblUsername, lblPassword, lblTitle;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnCancel, btnSignUp;
    private JPanel panel;

    public LoginPage() {
        setTitle("Login Page");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(56, 97, 143));

        lblTitle = new JLabel("Login to Continue");
        lblTitle.setBounds(130, 30, 300, 40);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        panel.add(lblTitle);

        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(70, 120, 80, 25);
        lblUsername.setForeground(Color.WHITE);
        panel.add(lblUsername);

        txtUsername = new JTextField(20);
        txtUsername.setBounds(160, 120, 200, 25);
        panel.add(txtUsername);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(70, 160, 80, 25);
        lblPassword.setForeground(Color.WHITE);
        panel.add(lblPassword);

        txtPassword = new JPasswordField(20);
        txtPassword.setBounds(160, 160, 200, 25);
        panel.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(70, 220, 100, 40);
        btnLogin.addActionListener(this);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(29, 185, 84));
        panel.add(btnLogin);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(220, 220, 100, 40);
        btnCancel.addActionListener(this);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBackground(new Color(221, 75, 57));
        panel.add(btnCancel);

        add(panel);

        btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(370, 220, 100, 40);
        btnSignUp.addActionListener(this);
        btnSignUp.setFont(new Font("Arial", Font.BOLD, 14));
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setBackground(new Color(45, 156, 219));
        panel.add(btnSignUp);

        ImageIcon icon = new ImageIcon("schedule.png");
        JLabel lblLogo = new JLabel(icon);
        lblLogo.setBounds(400, 300, 80, 80);
        panel.add(lblLogo);

        setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnLogin) {
            // Validate the username and password fields
            if (txtUsername.getText().trim().isEmpty() || txtPassword.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Username or password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            UserController userController = new UserController();
            User user = userController.loginCustomerPreparedStatement(txtUsername.getText(), new String(txtPassword.getPassword()));
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Add code here to move to the next screen or display the desired information
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username or password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (event.getSource() == btnSignUp) {
            SignUpPage signUpPage = new SignUpPage();
            signUpPage.setVisible(true);
            dispose();

        } else if (event.getSource() == btnCancel) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
}