package Views;

import Controller.UserController;
import Model.Task;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTask extends JFrame implements ActionListener {

    private UserController userController;

    User user;
    private JLabel lbltaskid, lbltaskname;
    private JTextField taskIdField, taskNameField;
    private JButton addTaskButton, goBackButton;

    public AddTask() {
        this.userController=new UserController();
        this.user=userController.fetchLoggedInCustomer();
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        lbltaskid = new JLabel("Task ID:");
        lbltaskid.setFont(new Font("Arial", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(lbltaskid, constraints);

        taskIdField = new JTextField(20);
        taskIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(taskIdField, constraints);

        lbltaskname = new JLabel("Task Name:");
        lbltaskname.setFont(new Font("Arial", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(lbltaskname, constraints);

        taskNameField = new JTextField(20);
        taskNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(taskNameField, constraints);

        addTaskButton = new JButton("Add Task");
        addTaskButton.setFont(new Font("Arial", Font.BOLD, 14));
        addTaskButton.setBackground(Color.GREEN);
        addTaskButton.setForeground(Color.WHITE);
        addTaskButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(addTaskButton, constraints);

        goBackButton = new JButton("Go Back");
        goBackButton.setFont(new Font("Arial", Font.BOLD, 14));
        goBackButton.setBackground(Color.RED);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(goBackButton, constraints);

        setTitle("Add Task");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addTaskButton) {
            int taskId = Integer.parseInt(taskIdField.getText());
            String taskName = taskNameField.getText();
            Task task = new Task(taskId, taskName);
            UserController userController1 = new UserController();
            int result = userController1.taskCustomerPreparedStatement(task);
            if (result == 1) {
                JOptionPane.showMessageDialog(this, "Task added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Task not added. Try again!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (event.getSource() == goBackButton) {
            // Go back to Dashboard logic
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        AddTask addTask = new AddTask();
        addTask.setVisible(true);
    }
}
