import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame implements ActionListener {

    private JLabel lblWelcome;
    private JButton btnLogout;

    private JButton btnTasks;
    private JPanel panel;



    public Homepage() {
        setTitle("Homepage");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);

        lblWelcome = new JLabel("Welcome to the homepage");
        lblWelcome.setBounds(70, 70, 200, 25);
        panel.add(lblWelcome);


        btnTasks = new JButton("Assign Tasks");
        btnTasks.setBounds(70, 110, 150, 25);
        btnTasks.addActionListener(this);
        panel.add(btnTasks);



        btnLogout = new JButton("Logout");
        btnLogout.setBounds(200, 150, 80, 25);
        btnLogout.addActionListener(this);
        panel.add(btnLogout);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogout) {
            JOptionPane.showMessageDialog(this, "Logout successful");
            dispose();
            LoginPage loginPage = new LoginPage();
        }
        if (e.getSource() == btnTasks) {

            Tasks tasks = new Tasks();
        }
    }


}