// INHERITANCE - extends LibraryItem
public class Magazine extends LibraryItem {

    // Additional attributes specific to Magazine
    private String issueNumber;
    private String category;
    private int quantity;

    // Constructor
    public Magazine(String itemId, String title, String publisher, int year,
                    String issueNumber, String category) {
        super(itemId, title, publisher, year);
        this.issueNumber = issueNumber;
        this.category = category;
        this.quantity = 1;
    }

    // Overloaded constructor with quantity
    public Magazine(String itemId, String title, String publisher, int year,
                    String issueNumber, String category, int quantity) {
        super(itemId, title, publisher, year);
        this.issueNumber = issueNumber;
        this.category = category;
        this.quantity = quantity;
    }

    // ========== GETTERS AND SETTERS ==========
    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ========== POLYMORPHISM - Method Overriding ==========
    @Override
    public void displayInfo() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│           📰 MAGAZINE DETAILS           │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ Item ID      : " + padRight(getItemId(), 25) + "│");
        System.out.println("│ Title        : " + padRight(getTitle(), 25) + "│");
        System.out.println("│ Issue        : " + padRight(issueNumber, 25) + "│");
        System.out.println("│ Category     : " + padRight(category, 25) + "│");
        System.out.println("│ Publisher    : " + padRight(getPublisher(), 25) + "│");
        System.out.println("│ Year         : " + padRight(String.valueOf(getYear()), 25) + "│");
        System.out.println("│ Quantity     : " + padRight(String.valueOf(quantity), 25) + "│");
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
