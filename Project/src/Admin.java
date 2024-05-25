import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Admin extends JFrame {
    static final String Dburl = "jdbc:mysql://localhost:3306/waste_management";
    static final String user = "root";
    static final String passwordd = "pass123#pass";

    private JTable userTable;
    private DefaultTableModel tableModel;

    public Admin() {
        setTitle("Welcome to Waste Management");
        setSize(1500, 1000);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        // Create the search and filter components
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 5, 5, 5));
        searchPanel.setBackground(new Color(0, 128, 0));

        JLabel searchJLabel = new JLabel("Search");
        JTextField searchJTextField = new JTextField();
        JButton searchButton = new JButton("Search");
        JLabel filterJLabel = new JLabel("Filter by Role:");
        JComboBox<String> roleBox = new JComboBox<>(new String[] { "All", "Employee", "Admin", "Moderator" });

        searchPanel.add(searchJLabel);
        searchPanel.add(searchJTextField);
        searchPanel.add(searchButton);
        searchPanel.add(filterJLabel);
        searchPanel.add(roleBox);
        searchPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(50, 30, 50, 30));
        // Create the user table
        userTable = new JTable();
        tableModel = (DefaultTableModel) userTable.getModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Role");
        populateUserTable();

        // Add the components to the frame
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(userTable), BorderLayout.CENTER);
        userTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(50, 30, 50, 30));
        userTable.setBackground(new Color(0, 128, 0));
        // Add action listeners for search and filter
        searchButton
                .addActionListener(e -> filterUsers(searchJTextField.getText(), (String) roleBox.getSelectedItem()));
        roleBox.addActionListener(e -> filterUsers(searchJTextField.getText(), (String) roleBox.getSelectedItem()));
    }

    private void populateUserTable() {
        try (Connection connection = DriverManager.getConnection(Dburl, user, passwordd)) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Registered");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                tableModel.addRow(new Object[] { name, email, role });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void filterUsers(String searchText, String role) {
        tableModel.setRowCount(0); // Clear the table
        try (Connection connection = DriverManager.getConnection(Dburl, user, passwordd)) {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM Registered WHERE name LIKE '%" + searchText + "%'";
            if (!role.equalsIgnoreCase("All")) {
                query += " AND role = '" + role + "'";
            }
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String userRole = resultSet.getString("role");
                tableModel.addRow(new Object[] { name, email, userRole });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAdminFrame() {
        setVisible(true);
    }
}