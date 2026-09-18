class GymMemberP2 {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMemberP2(String memberId, int monthlyFee) {
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
}

class PremiumMemberP2 extends GymMemberP2 {
    protected String trainerName;

    public PremiumMemberP2(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Premium Member | Trainer: " + trainerName + " | Sessions: " + sessionsAttended);
    }
}

class EliteMemberP2 extends PremiumMemberP2 {
    private String lockerNumber;

    public EliteMemberP2(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("Elite Member | Trainer: " + trainerName + " | Locker: " + lockerNumber + " | Sessions: " + sessionsAttended);
    }
}

class GroupClassMemberP2 extends GymMemberP2 {
    private String className;

    public GroupClassMemberP2(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public void displayInfo() {
        System.out.println("Group Class Member | Class: " + className + " | Sessions: " + sessionsAttended);
    }
}

public class Problem2 {

    static String classifyGeneration(GymMemberP2 member) {
        if (member instanceof EliteMemberP2) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof GroupClassMemberP2) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Standard/Premium Member";
    }

    static int getTotalSessionsAttended(GymMemberP2[] members) {
        int total = 0;
        for (GymMemberP2 member : members) {
            total += member.getSessionsAttended();
        }
        return total;
    }

    public static void main(String[] args) {
        GymMemberP2 m1 = new GymMemberP2("MEM1", 1000);
        PremiumMemberP2 m2 = new PremiumMemberP2("MEM2", 2000, "Coach Riya");
        EliteMemberP2 m3 = new EliteMemberP2("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMemberP2 m4 = new GroupClassMemberP2("MEM4", 1500, "Zumba");

        m1.displayInfo();
        m2.displayInfo();
        m3.displayInfo();
        m4.displayInfo();

        System.out.println(classifyGeneration(m3));
        System.out.println(classifyGeneration(m4));

        m2.attendSession();
        m2.attendSession();
        m2.attendSession();

        m3.attendSession();
        m3.attendSession();

        m4.attendSession();
        m4.attendSession();
        m4.attendSession();
        m4.attendSession();

        GymMemberP2[] members = {m2, m3, m4};

        System.out.println("Total Sessions: " + getTotalSessionsAttended(members));
    }
}