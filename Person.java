public class Person {

    // ENCAPSULATION - Private attributes
    private String name;
    private String icNumber;
    private String email;
    private String phone;
    private String address;

    // Constructor
    public Person(String name, String icNumber, String email, String phone) {
        this.name = name;
        this.icNumber = icNumber;
        this.email = email;
        this.phone = phone;
        this.address = "";
    }

    // Overloaded constructor with address
    public Person(String name, String icNumber, String email, String phone, String address) {
        this.name = name;
        this.icNumber = icNumber;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // ========== GETTERS AND SETTERS ==========
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Method to be overridden (POLYMORPHISM)
    public void displayDetails() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│           👤 PERSON DETAILS             │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│ Name         : " + padRight(name, 25) + "│");
        System.out.println("│ IC Number    : " + padRight(icNumber, 25) + "│");
        System.out.println("│ Email        : " + padRight(email, 25) + "│");
        System.out.println("│ Phone        : " + padRight(phone, 25) + "│");
        if (!address.isEmpty()) {
            System.out.println("│ Address      : " + padRight(address, 25) + "│");
        }
        System.out.println("└─────────────────────────────────────────┘");
    }

    private String padRight(String s, int length) {
        if (s == null) s = "";
        if (s.length() > length) return s.substring(0, length);
        return String.format("%-" + length + "s", s);
    }
}
