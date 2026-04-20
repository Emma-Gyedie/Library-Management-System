import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Library {
    private List<LibraryItem> items;
    private List<Member> members;
    private Scanner scanner;

    public Library() {
        items = new ArrayList<>();
        members = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // ========== CORE OPERATIONS ==========

    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("✅ Item added successfully: " + item.getTitle());
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("✅ Member added successfully: " + member.getName());
    }

    public LibraryItem searchItem(String searchTerm) {
        for (LibraryItem item : items) {
            if (item.getItemId().equalsIgnoreCase(searchTerm) ||
                    item.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public Member searchMember(String searchTerm) {
        for (Member member : members) {
            if (member.getIcNumber().equalsIgnoreCase(searchTerm) ||
                    member.getMembershipId().equalsIgnoreCase(searchTerm) ||
                    member.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                return member;
            }
        }
        return null;
    }

    // Borrow item with due date calculation
    public void borrowItem(String itemId, String memberSearch) {
        LibraryItem item = searchItem(itemId);
        Member member = searchMember(memberSearch);

        if (item == null) {
            System.out.println("❌ Item not found!");
            return;
        }

        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }

        if (!member.canBorrow()) {
            System.out.println("❌ Member has reached maximum borrowing limit (" + member.getMaxBorrowLimit() + " items)!");
            return;
        }

        if (item.isAvailable()) {
            // Calculate due date (14 days from today)
            LocalDate today = LocalDate.now();
            LocalDate dueDate = today.plusDays(14);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String borrowDateStr = today.format(formatter);
            String dueDateStr = dueDate.format(formatter);

            item.borrowItem(borrowDateStr, dueDateStr);
            member.borrowItem(itemId, borrowDateStr, dueDateStr);
        } else {
            System.out.println("❌ Item is already borrowed!");
            System.out.println("   Expected return date: " + item.getDueDate());
        }
    }

    // Return item
    public void returnItem(String itemId, String memberSearch) {
        LibraryItem item = searchItem(itemId);
        Member member = searchMember(memberSearch);

        if (item == null) {
            System.out.println("❌ Item not found!");
            return;
        }

        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }

        item.returnItem();
        member.returnItem(itemId);
    }

    // ========== DISPLAY METHODS ==========

    public void displayAllItems() {
        if (items.isEmpty()) {
            System.out.println("📚 No items in the library.");
            return;
        }

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    📚 LIBRARY CATALOG                         ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");

        int bookCount = 0, magCount = 0;
        for (LibraryItem item : items) {
            if (item instanceof Book) bookCount++;
            else if (item instanceof Magazine) magCount++;
        }
        System.out.println("║  Total: " + items.size() + " items (Books: " + bookCount + ", Magazines: " + magCount + ")          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        for (LibraryItem item : items) {
            item.displayInfo();
            System.out.println();
        }
    }

    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("👥 No members registered.");
            return;
        }

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    👥 LIBRARY MEMBERS                         ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Total Members: " + members.size() + "                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        for (Member member : members) {
            member.displayDetails();
            System.out.println();
        }
    }

    public void displayAvailableItems() {
        System.out.println("\n📖 AVAILABLE ITEMS:");
        System.out.println("─────────────────────────────────────────");
        boolean found = false;
        for (LibraryItem item : items) {
            if (item.isAvailable()) {
                item.displayBriefInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available items at the moment.");
        }
    }

    public void displayBorrowedItems() {
        System.out.println("\n❌ BORROWED ITEMS:");
        System.out.println("─────────────────────────────────────────");
        boolean found = false;
        for (LibraryItem item : items) {
            if (!item.isAvailable()) {
                item.displayBriefInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No borrowed items.");
        }
    }

    // ========== ADD METHODS ==========

    public void addNewBook() {
        System.out.println("\n📖 ─── ADD NEW BOOK ───");
        System.out.print("   Item ID      : ");
        String itemId = scanner.nextLine();

        // Check if ID exists
        if (searchItem(itemId) != null) {
            System.out.println("❌ Item ID already exists!");
            return;
        }

        System.out.print("   Title        : ");
        String title = scanner.nextLine();
        System.out.print("   Author       : ");
        String author = scanner.nextLine();
        System.out.print("   Publisher    : ");
        String publisher = scanner.nextLine();
        System.out.print("   Year         : ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("   ISBN         : ");
        String isbn = scanner.nextLine();
        System.out.print("   Pages        : ");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.print("   Genre        : ");
        String genre = scanner.nextLine();

        Book book = new Book(itemId, title, publisher, year, author, isbn, pages, genre);
        addItem(book);
    }

    public void addNewMagazine() {
        System.out.println("\n📰 ─── ADD NEW MAGAZINE ───");
        System.out.print("   Item ID      : ");
        String itemId = scanner.nextLine();

        if (searchItem(itemId) != null) {
            System.out.println("❌ Item ID already exists!");
            return;
        }

        System.out.print("   Title        : ");
        String title = scanner.nextLine();
        System.out.print("   Publisher    : ");
        String publisher = scanner.nextLine();
        System.out.print("   Year         : ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("   Issue Number : ");
        String issueNumber = scanner.nextLine();
        System.out.print("   Category     : ");
        String category = scanner.nextLine();

        Magazine magazine = new Magazine(itemId, title, publisher, year, issueNumber, category);
        addItem(magazine);
    }

    public void addNewMember() {
        System.out.println("\n👤 ─── ADD NEW MEMBER ───");
        System.out.print("   Name              : ");
        String name = scanner.nextLine();
        System.out.print("   IC/Passport Number: ");
        String icNumber = scanner.nextLine();

        if (searchMember(icNumber) != null) {
            System.out.println("❌ Member with this IC already exists!");
            return;
        }

        System.out.print("   Email             : ");
        String email = scanner.nextLine();
        System.out.print("   Phone             : ");
        String phone = scanner.nextLine();
        System.out.print("   Membership ID     : ");
        String membershipId = scanner.nextLine();
        System.out.print("   Join Date (DD/MM/YYYY): ");
        String joinDate = scanner.nextLine();
        System.out.print("   Membership Type (Standard/Premium/Student): ");
        String type = scanner.nextLine();

        Member member = new Member(name, icNumber, email, phone, membershipId, joinDate, type);
        addMember(member);
    }

    // ========== SEARCH METHODS ==========

    public void searchItemMenu() {
        System.out.println("\n🔍 ─── SEARCH ITEM ───");
        System.out.print("   Enter Item ID or Title: ");
        String searchTerm = scanner.nextLine();
        LibraryItem item = searchItem(searchTerm);

        if (item != null) {
            item.displayInfo();
        } else {
            System.out.println("❌ Item not found!");
        }
    }

    public void searchMemberMenu() {
        System.out.println("\n🔍 ─── SEARCH MEMBER ───");
        System.out.print("   Enter IC Number, Membership ID, or Name: ");
        String searchTerm = scanner.nextLine();
        Member member = searchMember(searchTerm);

        if (member != null) {
            member.displayDetails();
        } else {
            System.out.println("❌ Member not found!");
        }
    }

    // ========== MAIN MENU ==========

    public void showMenu() {
        int choice;

        do {
            System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                    LIBRARY MANAGEMENT SYSTEM                 ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  📚 ITEM MANAGEMENT                                          ║");
            System.out.println("║     1. Add New Book                                          ║");
            System.out.println("║     2. Add New Magazine                                      ║");
            System.out.println("║     3. Display All Items                                     ║");
            System.out.println("║     4. Search Item                                           ║");
            System.out.println("║                                                              ║");
            System.out.println("║  👥 MEMBER MANAGEMENT                                        ║");
            System.out.println("║     5. Add New Member                                        ║");
            System.out.println("║     6. Display All Members                                   ║");
            System.out.println("║     7. Search Member                                         ║");
            System.out.println("║                                                              ║");
            System.out.println("║  🔄 TRANSACTIONS                                             ║");
            System.out.println("║     8. Borrow Item                                           ║");
            System.out.println("║     9. Return Item                                           ║");
            System.out.println("║                                                              ║");
            System.out.println("║  📊 REPORTS                                                  ║");
            System.out.println("║     10. View Available Items                                 ║");
            System.out.println("║     11. View Borrowed Items                                  ║");
            System.out.println("║                                                              ║");
            System.out.println("║  🚪 EXIT                                                     ║");
            System.out.println("║     0. Exit System                                           ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            System.out.print("\n   Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    addNewMagazine();
                    break;
                case 3:
                    displayAllItems();
                    break;
                case 4:
                    searchItemMenu();
                    break;
                case 5:
                    addNewMember();
                    break;
                case 6:
                    displayAllMembers();
                    break;
                case 7:
                    searchMemberMenu();
                    break;
                case 8:
                    performBorrow();
                    break;
                case 9:
                    performReturn();
                    break;
                case 10:
                    displayAvailableItems();
                    break;
                case 11:
                    displayBorrowedItems();
                    break;
                case 0:
                    System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
                    System.out.println("║         THANK YOU FOR USING LIBRARY MANAGEMENT SYSTEM!        ║");
                    System.out.println("║                      GOODBYE!                                 ║");
                    System.out.println("╚══════════════════════════════════════════════════════════════╝");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please enter a number between 0-11.");
            }
        } while (choice != 0);
    }

    private void performBorrow() {
        System.out.println("\n🔄 ─── BORROW ITEM ───");
        System.out.print("   Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("   Enter Member IC/Membership ID: ");
        String memberSearch = scanner.nextLine();
        borrowItem(itemId, memberSearch);
    }

    private void performReturn() {
        System.out.println("\n🔄 ─── RETURN ITEM ───");
        System.out.print("   Enter Item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("   Enter Member IC/Membership ID: ");
        String memberSearch = scanner.nextLine();
        returnItem(itemId, memberSearch);
    }
}
