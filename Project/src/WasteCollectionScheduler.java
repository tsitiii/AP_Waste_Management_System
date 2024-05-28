import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class WasteCollectionScheduler extends JFrame {
    static final String Dburl = "jdbc:mysql://localhost:3306/waste_management";
    static final String user = "root";
    static final String passwordd = "pass123#pass";

    private JTable scheduleTable;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton, deleteButton;
    private JTextField locationField, typeField, dateField;
    private JLabel locationLabel, typeLabel, dateLabel;

    public WasteCollectionScheduler() {
        setTitle("Waste Collection Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create the schedule table
        String[] columnNames = {"Location", "Type", "Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        scheduleTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scheduleTable);

        try (Connection connection = DriverManager.getConnection(Dburl, user, passwordd);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM schedule_events")) {
            while (resultSet.next()) {
                String location = resultSet.getString("location");
                String type = resultSet.getString("type");
                String dateStr = resultSet.getString("event_date");
                tableModel.addRow(new Object[]{location, type, dateStr});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        locationLabel = new JLabel("Location:");
        locationField = new JTextField(20);
        typeLabel = new JLabel("Type:");
        typeField = new JTextField(20);
        dateLabel = new JLabel("Date (yyyy-MM-dd):");
        dateField = new JTextField(20);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        // Add action listeners to the buttons
        addButton.addActionListener(e -> addScheduleEvent());
        editButton.addActionListener(e -> editScheduleEvent());
        deleteButton.addActionListener(e -> deleteScheduleEvent());

        // Organize the layout
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.add(locationLabel);
        inputPanel.add(locationField);
        inputPanel.add(typeLabel);
        inputPanel.add(typeField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addScheduleEvent() {
        String location = locationField.getText();
        String type = typeField.getText();
        String dateStr = dateField.getText();

        if (!location.isEmpty() && !type.isEmpty() && !dateStr.isEmpty()) {
            try {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-d"));
                tableModel.addRow(new Object[]{location, type, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))});

                try (Connection connection = DriverManager.getConnection(Dburl, user, passwordd);
                     PreparedStatement statement = connection.prepareStatement("INSERT INTO schedule_events (location, type,event_date) VALUES (?, ?, ?)")) {
                    statement.setString(1, location);
                    statement.setString(2, type);
                    statement.setString(3, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // clearInputFields();
            } catch (Exception e) {
                try {
                    LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-M-d"));
                    tableModel.addRow(new Object[]{location, type, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))});

                    try (Connection connection = DriverManager.getConnection(Dburl, user, passwordd);
                         PreparedStatement statement = connection.prepareStatement("INSERT INTO schedule_events (location, type, event_date) VALUES (?, ?, ?)")) {
                        statement.setString(1, location);
                        statement.setString(2, type);
                        statement.setString(3, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        statement.executeUpdate();
                    } catch (SQLException ev) {
                        ev.printStackTrace();
                    }

                    // clearInputFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid date format. Please use the format 'yyyy-MM-dd' or 'yyyy-M-d'.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editScheduleEvent() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow != -1) {
            String location = locationField.getText();
            String type = typeField.getText();
            String dateStr = dateField.getText();

            if (!location.isEmpty() && !type.isEmpty() && !dateStr.isEmpty()) {
                try {
                    LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    tableModel.setValueAt(location, selectedRow, 0);
                    tableModel.setValueAt(type, selectedRow, 1);
                    tableModel.setValueAt(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), selectedRow, 2);

                    try (Connection connection = DriverManager.getConnection(Dburl, user, passwordd);
                         PreparedStatement statement = connection.prepareStatement("UPDATE schedule_events SET location = ?, type = ?, event_date = ? WHERE id = ?")) {
                        statement.setString(1, location);
                        statement.setString(2, type);
                        statement.setString(3, date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        statement.setInt(4, selectedRow + 1); // Assuming the table has an auto-incrementing id column starting from 1
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    // clearInputFields();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Invalid date format. Please use the format 'yyyy-MM-dd'.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteScheduleEvent() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow != -1) {
            try (Connection connection = DriverManager.getConnection(Dburl, user, passwordd);
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM schedule_events WHERE id = ?")) {
                int id = (int) tableModel.getValueAt(selectedRow, 0); // Assuming the first column is the id
                statement.setInt(1, id);
                statement.executeUpdate();
    
                // Remove the corresponding row from the tableModel
                tableModel.removeRow(selectedRow);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void showSchedule(){
            WasteCollectionScheduler scheduler = new WasteCollectionScheduler();
            scheduler.setVisible(true);
        }
    }
