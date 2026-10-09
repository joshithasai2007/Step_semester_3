
import java.time.LocalDate;
import java.util.*;

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

abstract class Room {
    int number;
    double rate;

    Room(int number, double rate) {
        this.number = number;
        this.rate = rate;
    }

    abstract double calculatePrice(long days);
}

class StandardRoom extends Room {
    StandardRoom(int number) {
        super(number, 50);
    }

    double calculatePrice(long days) {
        return rate * days;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int number) {
        super(number, 100);
    }

    double calculatePrice(long days) {
        return rate * days + 25;
    }
}

class Suite extends Room {
    Suite(int number) {
        super(number, 200);
    }

    double calculatePrice(long days) {
        return rate * days + 50;
    }
}

class Reservation {
    Customer customer;
    Room room;
    LocalDate start, end;
    boolean active = true;

    Reservation(Customer c, Room r, LocalDate start, LocalDate end) {
        customer = c;
        room = r;
        this.start = start;
        this.end = end;
    }

    boolean overlaps(LocalDate s, LocalDate e) {
        return active && start.isBefore(e) && s.isBefore(end);
    }

    void cancel(LocalDate today) {
        if (!active) {
            System.out.println("Reservation already cancelled.");
        } else if (!today.isBefore(start.minusDays(1))) {
            System.out.println("Cancellation deadline has passed.");
        } else {
            active = false;
            System.out.println("Reservation for " + customer.name +
                    ", Room " + room.number + " cancelled successfully.");
        }
    }
}

class Hotel {
    List<Reservation> reservations = new ArrayList<>();

    boolean available(Room room, LocalDate start, LocalDate end) {
        if (!start.isBefore(end)) {
            return false;
        }

        for (Reservation r : reservations) {
            if (r.room == room && r.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    void book(Customer c, Room room,
              LocalDate start, LocalDate end) {
        if (!start.isBefore(end)) {
            System.out.println("Invalid reservation dates.");
            return;
        }

        if (!available(room, start, end)) {
            System.out.println("Room " + room.number +
                    " is not available from " + start + " to " + end);
            return;
        }

        Reservation r = new Reservation(c, room, start, end);
        reservations.add(r);

        long days = java.time.temporal.ChronoUnit.DAYS.between(start, end);

        System.out.println("Reservation confirmed for " + c.name +
                ", Room " + room.number + " (" + start + " to " + end + ")");
        System.out.println("Price: $" + room.calculatePrice(days));
    }
}

public class Problem4 {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        Room standard = new StandardRoom(101);
        Room deluxe = new DeluxeRoom(201);

        LocalDate start1 = LocalDate.of(2026, 1, 1);
        LocalDate end1 = LocalDate.of(2026, 1, 5);

        System.out.println("Room available: " +
                hotel.available(standard, start1, end1));

        hotel.book(a, standard, start1, end1);
        hotel.book(b, standard,
                LocalDate.of(2026, 1, 3),
                LocalDate.of(2026, 1, 7));

        Reservation first = hotel.reservations.get(0);
        first.cancel(LocalDate.of(2025, 12, 20));

        hotel.book(c, deluxe,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12));
    }
}