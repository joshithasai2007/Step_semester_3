class GymMemberP5 {

    private static int membersEnrolled = 0;
    private final String membershipNumber;

    protected int monthlyFee;
    private int feesPaid = 0;

    public GymMemberP5(int monthlyFee) {
        this.monthlyFee = monthlyFee;
        membersEnrolled++;
        membershipNumber = "GYM-" + (2000 + membersEnrolled);
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        System.out.println("Payment Mode: " + mode);
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }
        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public void checkIn() {
        System.out.println("Individual check-in");
    }
}

class GroupClassMemberP5 extends GymMemberP5 {

    private String className;

    public GroupClassMemberP5(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    @Override
    public void checkIn() {
        System.out.println("Group class check-in");
    }
}

public class Problem5 {

    static String processWeeklyCheckIn(GymMemberP5[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (GymMemberP5 member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof GroupClassMemberP5) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               group + " group | " +
               individual + " individual";
    }

    public static void main(String[] args) {
        GymMemberP5 m1 = new GymMemberP5(1000);

        System.out.println("Enrolled Members: " + GymMemberP5.getMembersEnrolled());

        System.out.println("G45B: " + GymMemberP5.isValidReferralCode("G45B"));
        System.out.println("G4B:  " + GymMemberP5.isValidReferralCode("G4B"));
        System.out.println("X45B: " + GymMemberP5.isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");

        System.out.println("Fees Paid: " + m1.getFeesPaid());

        GymMemberP5[] members = {
            new GroupClassMemberP5(1500, "Zumba"),
            null,
            new GymMemberP5(1000)
        };

        System.out.println(processWeeklyCheckIn(members));
    }
}