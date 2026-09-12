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
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }
}

public class Problem4 {

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (LibraryMember member : members) {

            if (member == null)
                continue;

            // Safe downcasting
            if (member instanceof StudentMember) {

                StudentMember student = (StudentMember) member;

                sb.append("Student | Course: ")
                  .append(student.getCourse())
                  .append(" | Books: ")
                  .append(student.getBooksBorrowed())
                  .append(" [Course via downcast: ")
                  .append(student.getCourse())
                  .append("] | ");

            } else {

                sb.append("General | Books: ")
                  .append(member.getBooksBorrowed())
                  .append(" | ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        LibraryMember m1 =
            new LibraryMember("L101", 3);

        StudentMember m2 =
            new StudentMember("S101", 5, "CSE");

        LibraryMember[] members = {m1, m2, null};

        System.out.println(batchPrint(members));
    }
}