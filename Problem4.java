class GymMemberP4 {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMemberP4(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void displayInfo(StringBuilder sb) {
        sb.append("Standard | Sessions: ").append(sessionsAttended);
    }
}

class PremiumMemberP4 extends GymMemberP4 {
    private String trainerName;

    public PremiumMemberP4(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo(StringBuilder sb) {
        sb.append("Premium | Trainer: ").append(trainerName).append(" | Sessions: ").append(sessionsAttended);
    }
}

public class Problem4 {

    static String batchPrint(GymMemberP4[] members) {
        StringBuilder sb = new StringBuilder();

        for (GymMemberP4 member : members) {
            member.displayInfo(sb);

            if (member instanceof PremiumMemberP4) {
                PremiumMemberP4 p = (PremiumMemberP4) member;
                sb.append(" [Trainer via downcast: ").append(p.getTrainerName()).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        GymMemberP4[] members = {
            new GymMemberP4("MEM6", 1000),
            new PremiumMemberP4("MEM7", 2000, "Coach Riya")
        };

        System.out.println(batchPrint(members));
    }
}