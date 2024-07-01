import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LoanOperations {

    // Method to loan a book to a member
    public static void loanBook() {
        Scanner scanner = new Scanner(System.in);

        // Get book ID, member ID, and loan date from user
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();

        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();

        System.out.print("Enter loan date (YYYY-MM-DD): ");
        String loanDate = scanner.next();

        // Establish a database connection
        Connection connection = DBConnection.getConnection();
        String sql = "INSERT INTO loans (book_id, member_id, loan_date) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, memberId);
            preparedStatement.setString(3, loanDate);
            preparedStatement.executeUpdate();

            System.out.println("Book loaned successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to return a loaned book
    public static void returnBook() {
        Scanner scanner = new Scanner(System.in);

        // Get loan ID and return date from user
        System.out.print("Enter loan ID: ");
        int loanId = scanner.nextInt();

        System.out.print("Enter return date (YYYY-MM-DD): ");
        String returnDate = scanner.next();

        // Establish a database connection
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE loans SET return_date = ? WHERE loan_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, returnDate);
            preparedStatement.setInt(2, loanId);
            preparedStatement.executeUpdate();

            System.out.println("Book returned successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to test loan and return functionalities
    public static void main(String[] args) {
        loanBook();   // Call the loanBook method
        returnBook(); // Call the returnBook method
    }
}
