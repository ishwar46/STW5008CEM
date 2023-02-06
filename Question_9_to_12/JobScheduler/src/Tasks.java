import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Tasks extends JFrame implements ActionListener {
    private JTextField taskNameField;
    private JTextField taskDescriptionField;
    private DefaultTableModel tableModel;

    private JButton btnUpdate;

    private JButton btnDelete;
    private JTable taskTable;


    private JTextField dropdownField;

    //Remarks
    private JTextField remarksField;

    public Tasks() {
        super("Add Task");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel taskPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel taskNameLabel = new JLabel("Task Name:");
        taskNameField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        taskPanel.add(taskNameLabel, constraints);
        constraints.gridx = 1;
        taskPanel.add(taskNameField, constraints);

        JLabel taskDescriptionLabel = new JLabel("Task Description:");
        taskDescriptionField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 1;
        taskPanel.add(taskDescriptionLabel, constraints);
        constraints.gridx = 1;
        taskPanel.add(taskDescriptionField, constraints);

        //Assigned To
        JLabel dropdownLabel = new JLabel("Assigned To:");
        dropdownField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 2;
        taskPanel.add(dropdownLabel, constraints);
        constraints.gridx = 1;
        taskPanel.add(dropdownField, constraints);


        //Remarks
        JLabel remarksLabel = new JLabel("Remarks:");
        remarksField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 3;
        taskPanel.add(remarksLabel, constraints);
        constraints.gridx = 1;
        taskPanel.add(remarksField, constraints);


        mainPanel.add(taskPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new Object[] {"Task Name", "Task Description", "Assigned To","Remarks"}, 0);
        taskTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(taskTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this);
        buttonPanel.add(btnUpdate);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);
        buttonPanel.add(btnDelete);


        add(mainPanel);
        setPreferredSize(new Dimension(400, 450));
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            String taskName = taskNameField.getText();
            String taskDescription = taskDescriptionField.getText();
            tableModel.addRow(new Object[] {taskName, taskDescription, "Not Assigned"});
        } else if (e.getActionCommand().equals("Cancel")) {
            dispose();
        }
        //Update Task
        else if (e.getActionCommand().equals("Update")) {
            int selectedRow = taskTable.getSelectedRow();
            if (selectedRow != -1) {
                String taskName = taskNameField.getText();
                String taskDescription = taskDescriptionField.getText();
                tableModel.setValueAt(taskName, selectedRow, 0);
                tableModel.setValueAt(taskDescription, selectedRow, 1);
            }
        }

        //if not selected show error


        //Delete Task
        else if (e.getActionCommand().equals("Delete")) {
            int selectedRow = taskTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            }
        }

        //Remarks
        else if (e.getActionCommand().equals("Remarks")) {
            int selectedRow = taskTable.getSelectedRow();
            if (selectedRow != -1) {
                String remarks = remarksField.getText();
                tableModel.setValueAt(remarks, selectedRow, 2);
            }
        }


    }
}
