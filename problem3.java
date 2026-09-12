import java.util.Arrays;

class LibraryMember {
    protected String memberId;
    protected int borrowLimit;

    // Encapsulated fine ledger
    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    public LibraryMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    protected void chargeFine(int amount) {
        if (amount > 0 && fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }

    // Defensive copy prevents internal state tampering
    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }
        return total;
    }
}

class StudentMember extends LibraryMember {
    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
    }

    @Override
    protected void chargeFine(int amount) {
        // Halves the fine and delegates ledger recording to super
        super.chargeFine(amount / 2);
    }
}

public class problem3 {
    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU5", 3, "CSE");
        s.chargeFine(100);
        
        System.out.println("Total Fine: " + s.getTotalFine()); // Output: 50

        // External tampering attempt
        int[] history = s.getFineHistory();
        history[0] = 999;

        // Internal state remains unmodified
        System.out.println("Recorded Fine: " + s.getFineHistory()[0]); // Output: 50
    }
}