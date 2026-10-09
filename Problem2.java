
import java.time.LocalDate;

abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days > 0 && days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days > 0 && days <= 10;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days > 0 && days <= 5;
    }
}

class LeaveRequest {
    Employee employee;
    LocalDate start, end;
    String status = "Pending";

    LeaveRequest(Employee e, LocalDate start, LocalDate end) {
        employee = e;
        this.start = start;
        this.end = end;
    }

    void review(String decision, String reviewer) {
        if (!status.equals("Pending")) {
            System.out.println("Request already reviewed.");
            return;
        }

        if (!decision.equals("Approved") &&
            !decision.equals("Rejected")) {
            System.out.println("Invalid status change.");
            return;
        }

        status = decision;
        System.out.println(employee.name + "'s leave (" +
                start + " to " + end + ") " +
                status.toLowerCase() + " by " + reviewer);
        System.out.println("Status: " + status);
    }

    void changeStatus(String newStatus) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from "
                    + status + " to " + newStatus + ".");
        }
    }
}

public class Problem2 {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest r1 = new LeaveRequest(john,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5));

        LeaveRequest r2 = new LeaveRequest(jane,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 11));

        System.out.println("Leave request submitted for John.");
        System.out.println("Status: " + r1.status);
        r1.review("Approved", "Alice");

        System.out.println("\nLeave request submitted for Jane.");
        System.out.println("Status: " + r2.status);
        r2.review("Rejected", "Bob");

        r1.changeStatus("Pending");
    }
}