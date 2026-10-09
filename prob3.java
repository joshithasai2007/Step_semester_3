
import java.util.*;

public class prob3 {

    abstract class Seat {
        String id;

        Seat(String id) {
            this.id = id;
        }

        abstract double getPrice();
    }

    class Regular extends Seat {
        Regular(String id) { super(id); }
        double getPrice() { return 150; }
    }

    class Premium extends Seat {
        Premium(String id) { super(id); }
        double getPrice() { return 250; }
    }

    class Recliner extends Seat {
        Recliner(String id) { super(id); }
        double getPrice() { return 400; }
    }

    class Customer {
        String name;

        Customer(String name) {
            this.name = name;
        }
    }

    class Show {
        String time;
        private boolean started = false;
        private Set<String> booked = new HashSet<>();

        Show(String time) {
            this.time = time;
        }

        boolean bookSeats(Booking booking) {
            if (started) {
                System.out.println("Booking denied: Show has started.");
                return false;
            }

            if (booking.seats.size() > 6 || booking.seats.isEmpty()) {
                System.out.println("Booking must contain 1 to 6 seats.");
                return false;
            }

            Set<String> requested = new HashSet<>();

            for (Seat s : booking.seats) {
                if (booked.contains(s.id) || !requested.add(s.id)) {
                    System.out.println("Seat " + s.id
                            + " is already booked for this show.");
                    return false;
                }
            }

            booked.addAll(requested);
            booking.active = true;

            System.out.print("Booking confirmed for "
                    + booking.customer.name + ": ");

            for (int i = 0; i < booking.seats.size(); i++) {
                System.out.print(booking.seats.get(i).id);
                if (i < booking.seats.size() - 1)
                    System.out.print(", ");
            }

            System.out.printf(". Total: ₹%.2f.%n", booking.getTotal());
            return true;
        }

        void cancel(Booking booking) {
            if (started) {
                System.out.println("Cannot cancel: Show has started.");
                return;
            }

            if (!booking.active) {
                System.out.println("Booking is not active.");
                return;
            }

            for (Seat s : booking.seats)
                booked.remove(s.id);

            booking.active = false;

            System.out.println(booking.customer.name
                    + "'s booking cancelled.");

            System.out.print("Seats ");

            for (int i = 0; i < booking.seats.size(); i++) {
                System.out.print(booking.seats.get(i).id);
                if (i < booking.seats.size() - 1)
                    System.out.print(", ");
            }

            System.out.println(" released.");
        }

        void startShow() {
            started = true;
        }
    }

    class Booking {
        Customer customer;
        Show show;
        List<Seat> seats;
        boolean active = false;

        Booking(Customer c, Show show, Seat... seats) {
            customer = c;
            this.show = show;
            this.seats = Arrays.asList(seats);
        }

        double getTotal() {
            double total = 0;
            for (Seat s : seats)
                total += s.getPrice();
            return total;
        }

        void confirm() {
            show.bookSeats(this);
        }

        void cancel() {
            show.cancel(this);
        }
    }

    public static void main(String[] args) {
        prob3 p = new prob3();

        Show show = p.new Show("7 PM");

        Customer asha = p.new Customer("Asha");
        Customer ravi = p.new Customer("Ravi");
        Customer neha = p.new Customer("Neha");

        Booking b1 = p.new Booking(asha, show,
                p.new Regular("A1"),
                p.new Regular("A2"),
                p.new Premium("F5"));
        b1.confirm();

        Booking b2 = p.new Booking(ravi, show,
                p.new Regular("A2"));
        b2.confirm();

        Booking b3 = p.new Booking(ravi, show,
                p.new Recliner("R1"));
        b3.confirm();

        b1.cancel();

        Booking b4 = p.new Booking(neha, show,
                p.new Regular("A2"));
        b4.confirm();
    }
}