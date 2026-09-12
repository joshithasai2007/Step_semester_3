class LibraryMember {
    private static int counter = 100;
    private static int totalMembersEnrolled = 0;

    // Read-only member identifier string assigned during creation
    public final String memberNumber;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(int borrowLimit) {
        counter++;
        this.memberNumber = "LIB-" + counter;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
        totalMembersEnrolled++;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    // Method Overloading: delegates work to the no-argument variant
    public void borrowBook(String genre) {
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static int getMembersEnrolled() {
        return totalMembersEnrolled;
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class Problem5 {
    // Validates exact format: "R" + 2 digits + 1 uppercase letter without regular expressions
    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'R'
            && Character.isDigit(code.charAt(1))
            && Character.isDigit(code.charAt(2))
            && Character.isUpperCase(code.charAt(3));
    }

    // Nightly audit safely navigating null entries
    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        if (members != null) {
            for (LibraryMember member : members) {
                if (member == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (member instanceof FacultyMember) {
                    facultyCount++;
                } else {
                    regularCount++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + facultyCount + " faculty | " + regularCount + " regular";
    }

    public static void main(String[] args) {
        // Test 1: Auto-generated memberNumber and static count
        LibraryMember m1 = new LibraryMember(3);
        System.out.println("Member Number: " + m1.memberNumber); // Output: LIB-101
        System.out.println("Members Enrolled: " + LibraryMember.getMembersEnrolled()); // Output: 1

        // Test 2: Overloaded borrowBook method delegation
        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println("Books Borrowed: " + m1.getBooksBorrowed()); // Output: 2

        // Test 3: Renewal code format verification
        System.out.println("R12A valid? " + isValidRenewalCode("R12A")); // Output: true
        System.out.println("R1A valid?  " + isValidRenewalCode("R1A"));  // Output: false
        System.out.println("X12A valid? " + isValidRenewalCode("X12A")); // Output: false

        // Test 4: Nightly audit with null handling
        LibraryMember[] batch = new LibraryMember[] {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };
        System.out.println(processNightlyAudit(batch)); 
        // Output: 2 processed | 1 null skipped | 1 faculty | 1 regular
    }
}