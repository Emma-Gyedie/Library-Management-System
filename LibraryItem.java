// ABSTRACT CLASS - Demonstrates Abstraction concept
public abstract class LibraryItem {

    // ENCAPSULATION - Private attributes
    private String itemId;
    private String title;
    private String publisher;
    private int year;
    private boolean isAvailable;
    private String borrowDate;
    private String dueDate;

    // Constructor
    public LibraryItem(String itemId, String title, String publisher, int year) {
        this.itemId = itemId;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.isAvailable = true;
        this.borrowDate = "";
        this.dueDate = "";
    }

    // ========== GETTERS AND SETTERS (ENCAPSULATION) ==========
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    // ========== METHODS ==========

    // Borrow an item
    public void borrowItem(String borrowDate, String dueDate) {
        if (isAvailable) {
            isAvailable = false;
            this.borrowDate = borrowDate;
            this.dueDate = dueDate;
            System.out.println("✅ Successfully borrowed: " + title);
            System.out.println("   Borrow Date: " + borrowDate);
            System.out.println("   Due Date: " + dueDate);
        } else {
            System.out.println("❌ Sorry, '" + title + "' is currently not available.");
            System.out.println("   Expected return date: " + this.dueDate);
        }
    }

    // Return an item
    public void returnItem() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("✅ Successfully returned: " + title);
            // Calculate late fee if any
            System.out.println("   Thank you for returning!");
        } else {
            System.out.println("⚠️  '" + title + "' was not borrowed.");
        }
    }

    // ABSTRACT METHOD - Must be implemented by subclasses
    public abstract void displayInfo();

    // Display brief info
    public void displayBriefInfo() {
        System.out.println("ID: " + itemId + " | " + title + " | " + (isAvailable ? "✅ Available" : "❌ Borrowed"));
    }
}
