
class PhoneFormatter {
    String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        StringBuilder result = new StringBuilder("XXXXXX");
        result.append(phone.substring(6));

        result.insert(6, "-");

        return result.toString();
    }
}

public class Problem4 {
    public static void main(String[] args) {
        PhoneFormatter p = new PhoneFormatter();

        System.out.println(p.maskPhoneNumber("9876543210"));
        System.out.println(p.maskPhoneNumber("98765"));
    }
}