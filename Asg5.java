import java.util.Arrays;

// Immutable Base Class
public final class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        // Inbound defensive copy
        if (bookIds != null) {
            this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
        } else {
            this.bookIds = new String[0];
        }
    }

    public String getMemberId() {
        return memberId;
    }

    // Outbound defensive copy
    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    // Wither pattern: returns a new modified instance without altering current object
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            return this;
        }
        String[] updatedBookIds = Arrays.copyOf(bookIds, bookIds.length);
        updatedBookIds[index] = newId;
        return new LoanReceipt(this.memberId, updatedBookIds);
    }
}

// Separate Reference-Only Class (ReferenceOnlyLoanReceipt)
class ReferenceOnlyLoanReceipt {
    private final String memberId;
    private final String[] bookIds;
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        this.memberId = memberId;
        this.bookIds = bookIds != null ? Arrays.copyOf(bookIds, bookIds.length) : new String[0];
        this.roomNumber = roomNumber;
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

class CirculationLedger {
    private static String branchCode;

    // Static Initialization Block
    static {
        branchCode = "PT-MAIN-BRANCH";
    }

    public static String getBranchCode() {
        return branchCode;
    }

    // Nightly processor handling mixed types & nulls safely
    public static String processNightlyCirculation(Object[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts != null) {
            for (Object receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;
                if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else if (receipt instanceof LoanReceipt) {
                    regular++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " 
                + referenceOnly + " reference-only | " + regular + " regular";
    }
}

public class Asg5 {
    public static void main(String[] args) {
        // Test 1: Defensive Copy In and Out Verification
        String[] originalBooks = new String[]{"BK-100", "BK-101"};
        LoanReceipt r = new LoanReceipt("LIB-8841", originalBooks);

        // Mutate array returned from getter
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println("Internal book ID after getter mutation: " + r.getBookIds()[0]); // Output: BK-100

        // Test 2: Wither method
        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println("Original receipt:  " + Arrays.toString(r.getBookIds()));         // Output: [BK-100, BK-101]
        System.out.println("Corrected receipt: " + Arrays.toString(corrected.getBookIds())); // Output: [BK-100, BK-102]

        // Test 3: Nightly Batch Processing with Null Safety
        Object[] batch = new Object[] {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };

        System.out.println(CirculationLedger.processNightlyCirculation(batch));
        // Output: 2 processed | 1 null skipped | 1 reference-only | 1 regular
    }
}