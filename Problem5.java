public class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otpHash; // Stores standard transformed hash, NO getter provided anywhere

    // JavaBean public no-argument constructor
    public MovieBookingProfile() {
    }

    // Convenience constructor chaining to default constructor via this()
    public MovieBookingProfile(String name) {
        this();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // JavaBean boolean standard getter naming convention
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    // Write-only OTP property: transforms & saves input, no getter exists anywhere
    public void setOtp(String otp) {
        if (otp != null && !otp.isEmpty()) {
            this.otpHash = computeHash(otp);
        }
    }

    private String computeHash(String input) {
        int hash = 17;
        for (int i = 0; i < input.length(); i++) {
            hash = hash * 31 + input.charAt(i);
        }
        return "OTP_HASH_" + Math.abs(hash);
    }

    public static void main(String[] args) {
        // Test 1: Chained constructor
        MovieBookingProfile p1 = new MovieBookingProfile("Rahul Dev");
        System.out.println("Name: " + p1.getName()); // Output: Rahul Dev

        // Test 2: Boolean property setting & getting
        p1.setConfirmed(true);
        System.out.println("Is Confirmed: " + p1.isConfirmed()); // Output: true

        // Test 3: Write-only property execution
        p1.setOtp("4471");
        System.out.println("OTP successfully set. (No getter method available on class)");
    }
}