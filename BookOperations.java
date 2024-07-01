import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookOperations {

    // Method to add new book to database
    public static void addBook() {
        Scanner scanner = new Scanner(System.in);

        // Get book details from user input
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();

        System.out.print("Enter year published: ");
        int yearPublished = scanner.nextInt();

        // Establish a database connection
        Connection connection = DBConnection.getConnection();
        if (connection != null) {
            String sql = "INSERT INTO books (title, author, publisher, year_published) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, author);
                preparedStatement.setString(3, publisher);
                preparedStatement.setInt(4, yearPublished);
                preparedStatement.executeUpdate();

                System.out.println("Book added successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to update an existing book's details
    public static void updateBook() {
        Scanner scanner = new Scanner(System.in);

        // Get book ID and new details from user
        System.out.print("Enter book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new author: ");
        String author = scanner.nextLine();

        System.out.print("Enter new publisher: ");
        String publisher = scanner.nextLine();

        System.out.print("Enter new year published: ");
        int yearPublished = scanner.nextInt();

        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE books SET title = ?, author = ?, publisher = ?, year_published = ? WHERE book_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, publisher);
            preparedStatement.setInt(4, yearPublished);
            preparedStatement.setInt(5, bookId);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a book from the database
    public static void deleteBook() {
        Scanner scanner = new Scanner(System.in);

        // Get book ID to delete
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();

        Connection connection = DBConnection.getConnection();
        String sql = "DELETE FROM books WHERE book_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to search for books based on different criteria
    public static void searchBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search by: 1. Title 2. Author 3. Year Published");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String sql = "";
        switch (option) {
            case 1:
                System.out.print("Enter title to search: ");
                String title = scanner.nextLine();
                sql = "SELECT * FROM books WHERE title LIKE ?";
                searchAndPrintBooks(sql, "%" + title + "%");
                break;
            case 2:
                System.out.print("Enter author to search: ");
                String author = scanner.nextLine();
                sql = "SELECT * FROM books WHERE author LIKE ?";
                searchAndPrintBooks(sql, "%" + author + "%");
                break;
            case 3:
                System.out.print("Enter year published to search: ");
                int yearPublished = scanner.nextInt();
                sql = "SELECT * FROM books WHERE year_published = ?";
                searchAndPrintBooks(sql, String.valueOf(yearPublished));
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Helper method to execute search query and print results
    private static void searchAndPrintBooks(String sql, String param) {
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, param);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                int yearPublished = resultSet.getInt("year_published");

                // Print book details
                System.out.println("Book ID: " + bookId);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Publisher: " + publisher);
                System.out.println("Year Published: " + yearPublished);
                System.out.println("-------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to test the functionalities
    public static void main(String[] args) {
        addBook();    // Add a new book
        updateBook(); // Update book details
        deleteBook(); // Delete a book
        searchBook(); // Search for books
    }
}
