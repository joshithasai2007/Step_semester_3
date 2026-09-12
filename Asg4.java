public class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash; // Stores one-way transformed security answer

    // Required JavaBean public no-arg constructor
    public LibraryMember() {}

    // Write-Once Property: setter takes effect only on the first non-null call
    public void setMembershipId(String id) {
        if (this.membershipId == null && id != null) {
            this.membershipId = id;
        }
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // JavaBean Boolean Convention uses 'is' prefix
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    // Write-Only Property: Stores transformed string, NO getter exists anywhere
    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswerHash = simpleHashTransform(answer);
        }
    }

    // Simple deterministic one-way transformation
    private String simpleHashTransform(String input) {
        int hash = 7;
        for (int i = 0; i < input.length(); i++) {
            hash = hash * 31 + input.charAt(i);
        }
        return "HASH_" + Math.abs(hash);
    }

    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println("ID: " + m.getMembershipId()); // Output: LIB-8841
        System.out.println("Is Premium: " + m.isPremiumMember()); // Output: true

        // Write-Once Verification
        m.setMembershipId("FAKE-0000");
        System.out.println("ID after overwrite attempt: " + m.getMembershipId()); // Output: LIB-8841

        // Write-Only Verification
        m.setSecurityAnswer("BlueMountain");
        System.out.println("Security answer set (no getter exists to expose it).");
    }
}