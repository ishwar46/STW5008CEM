
import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {

    public static String username;
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

        lblUsername = new JLabel("Username");
        lblUsername.setBounds(70, 70, 80, 25);
        panel.add(lblUsername);

        txtUsername = new JTextField(20);
        txtUsername.setBounds(160, 70, 165, 25);
        panel.add(txtUsername);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(70, 110, 80, 25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField(20);
        txtPassword.setBounds(160, 110, 165, 25);
        panel.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(70, 160, 80, 25);
        btnLogin.addActionListener(this);
        panel.add(btnLogin);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(165, 160, 80, 25);
        btnCancel.addActionListener(this);
        panel.add(btnCancel);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText();
            char[] password = txtPassword.getPassword();
            if (username.equals("admin") && new String(password).equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login successful");
                Homepage homePage = new Homepage();

                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Try again!");
                txtUsername.setText("");
                txtPassword.setText("");
            }
        } else if (e.getSource() == btnCancel) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
