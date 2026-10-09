
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class problem2 {

    class Student {
        String name;

        Student(String name) {
            this.name = name;
        }
    }

    abstract class Assignment {
        String title;
        int maxMarks;
        LocalDate dueDate;

        Assignment(String title, int maxMarks, LocalDate dueDate) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
        }

        abstract double getPenaltyRate();
    }

    class Coding extends Assignment {
        Coding(String t, int m, LocalDate d) {
            super(t, m, d);
        }

        double getPenaltyRate() {
            return 0.10;
        }
    }

    class Written extends Assignment {
        Written(String t, int m, LocalDate d) {
            super(t, m, d);
        }

        double getPenaltyRate() {
            return 0.20;
        }
    }

    class Submission {
        Student student;
        Assignment assignment;
        LocalDate submitDate;
        private String status = "Submitted";
        private double finalMarks;

        Submission(Student s, Assignment a, LocalDate date) {
            student = s;
            assignment = a;
            submitDate = date;

            long days = Math.max(0,
                    ChronoUnit.DAYS.between(a.dueDate, date));

            System.out.println(s.name + "'s submission for '" + a.title
                    + "' received (" + (days == 0 ? "on time" : days + " days late")
                    + "). Status: " + status + ".");
        }

        void resubmit(LocalDate date) {
            if (status.equals("Graded")) {
                System.out.println("Cannot resubmit: '" + assignment.title
                        + "' has already been graded.");
                return;
            }

            submitDate = date;
            System.out.println("Submission updated.");
        }

        void grade(double marks) {
            if (!status.equals("Submitted")) {
                System.out.println("Cannot grade this submission.");
                return;
            }

            if (marks < 0 || marks > assignment.maxMarks) {
                System.out.println("Invalid marks.");
                return;
            }

            long days = Math.max(0,
                    ChronoUnit.DAYS.between(
                            assignment.dueDate, submitDate));

            double penalty = days * assignment.getPenaltyRate();
            finalMarks = marks * Math.max(0, 1 - penalty);
            status = "Graded";

            System.out.printf("%s graded: %.0f/%d",
                    student.name, finalMarks, assignment.maxMarks);

            if (days > 0)
                System.out.printf(" after %.0f%% late penalty", penalty * 100);

            System.out.println(". Status: " + status + ".");
        }
    }

    public static void main(String[] args) {
        problem2 p = new problem2();

        Student asha = p.new Student("Asha");
        Student ravi = p.new Student("Ravi");

        Assignment coding = p.new Coding("Linked List Lab", 50,
                LocalDate.of(2026, 3, 10));

        Assignment written = p.new Written("Design Essay", 50,
                LocalDate.of(2026, 3, 12));

        Submission s1 = p.new Submission(asha, coding,
                LocalDate.of(2026, 3, 10));

        Submission s2 = p.new Submission(ravi, written,
                LocalDate.of(2026, 3, 14));

        s1.grade(45);
        s2.grade(40);
        s1.resubmit(LocalDate.of(2026, 3, 15));
    }
}