// INHERITANCE - extends LibraryItem
public class Book extends LibraryItem {

    // Additional attributes specific to Book
    private String author;
    private String isbn;
    private int numberOfPages;
    private String genre;

    // Constructor
    public Book(String itemId, String title, String publisher, int year,
                String author, String isbn, int numberOfPages) {
        super(itemId, title, publisher, year);
        this.author = author;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.genre = "General";
    }

    // Overloaded constructor with genre
    public Book(String itemId, String title, String publisher, int year,
                String author, String isbn, int numberOfPages, String genre) {
        super(itemId, title, publisher, year);
        this.author = author;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
    }

    // ========== GETTERS AND SETTERS ==========
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // ========== POLYMORPHISM - Method Overriding ==========
    @Override
    public void displayInfo() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│           📖 BOOK DETAILS               │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ Item ID      : " + padRight(getItemId(), 25) + "│");
        System.out.println("│ Title        : " + padRight(getTitle(), 25) + "│");
        System.out.println("│ Author       : " + padRight(author, 25) + "│");
        System.out.println("│ Genre        : " + padRight(genre, 25) + "│");
        System.out.println("│ ISBN         : " + padRight(isbn, 25) + "│");
        System.out.println("│ Publisher    : " + padRight(getPublisher(), 25) + "│");
        System.out.println("│ Year         : " + padRight(String.valueOf(getYear()), 25) + "│");
        System.out.println("│ Pages        : " + padRight(String.valueOf(numberOfPages), 25) + "│");
        System.out.println("│ Status       : " + padRight((isAvailable() ? "✅ Available" : "❌ Borrowed"), 25) + "│");
        if (!isAvailable()) {
            System.out.println("│ Due Date     : " + padRight(getDueDate(), 25) + "│");
        }
        System.out.println("└─────────────────────────────────────────┘");
    }

    // Helper method for formatting
    private String padRight(String s, int length) {
        if (s == null) s = "";
        if (s.length() > length) return s.substring(0, length);
        return String.format("%-" + length + "s", s);
    }
}
