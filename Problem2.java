// Base Class
class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }
}

// Single Level Inheritance
class StudentMember extends LibraryMember {
    protected String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course +
               " | Books Borrowed: " + booksBorrowed;
    }
}

// Multilevel Inheritance
class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit,
                               String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + course +
               " | Bonus Limit: " + bonusLimit +
               " | Books Borrowed: " + booksBorrowed;
    }
}

// Hierarchical Inheritance
class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department +
               " | Books Borrowed: " + booksBorrowed;
    }
}

// Main Class
public class Problem2 {

    // Classifies type of inheritance
    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Single level child (2 generations deep)";
        }
        return "Base Library Member";
    }

    // Calculates total books borrowed
    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;

        for (LibraryMember member : members) {
            if (member != null) {
                total += member.getBooksBorrowed();
            }
        }

        return total;
    }

    public static void main(String[] args) {

        LibraryMember m1 =
            new LibraryMember("L101", 3);

        StudentMember m2 =
            new StudentMember("S101", 5, "CSE");

        HonorsStudentMember m3 =
            new HonorsStudentMember("H101", 6, "CSE", 2);

        FacultyMember m4 =
            new FacultyMember("F101", 10, "EEE");

        LibraryMember[] members = {m1, m2, m3, m4};

        for (LibraryMember member : members) {
            System.out.println(member.displayInfo());
            System.out.println(classifyGeneration(member));
            System.out.println();
        }

        System.out.println("Total Books Borrowed: "
                           + getTotalBooksBorrowed(members));
    }
}