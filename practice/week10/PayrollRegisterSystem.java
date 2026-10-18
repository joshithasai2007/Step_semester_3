import java.util.*;

abstract class Staff {
    String name;
    String type;
    double pay;

    public Staff(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public abstract void calculatePay();
}

class FullTimeStaff extends Staff {
    double salary;

    public FullTimeStaff(String name, double salary) {
        super(name, "FullTime");
        this.salary = salary;
        calculatePay();
    }

    @Override
    public void calculatePay() {
        this.pay = salary;
    }
}

class PartTimeStaff extends Staff {
    double hours;
    double rate;

    public PartTimeStaff(String name, double hours, double rate) {
        super(name, "PartTime");
        this.hours = hours;
        this.rate = rate;
        calculatePay();
    }

    @Override
    public void calculatePay() {
        this.pay = hours * rate;
    }
}

class InternStaff extends Staff {
    double stipend;

    public InternStaff(String name, double stipend) {
        super(name, "Intern");
        this.stipend = stipend;
        calculatePay();
    }

    @Override
    public void calculatePay() {
        this.pay = stipend;
    }
}

public class PayrollRegisterSystem {
    public static void main(String[] args) {
        List<Staff> staffList = new ArrayList<>();

        // Sample Input processing
        staffList.add(new FullTimeStaff("Asha", 50000));
        staffList.add(new PartTimeStaff("Ravi", 80, 300));
        staffList.add(new InternStaff("Neha", 15000));

        double totalSum = 0;
        double maxPay = -1;
        String topEarner = "";

        for (Staff s : staffList) {
            System.out.println("Payslip[name=" + s.name + ", type=" + s.type + ", pay=" + (int)s.pay + "]");
            totalSum += s.pay;
            if (s.pay > maxPay) {
                maxPay = s.pay;
                topEarner = s.name;
            }
        }

        System.out.println("Total " + (int)totalSum);
        System.out.println("top earner " + topEarner);
    }
}