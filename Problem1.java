import java.util.Arrays;

class GymMemberP1 {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMemberP1(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid Member ID");
        }
        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Invalid Fee");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println("Standard Member | Sessions: " + sessionsAttended);
    }

    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new GymMemberP1(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }
}

class PremiumMemberP1 extends GymMemberP1 {
    private String trainerName;

    public PremiumMemberP1(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Premium Member | Trainer: " + trainerName + " | Sessions: " + sessionsAttended);
    }
}

public class Problem1 {
    public static void main(String[] args) {
        PremiumMemberP1 p = new PremiumMemberP1("MEM01", 2000, "Coach Riya");

        p.attendSession();
        p.attendSession();

        System.out.println("Sessions attended: " + p.getSessionsAttended());

        // Array with valid IDs (length >= 4) and invalid IDs to test exception handling
        String[] ids = {"MEM01", "GM01", "MEM02", " ", "MEM03"};

        System.out.println(GymMemberP1.signUpBatch(ids, 1000));
    }
}