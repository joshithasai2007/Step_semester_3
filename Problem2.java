
class StudentParser {
    void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",", -1);

        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.println("Name: " + fields[0]
                    + " | Roll No: " + fields[1]
                    + " | Dept: " + fields[2]);
        }
    }
}

public class Problem2 {
    public static void main(String[] args) {
        StudentParser s = new StudentParser();

        s.parseStudentRecord("Ananya Verma,RA2211003010123,CSE");
        s.parseStudentRecord("Ananya Verma,CSE");
    }
}