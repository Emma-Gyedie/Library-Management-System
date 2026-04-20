import java.util.ArrayList;
import java.util.List;

// INHERITANCE - extends Person
public class Member extends Person {

    private String membershipId;
    private String joinDate;
    private String membershipType; // Standard, Premium, Student
    private List<String> borrowedItems;
    private List<String> borrowedDates;
    private double totalFine;

    // Constructor
    public Member(String name, String icNumber, String email, String phone,
                  String membershipId, String joinDate) {
        super(name, icNumber, email, phone);
        this.membershipId = membershipId;
        this.joinDate = joinDate;
        this.membershipType = "Standard";
        this.borrowedItems = new ArrayList<>();
        this.borrowedDates = new ArrayList<>();
        this.totalFine = 0.0;
    }

    // Overloaded constructor with membership type
    public Member(String name, String icNumber, String email, String phone,
                  String membershipId, String joinDate, String membershipType) {
        super(name, icNumber, email, phone);
        this.membershipId = membershipId;
        this.joinDate = joinDate;
        this.membershipType = membershipType;
        this.borrowedItems = new ArrayList<>();
        this.borrowedDates = new ArrayList<>();
        this.totalFine = 0.0;
    }

    // ========== GETTERS AND SETTERS ==========
    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public List<String> getBorrowedItems() {
        return borrowedItems;
    }

    public double getTotalFine() {
        return totalFine;
    }

    public void addFine(double amount) {
        this.totalFine += amount;
    }

    public void payFine(double amount) {
        if (amount <= totalFine) {
            totalFine -= amount;
            System.out.println("✅ Paid RM " + String.format("%.2f", amount));
        } else {
            System.out.println("⚠️  Amount exceeds fine. Fine is RM " + String.format("%.2f", totalFine));
        }
    }

    // Borrow an item
    public void borrowItem(String itemId, String borrowDate, String dueDate) {
        borrowedItems.add(itemId);
        borrowedDates.add(borrowDate);
        System.out.println("✅ Item '" + itemId + "' borrowed on " + borrowDate);
    }

    // Return an item
    public void returnItem(String itemId) {
        int index = borrowedItems.indexOf(itemId);
        if (index != -1) {
            borrowedItems.remove(index);
            borrowedDates.remove(index);
            System.out.println("✅ Item '" + itemId + "' returned");
        }
    }

    // Get number of items borrowed
    public int getBorrowedCount() {
        return borrowedItems.size();
    }

    // Check if member can borrow more
    public boolean canBorrow() {
        int maxBorrow = membershipType.equals("Premium") ? 10 :
                membershipType.equals("Student") ? 3 : 5;
        return borrowedItems.size() < maxBorrow;
    }

    public int getMaxBorrowLimit() {
        return membershipType.equals("Premium") ? 10 :
                membershipType.equals("Student") ? 3 : 5;
    }

    // ========== POLYMORPHISM - Method Overriding ==========
    @Override
    public void displayDetails() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│           👤 MEMBER DETAILS             │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ Name         : " + padRight(getName(), 25) + "│");
        System.out.println("│ IC Number    : " + padRight(getIcNumber(), 25) + "│");
        System.out.println("│ Email        : " + padRight(getEmail(), 25) + "│");
        System.out.println("│ Phone        : " + padRight(getPhone(), 25) + "│");
        System.out.println("│ Membership ID: " + padRight(membershipId, 25) + "│");
        System.out.println("│ Join Date    : " + padRight(joinDate, 25) + "│");
        System.out.println("│ Type         : " + padRight(membershipType, 25) + "│");
        System.out.println("│ Borrow Limit : " + padRight(getMaxBorrowLimit() + " items", 25) + "│");
        System.out.println("│ Currently    : " + padRight(borrowedItems.size() + " borrowed", 25) + "│");
        System.out.println("│ Total Fine   : RM " + padRight(String.format("%.2f", totalFine), 24) + "│");
        if (borrowedItems.size() > 0) {
            System.out.println("│ Borrowed IDs : " + padRight(String.join(", ", borrowedItems), 25) + "│");
        }
        System.out.println("└─────────────────────────────────────────┘");
    }

    private String padRight(String s, int length) {
        if (s == null) s = "";
        if (s.length() > length) return s.substring(0, length);
        return String.format("%-" + length + "s", s);
    }
}
