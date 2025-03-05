import java.util.Scanner;

public class LibraryManagementSystem {
    private static class Book {
        String title;
        String author;
        int bookID;
        Book next;

        Book(String title, String author, int bookID) {
            this.title = title;
            this.author = author;
            this.bookID = bookID;
            this.next = null;
        }
    }

    private Book head;

    // Add a book to the library (at the beginning for simplicity)
    public void addBook(String title, String author, int bookID) {
        Book newBook = new Book(title, author, bookID);
        newBook.next = head;
        head = newBook;
        System.out.println("✅ Book Added: " + title + " by " + author);
    }

    // Remove a book by its ID
    public void removeBookById(int bookID) {
        if (head == null) {
            System.out.println("⚠ No books available to remove.");
            return;
        }

        // If the book to remove is the first book
        if (head.bookID == bookID) {
            System.out.println("❌ Book Removed: " + head.title);
            head = head.next;
            return;
        }

        // Search for the book to remove
        Book current = head;
        while (current.next != null) {
            if (current.next.bookID == bookID) {
                System.out.println("❌ Book Removed: " + current.next.title);
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }

        System.out.println("⚠ Book with ID " + bookID + " not found.");
    }

    // Search for a book by its ID
    public void searchBookById(int bookID) {
        Book current = head;
        while (current != null) {
            if (current.bookID == bookID) {
                System.out.println("\n🔎 Book Found:\n📖 Title: " + current.title + "\n✍ Author: " + current.author
                        + "\n🆔 ID: " + current.bookID);
                return;
            }
            current = current.next;
        }
        System.out.println("⚠ Book with ID " + bookID + " not found.");
    }

    // Display all books in the library
    public void displayBooks() {
        if (head == null) {
            System.out.println("📚 No books in the library.");
            return;
        }

        System.out.println("\n📚 Library Books:");
        Book current = head;
        while (current != null) {
            System.out.println("🆔 " + current.bookID + " | 📖 " + current.title + " | ✍ " + current.author);
            current = current.next;
        }
    }

    // Main method with a user-friendly menu
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n📌 Library Management System");
            System.out.println("1️⃣ Add a Book");
            System.out.println("2️⃣ Remove a Book");
            System.out.println("3️⃣ Search a Book");
            System.out.println("4️⃣ Display All Books");
            System.out.println("5️⃣ Exit");
            System.out.print("👉 Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("📖 Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("✍ Enter Author Name: ");
                    String author = scanner.nextLine();
                    System.out.print("🆔 Enter Book ID: ");
                    int bookID = scanner.nextInt();
                    library.addBook(title, author, bookID);
                    break;

                case 2:
                    System.out.print("🆔 Enter Book ID to Remove: ");
                    int removeID = scanner.nextInt();
                    library.removeBookById(removeID);
                    break;

                case 3:
                    System.out.print("🆔 Enter Book ID to Search: ");
                    int searchID = scanner.nextInt();
                    library.searchBookById(searchID);
                    break;

                case 4:
                    library.displayBooks();
                    break;

                case 5:
                    System.out.println("📕 Exiting Library Management System. Have a great day! 🚀");
                    scanner.close();
                    return;

                default:
                    System.out.println("⚠ Invalid choice. Please try again.");
            }
        }
    }
}
