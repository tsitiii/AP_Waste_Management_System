import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class RecyclableMaterialsApp {
    private JFrame frame; 
    private JTextArea textArea; 
    private JTextField searchField; 
    private JComboBox<String> cityComboBox; 

    public void showRecycleFrame(){
        EventQueue.invokeLater(() -> {
            try {
                RecyclableMaterialsApp window = new RecyclableMaterialsApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RecyclableMaterialsApp() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame("Recyclable Materials Information");
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel(); 
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        JLabel lblSearch = new JLabel("Search Material:");
        panel.add(lblSearch);

        searchField = new JTextField();
        panel.add(searchField);
        searchField.setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchRecyclableMaterial();
            }
        });
        panel.add(btnSearch);

        JLabel lblCity = new JLabel("City:");
        panel.add(lblCity);
        cityComboBox = new JComboBox<>();
        loadCities(); // Load cities into the combo box
        panel.add(cityComboBox);
        JButton btnFindCenter = new JButton("Find Center");
        btnFindCenter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findRecyclingCenter();
            }
        });
        panel.add(btnFindCenter);
        textArea = new JTextArea();
        textArea.setEditable(false); // Make it read-only
        frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    // Method to search recyclable materials
    private void searchRecyclableMaterial() {
        String materialName = searchField.getText(); // Get the search input
        Connection conn = DatabaseConnection.getConnection(); // Get database connection
        if (conn != null) {
            try {
                // SQL query to fetch recyclable material information
                String query = "SELECT name, description, recycle_method FROM recyclable_materials WHERE name LIKE ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, "%" + materialName + "%"); // Set search parameter
                ResultSet rs = stmt.executeQuery(); // Execute query

                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String recycleMethod = rs.getString("recycle_method");
                    sb.append("Name: ").append(name).append("\n");
                    sb.append("Description: ").append(description).append("\n");
                    sb.append("Recycle Method: ").append(recycleMethod).append("\n\n");
                }
                textArea.setText(sb.toString()); // Display results in the text area

                rs.close(); 
                stmt.close(); 
                conn.close(); 
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error loading data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Unable to connect to the database", "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void findRecyclingCenter() {
        String city = (String) cityComboBox.getSelectedItem(); // Get selected city
        Connection conn = DatabaseConnection.getConnection(); // Get database connection
        if (conn != null) {
            try {
                // SQL query to fetch recycling centers information
                String query = "SELECT name, address, contact_details, accepted_materials FROM recycling_centers WHERE city = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, city); // Set city parameter
                ResultSet rs = stmt.executeQuery(); // Execute query

                StringBuilder sb = new StringBuilder();
                // Process the result set
                while (rs.next()) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String contactDetails = rs.getString("contact_details");
                    String acceptedMaterials = rs.getString("accepted_materials");
                    sb.append("Name: ").append(name).append("\n");
                    sb.append("Address: ").append(address).append("\n");
                    sb.append("Contact Details: ").append(contactDetails).append("\n");
                    sb.append("Accepted Materials: ").append(acceptedMaterials).append("\n\n");
                }
                textArea.setText(sb.toString()); // Display results in the text area

                rs.close(); // Close result set
                stmt.close(); // Close statement
                conn.close(); // Close connection

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error loading data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Unable to connect to the database", "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCities() {
        Connection conn = DatabaseConnection.getConnection(); // Get database connection
        if (conn != null) {
            try {
                // SQL query to fetch distinct cities from recycling centers
                String query = "SELECT DISTINCT city FROM recycling_centers";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery(); // Execute query

                // Process the result set
                while (rs.next()) {
                    String city = rs.getString("city");
                    cityComboBox.addItem(city); // Add city to combo box
                }

                rs.close(); // Close result set
                stmt.close(); // Close statement
                conn.close(); // Close connection
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error loading cities", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Unable to connect to the database", "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
