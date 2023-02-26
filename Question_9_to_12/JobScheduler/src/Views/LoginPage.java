package Views;

import Controller.UserController;
import Model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPage extends JFrame implements ActionListener {
    public static int USER_ID=0;
    private JLabel lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnCancel;
    private JPanel panel;

    public LoginPage() {
        setTitle("Login Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));

        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(70, 70, 80, 25);
        panel.add(lblUsername);

        txtUsername = new JTextField(20);
        txtUsername.setBounds(160, 70, 165, 25);
        panel.add(txtUsername);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(70, 110, 80, 25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField(20);
        txtPassword.setBounds(160, 110, 165, 25);
        panel.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(70, 160, 80, 25);
        btnLogin.addActionListener(this);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(15, 204, 68));
        panel.add(btnLogin);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(170, 160, 90, 25);
        btnCancel.addActionListener(this);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBackground(new Color(255, 0, 0));
        panel.add(btnCancel);

        add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnLogin) {
// Validate the username and password fields
            if (txtUsername.getText().trim().isEmpty() || txtPassword.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Username or password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }        UserController userController = new UserController();
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
        } else if (event.getSource() == btnCancel) {
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPage screen = new LoginPage();
            screen.setVisible(true);
        });
    }
}
