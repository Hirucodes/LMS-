import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Book");
            System.out.println("5. Add Member");
            System.out.println("6. Loan Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Handle user's menu choice
            switch (option) {
                case 1:
                    BookOperations.addBook();
                    break;
                case 2:
                    BookOperations.updateBook();
                    break;
                case 3:
                    BookOperations.deleteBook();
                    break;
                case 4:
                    BookOperations.searchBook();
                    break;
                case 5:
                    MemberOperations.addMember();
                    break;
                case 6:
                    LoanOperations.loanBook();
                    break;
                case 7:
                    LoanOperations.returnBook();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return; // Exit the program
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
