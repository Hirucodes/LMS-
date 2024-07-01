import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database URL, username, and password constants
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Method to establish a database connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Attempt to create a connection to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Print an error message if the connection fails
            System.out.println("Connection failed!");
            e.printStackTrace(); // Print the stack trace for debugging
        }
        return connection; // Return the connection object (or null if failed)
    }

    // Main method to test the database connection
    public static void main(String[] args) {
        getConnection(); // Call the getConnection method
    }
}
