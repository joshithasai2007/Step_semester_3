public class problem1 {

    abstract class WashCycle {
        abstract int getDuration();
        abstract double getCharge();
    }

    class Quick extends WashCycle {
        int getDuration() { return 30; }
        double getCharge() { return 20; }
    }

    class Normal extends WashCycle {
        int getDuration() { return 45; }
        double getCharge() { return 30; }
    }

    class Heavy extends WashCycle {
        int getDuration() { return 60; }
        double getCharge() { return 45; }
    }

    class Student {
        String name;

        Student(String name) {
            this.name = name;
        }
    }

    class WashingMachine {
        String id;
        boolean busy = false;

        WashingMachine(String id) {
            this.id = id;
        }

        void startWash(Student s, WashCycle w) {
            if (busy) {
                System.out.println("Machine " + id + " is busy.");
                return;
            }

            busy = true;
            System.out.println(w.getClass().getSimpleName()
                    + " wash started on " + id
                    + " for " + s.name
                    + " (" + w.getDuration()
                    + " min). Charge: ₹" + w.getCharge());
        }

        void completeWash() {
            if (!busy) {
                System.out.println(id + " is already free.");
                return;
            }

            System.out.println(id + " cycle completed.");
            busy = false;
            System.out.println(id + " is now free.");
        }
    }

    public static void main(String[] args) {
        problem1 p = new problem1();

        Student asha = p.new Student("Asha");
        Student ravi = p.new Student("Ravi");
        Student neha = p.new Student("Neha");

        WashingMachine m1 = p.new WashingMachine("M1");
        WashingMachine m2 = p.new WashingMachine("M2");

        m1.startWash(asha, p.new Quick());
        m1.startWash(ravi, p.new Heavy());
        m2.startWash(ravi, p.new Heavy());
        m1.completeWash();
        m1.startWash(neha, p.new Normal());
    }
}