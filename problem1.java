
import java.util.*;

abstract class Vehicle {
    String id;
    double rate;
    boolean available = true;

    Vehicle(String id, double rate) {
        this.id = id;
        this.rate = rate;
    }

    abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String id) { super(id, 50); }

    double calculateCharge(int days) {
        return rate * days;
    }
}

class SUV extends Vehicle {
    SUV(String id) { super(id, 80); }

    double calculateCharge(int days) {
        return rate * days + 20;
    }
}

class Truck extends Vehicle {
    Truck(String id) { super(id, 100); }

    double calculateCharge(int days) {
        return rate * days + 50;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    Vehicle vehicle;
    Customer customer;
    int days;
    boolean active = true;

    Rental(Vehicle v, Customer c, int days) {
        vehicle = v;
        customer = c;
        this.days = days;
    }

    void returnVehicle() {
        vehicle.available = true;
        active = false;
        System.out.println(vehicle.id + " returned by " + customer.name);
    }
}

class RentalSystem {
    void rent(Vehicle v, Customer c, int days) {
        if (days <= 0) {
            System.out.println("Invalid rental duration.");
            return;
        }

        if (!v.available) {
            System.out.println(v.id + " is currently unavailable.");
            return;
        }

        v.available = false;
        Rental r = new Rental(v, c, days);
        System.out.println(v.id + " rented successfully by " + c.name);
        System.out.println("Rental charge: $" +
                r.vehicle.calculateCharge(r.days));
    }
}

public class problem1 {
    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();

        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        system.rent(sedan, c1, 3);
        system.rent(sedan, c2, 2);

        Rental r = new Rental(sedan, c1, 3);
        r.returnVehicle();

        system.rent(suv, c3, 5);
    }
}