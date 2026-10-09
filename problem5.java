
import java.util.*;

interface NotificationChannel {
    void send(String student, String title);
}

class EmailChannel implements NotificationChannel {
    public void send(String s, String t) {
        System.out.println("[Email -> " + s + "] " + t);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(String s, String t) {
        System.out.println("[SMS -> " + s + "] " + t);
    }
}

class AppChannel implements NotificationChannel {
    public void send(String s, String t) {
        System.out.println("[App -> " + s + "] " + t);
    }
}

class Student {
    String name, department;
    List<NotificationChannel> channels = new ArrayList<>();

    Student(String n, String d) {
        name = n;
        department = d;
    }

    void addChannel(NotificationChannel c) {
        channels.add(c);
    }
}

class Notice {
    String title;
    Set<String> departments;

    Notice(String title, String... departments) {
        this.title = title;
        this.departments = new LinkedHashSet<>(
            Arrays.asList(departments));
    }

    boolean isValid() {
        return title != null && !title.trim().isEmpty()
            && !departments.isEmpty()
            && !departments.contains("");
    }
}

class NoticeBoard {
    List<Student> students = new ArrayList<>();

    void addStudent(Student s) {
        students.add(s);
    }

    void postNotice(Notice n) {
        if (!n.isValid()) {
            System.out.println(
                "Cannot post notice: At least one target department is required.");
            return;
        }

        System.out.println("Notice '" + n.title + "' posted to "
            + String.join(", ", n.departments) + ".");

        for (Student s : students) {
            if (n.departments.contains(s.department))
                for (NotificationChannel c : s.channels)
                    c.send(s.name, n.title);
        }
    }
}

public class problem5 {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        board.addStudent(asha);
        board.addStudent(ravi);

        board.postNotice(new Notice(
            "Lab Closed Tomorrow", "CSE"));

        board.postNotice(new Notice(
            "Fee Deadline Extended", "CSE", "ECE"));

        board.postNotice(new Notice("Sports Day"));
    }
}