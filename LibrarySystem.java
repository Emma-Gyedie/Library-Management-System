import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   WELCOME TO LIBRARY MANAGEMENT SYSTEM   ");
        System.out.println("=========================================");

        Library library = new Library();

        // Loading sample data
        System.out.println("\n📚 Loading sample data...\n");

        // Sample Books
        Book book1 = new Book("B001", "Java Programming: A Comprehensive Guide", "Tech Press", 2020,
                "John Smith", "978-1234567890", 450);
        Book book2 = new Book("B002", "Data Structures and Algorithms", "CS Press", 2021,
                "Jane Doe", "978-0987654321", 580);
        Book book3 = new Book("B003", "Clean Code: Best Practices", "Software Press", 2019,
                "Robert Martin", "978-1122334455", 320);

        // Sample Magazines
        Magazine mag1 = new Magazine("M001", "TIME Magazine", "Time USA", 2024,
                "Vol 185, Issue 4", "News & Politics");
        Magazine mag2 = new Magazine("M002", "National Geographic", "National Geographic Society", 2024,
                "Issue 245", "Science & Nature");
        Magazine mag3 = new Magazine("M003", "Forbes", "Forbes Media", 2024,
                "Vol 207, Issue 2", "Business");

        // Sample Members
        Member member1 = new Member("Ahmad Faiz Bin Abdullah", "890101-10-5678",
                "ahmad.faiz@email.com", "012-3456789",
                "M2024001", "15/01/2024");
        Member member2 = new Member("Siti Nurhaliza Binti Mohd", "900505-08-1234",
                "siti.nur@email.com", "013-9876543",
                "M2024002", "20/01/2024");
        Member member3 = new Member("Tan Wei Ming", "950312-14-5678",
                "weiming.tan@email.com", "016-2345678",
                "M2024003", "25/01/2024");

        // Add to library
        library.addItem(book1);
        library.addItem(book2);
        library.addItem(book3);
        library.addItem(mag1);
        library.addItem(mag2);
        library.addItem(mag3);

        library.addMember(member1);
        library.addMember(member2);
        library.addMember(member3);

        System.out.println("\n✅ Sample data loaded successfully!");
        System.out.println("📖 Total Items: 6 (3 Books + 3 Magazines)");
        System.out.println("👥 Total Members: 3");

        // Start the system
        library.showMenu();
    }
}
