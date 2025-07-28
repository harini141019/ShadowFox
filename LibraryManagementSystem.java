import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean issued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.issued = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isIssued() { return issued; }
    public void issue() { this.issued = true; }
    public void returnBook() { this.issued = false; }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author +
                ", ISBN: " + isbn + ", Issued: " + issued;
    }
}

class Library {
    private Map<String, Book> books;

    public Library() {
        books = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public Book getBook(String isbn) {
        return books.get(isbn);
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
            return;
        }
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    public boolean issueBook(String isbn) {
        Book book = books.get(isbn);
        if (book != null && !book.isIssued()) {
            book.issue();
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        Book book = books.get(isbn);
        if (book != null && book.isIssued()) {
            book.returnBook();
            return true;
        }
        return false;
    }
}

public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBookUI();
                    break;
                case 2:
                    issueBookUI();
                    break;
                case 3:
                    returnBookUI();
                    break;
                case 4:
                    library.displayBooks();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void addBookUI() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        Book book = new Book(title, author, isbn);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    private static void issueBookUI() {
        System.out.print("Enter ISBN to issue: ");
        String isbn = scanner.nextLine();
        if (library.issueBook(isbn)) {
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book not found or already issued.");
        }
    }

    private static void returnBookUI() {
        System.out.print("Enter ISBN to return: ");
        String isbn = scanner.nextLine();
        if (library.returnBook(isbn)) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found or not issued.");
        }
    }
}