import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MemberOperations {

    // Method to add a new member to the database
    public static void addMember() {
        Scanner scanner = new Scanner(System.in);

        // Get member details from user input
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        // Establish a database connection
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.executeUpdate();

            System.out.println("Member added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to test the addMember functionality
    public static void main(String[] args) {
        addMember(); // Call the addMember method
    }
}
