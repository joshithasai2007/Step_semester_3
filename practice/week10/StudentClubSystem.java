import java.util.*;

class Student {
    String rollNumber;
    String name;

    public Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        // Uniqueness is determined solely by rollNumber
        return Objects.equals(rollNumber, student.rollNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }
}

public class StudentClubSystem {
    public static void main(String[] args) {
        Set<Student> members = new HashSet<>();

        String[] operations = {
            "ADD 21CS01 Asha",
            "ADD 21CS01 Asha",
            "ADD 21CS02 Ravi",
            "CONTAINS 21CS02 Ravi",
            "CONTAINS 21CS03 Priya"
        };

        for (String op : operations) {
            String[] parts = op.split(" ");
            String command = parts[0];
            String roll = parts[1];
            String name = parts[2];

            if (command.equals("ADD")) {
                Student s = new Student(roll, name);
                boolean added = members.add(s);
                if (added) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (command.equals("CONTAINS")) {
                // Check membership based on roll number instance query
                Student query = new Student(roll, name);
                boolean found = members.contains(query);
                System.out.println("contains: " + found);
            }
        }

        System.out.println("member count " + members.size());
    }
}