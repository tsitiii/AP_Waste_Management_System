import java.sql.*;

public class Registered_DB {
    static final String Dburl = "jdbc:mysql://localhost:3306/waste_management";
    static final String user = "root";
    static final String passwordd = "pass123#pass";

    public boolean createUser(String name, String phone, String email, String password, String area) {
        try (Connection connection = DriverManager.getConnection(Dburl, user, passwordd);
             PreparedStatement stmt = connection.prepareStatement(
                 "INSERT INTO Registered (name, phone, email, password, area) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, area);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean LoginUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(Dburl, user, passwordd)) {
            String retrieveQuery = "SELECT * FROM Registered WHERE name = ?";
            PreparedStatement stmt = connection.prepareStatement(retrieveQuery);
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();
    
            if (result.next()) {
                String storedPassword = result.getString("password");
                if (storedPassword.equals(password)) {
                    return true; // Login successful
                } else {
                    return false; // Incorrect password
                }
            } else {
                return false; // User not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
    public static void main(String[] args) {
        
    }
}