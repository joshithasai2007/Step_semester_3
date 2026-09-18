import java.util.Arrays;

class GymMemberP3 {
    protected String memberId;
    protected int monthlyFee;

    private int[] lateFeeHistory = new int[10];
    private int feeCount = 0;

    public GymMemberP3(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    protected void chargeLateFee(int amount) {
        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < feeCount; i++) {
            total += lateFeeHistory[i];
        }
        return total;
    }
}

class PremiumMemberP3 extends GymMemberP3 {
    private String trainerName;

    public PremiumMemberP3(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        PremiumMemberP3 p = new PremiumMemberP3("MEM5", 2000, "Coach Riya");

        p.chargeLateFee(200);

        System.out.println("Total Late Fee: " + p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();
        history[0] = 999; // Modifying returned copy does not alter internal state

        System.out.println("Actual History: " + Arrays.toString(p.getLateFeeHistory()));
    }
}