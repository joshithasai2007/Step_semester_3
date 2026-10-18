import java.util.*;

interface Chargeable {
    void chargeBay();
}

abstract class Vehicle {
    String passNumber;
    String ownerName;
    String type;
    double passFee;

    public Vehicle(String passNumber, String ownerName, String type, double passFee) {
        this.passNumber = passNumber;
        this.ownerName = ownerName;
        this.type = type;
        this.passFee = passFee;
    }
}

class Bike extends Vehicle {
    public Bike(String passNumber, String ownerName) {
        super(passNumber, ownerName, "Bike", 300);
    }
}

class Car extends Vehicle {
    public Car(String passNumber, String ownerName) {
        super(passNumber, ownerName, "Car", 1000);
    }
}

class EBike extends Vehicle implements Chargeable {
    public EBike(String passNumber, String ownerName) {
        super(passNumber, ownerName, "EBike", 300);
    }

    @Override
    public void chargeBay() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

class ECar extends Vehicle implements Chargeable {
    public ECar(String passNumber, String ownerName) {
        super(passNumber, ownerName, "ECar", 1000);
    }

    @Override
    public void chargeBay() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

public class CampusVehicleSystem {
    public static void main(String[] args) {
        Map<String, Vehicle> registry = new HashMap<>();

        String[] operations = {
            "PASS Bike KA01 Asha",
            "PASS ECar KA02 Ravi",
            "CHARGE KA02",
            "CHARGE KA01"
        };

        for (String op : operations) {
            String[] parts = op.split(" ");
            if (parts[0].equals("PASS")) {
                String type = parts[1];
                String passNum = parts[2];
                String owner = parts[3];
                Vehicle v = null;

                if (type.equals("Bike")) v = new Bike(passNum, owner);
                else if (type.equals("Car")) v = new Car(passNum, owner);
                else if (type.equals("EBike")) v = new EBike(passNum, owner);
                else if (type.equals("ECar")) v = new ECar(passNum, owner);

                if (v != null) {
                    registry.put(passNum, v);
                    System.out.println(passNum + " (" + type + ") pass fee " + (int)v.passFee);
                }
            } else if (parts[0].equals("CHARGE")) {
                String passNum = parts[1];
                Vehicle v = registry.get(passNum);
                if (v instanceof Chargeable) {
                    ((Chargeable) v).chargeBay();
                } else {
                    System.out.println(passNum + " rejected: charging unsupported");
                }
            }
        }
    }
}