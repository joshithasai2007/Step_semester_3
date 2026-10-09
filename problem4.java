
public class problem4 {
    interface MembershipPlan {
        int getMonths();
        double getDiscount();
        String getName();

        default double calculateFee() {
            return 1000 * getMonths() * (1 - getDiscount());
        }
    }

    static class Monthly implements MembershipPlan {
        public int getMonths() { return 1; }
        public double getDiscount() { return 0; }
        public String getName() { return "Monthly"; }
    }

    static class Quarterly implements MembershipPlan {
        public int getMonths() { return 3; }
        public double getDiscount() { return 0.10; }
        public String getName() { return "Quarterly"; }
    }

    static class Annual implements MembershipPlan {
        public int getMonths() { return 12; }
        public double getDiscount() { return 0.25; }
        public String getName() { return "Annual"; }
    }

    static class Member {
        String name;
        Membership membership;

        Member(String name) {
            this.name = name;
        }

        void buyMembership(MembershipPlan plan) {
            membership = new Membership(this, plan);
            System.out.printf(
                "%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
                plan.getName(), name, plan.calculateFee());
        }

        void checkIn() {
            if (membership == null)
                System.out.println("Check-in denied: No membership.");
            else
                membership.checkIn();
        }
    }

    static class Membership {
        private Member member;
        private String status = "Active";

        Membership(Member member, MembershipPlan plan) {
            this.member = member;
        }

        void checkIn() {
            if (status.equals("Active"))
                System.out.println(member.name + " checked in successfully.");
            else
                System.out.println("Check-in denied: " + member.name
                    + "'s membership is " + status + ".");
        }

        void freeze() {
            if (status.equals("Expired"))
                System.out.println("Cannot freeze an Expired membership.");
            else if (status.equals("Frozen"))
                System.out.println("Membership is already Frozen.");
            else {
                status = "Frozen";
                System.out.println(member.name + "'s membership frozen.");
                System.out.println("Status: " + status + ".");
            }
        }

        void unfreeze() {
            if (status.equals("Expired"))
                System.out.println("Cannot unfreeze an Expired membership.");
            else if (status.equals("Frozen")) {
                status = "Active";
                System.out.println(member.name + "'s membership unfrozen.");
            } else
                System.out.println("Membership is already Active.");
        }

        void expire() {
            status = "Expired";
            System.out.println(member.name + "'s membership expired.");
            System.out.println("Status: " + status + ".");
        }
    }

    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        asha.buyMembership(new Quarterly());
        ravi.buyMembership(new Monthly());

        asha.checkIn();
        asha.membership.freeze();
        asha.checkIn();

        ravi.membership.expire();
        ravi.membership.freeze();
    }
}