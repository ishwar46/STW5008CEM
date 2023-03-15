package Views;

import Controller.UserController;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    private JButton addTaskButton;
    private JButton createJobButton;
    private JButton scheduleButton;
    private JButton addTaskToJobButton;
    UserController userController;
    User user;
    private JLabel welcome;

    public Dashboard() {
        this.userController=new UserController();
        this.user=userController.fetchLoggedInCustomer();
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        welcome = new JLabel(("Welcome, " + user.getUsername()));
        welcome.setFont(new Font("Arial", Font.BOLD, 20));
        welcome.setHorizontalAlignment(JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        add(welcome, constraints);

        addTaskButton = new JButton("Add New Task");
        addTaskButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(addTaskButton, constraints);

        createJobButton = new JButton("Create New Job");
        createJobButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(createJobButton, constraints);

        addTaskToJobButton = new JButton("Add Task to Job");
        addTaskToJobButton.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(addTaskToJobButton, constraints);

        scheduleButton = new JButton("Schedule Job");
        scheduleButton.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 1;
        add(scheduleButton, constraints);

        setTitle("Dashboard");
        setSize(600, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // set UI color scheme
        getContentPane().setBackground(new Color(44, 62, 80));
        welcome.setForeground(new Color(236, 240, 241));
        addTaskButton.setBackground(new Color(52, 152, 219));
        createJobButton.setBackground(new Color(46, 204, 113));
        addTaskToJobButton.setBackground(new Color(231, 76, 60));
        scheduleButton.setBackground(new Color(155, 89, 182));

        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addTaskButton) {
            // Add Task logic
            AddTask addTask = new AddTask();
            addTask.setVisible(true);
            this.dispose();
        } else if (event.getSource() == createJobButton) {
            // Create Job logic
            // Add Task logic
            CreateJobPage createJobScreen = new CreateJobPage();
            createJobScreen.setVisible(true);
            this.dispose();

        }else if (event.getSource() == addTaskToJobButton) {
            // Create Job logic
            // Add Task logic
            AddTaskPage addTaskScreen = new AddTaskPage();
            addTaskScreen.setVisible(true);
            this.dispose();
        }
        else if (event.getSource() == scheduleButton) {
            ScheduleJobPage scheduleJobScreen=new ScheduleJobPage();
            scheduleJobScreen.setVisible(true);
            this.dispose();
            // Schedule logic
        }
    }
}
