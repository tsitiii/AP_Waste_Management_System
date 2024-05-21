import java.sql.*;

public class Main{
    static final String Dburl = "jdbc:mysql://localhost:3306/demo";
    static final String user = "root";
    static final String password = "pass123#pass";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(Dburl, user, password);
            Statement stmt = conn.createStatement();) {
            String result= "select * from customer";
            ResultSet resul=stmt.executeQuery(result);
            
            while (resul.next()) {
                int id= resul.getInt("id");
                String Fname=resul.getString("fname");
                String lname=resul.getString("lname");
                String email=resul.getString("email");
                System.out.println("Id: "+ id+" First name: "+Fname+" last name: "+lname+"Email: "+ email);
            }
            System.out.println(conn.isValid(0) );
            System.out.println("Database demo connected successfully!");
        } catch (SQLException e) {
            System.out.println("Error at: " + e.toString());
        }
    }
}