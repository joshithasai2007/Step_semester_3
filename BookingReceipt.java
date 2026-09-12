import java.util.Arrays;

// Base non-final class allowing inheritance, while retaining defensive copy discipline
public class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        // Inbound defensive copy
        if (seatNumbers != null) {
            this.seatNumbers = Arrays.copyOf(seatNumbers, seatNumbers.length);
        } else {
            this.seatNumbers = new String[0];
        }
    }

    public String getBookingId() {
        return bookingId;
    }

    // Outbound defensive copy
    public String[] getSeatNumbers() {
        return Arrays.copyOf(seatNumbers, seatNumbers.length);
    }

    // Wither method: returns a new modified instance without altering internal state
    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        if (index < 0 || index >= seatNumbers.length) {
            return this;
        }
        String[] updatedSeats = Arrays.copyOf(seatNumbers, seatNumbers.length);
        updatedSeats[index] = newSeat;
        return new BookingReceipt(this.bookingId, updatedSeats);
    }
}

// Group booking subclass (Inheritance valid now)
class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

class NightlySettlementProcessor {

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (receipts != null) {
            for (BookingReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;
                // Check subclass first before parent type
                if (receipt instanceof GroupBookingReceipt) {
                    groupCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " 
                + groupCount + " group | " + individualCount + " individual";
    }
}

// public class Problem5 {
    public static void main(String[] args) {
        // Test 1: Defensive copy checks
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X";
        System.out.println("Getter defensive copy check: " + b.getSeatNumbers()[0]); // Output: A1

        // Test 2: Wither method verification
        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println("Original seats: " + Arrays.toString(b.getSeatNumbers()));       // Output: ["A1", "A2"]
        System.out.println("Updated seats:  " + Arrays.toString(updated.getSeatNumbers())); // Output: ["A1", "A3"]

        // Test 3: Null-safe batch processing (typed array)
        BookingReceipt[] batch = new BookingReceipt[] {
            new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };

        System.out.println(NightlySettlementProcessor.processNightlySettlement(batch));
        // Output: 2 processed | 1 null skipped | 1 group | 1 individual
    }
// }