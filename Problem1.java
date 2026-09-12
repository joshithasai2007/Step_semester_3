import java.util.Arrays;

class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    // Constructor contains the sole validation rule
    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Construction rejected: ID must be at least 4 non-blank characters.");
        }
        this.memberId = memberId.trim();
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }
}

// Single Inheritance
class StudentMember extends LibraryMember {
    protected String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }
}

public class Problem1 {
    // Rejection counter relying purely on try/catch
    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new LibraryMember(id, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        // Example test
        System.out.println(enrollBatch(new String[]{"STU1", "LB1", "STU2", " ", "STU3"}, 3));
        // Output: Enrolled: 3 | Rejected: 2
    }
}