class CineScreen {
    private final int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public void bookSeat() {
        // Boundary check: prevent availability from dropping below 0
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    public void cancelBooking() {
        // Boundary check: prevent availability from exceeding seatsTotal capacity
        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public int getSeatsTotal() {
        return seatsTotal;
    }
}

public class Problem3 {
    public static void main(String[] args) {
        // Test 1: Constructor zero/negative validation
        try {
            new CineScreen(0);
        } catch (IllegalArgumentException e) {
            System.out.println("new CineScreen(0) -> " + e.getMessage()); // Output: construction rejected
        }

        // Test 2: Over-booking boundary safety
        CineScreen c = new CineScreen(2);
        c.bookSeat(); 
        c.bookSeat(); 
        c.bookSeat(); // 3rd booking rejected silently
        System.out.println("Seats available after 3 bookings (capacity 2): " + c.getSeatsAvailable()); // Output: 0

        // Test 3: Over-canceling boundary safety
        c.cancelBooking(); 
        c.cancelBooking(); 
        c.cancelBooking(); // 3rd cancellation rejected silently
        System.out.println("Seats available after 3 cancellations: " + c.getSeatsAvailable()); // Output: 2
    }
}